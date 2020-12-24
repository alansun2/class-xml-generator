package com.alan344.xml.render;

import com.alan344.util.CustomCollectors;
import com.alan344.xml.ElementVisitor;
import com.alan344.xml.TextElement;
import com.alan344.xml.VisitableElement;
import com.alan344.xml.XmlElement;

import java.util.stream.Stream;

public class ElementRenderer implements ElementVisitor<Stream<String>> {

    private final AttributeRenderer attributeRenderer = new AttributeRenderer();

    @Override
    public Stream<String> visit(TextElement element) {
        return Stream.of(element.getContent());
    }

    @Override
    public Stream<String> visit(XmlElement element) {
        if (element.hasChildren()) {
            return renderWithChildren(element);
        } else {
            return renderWithoutChildren(element);
        }
    }

    private Stream<String> renderWithoutChildren(XmlElement element) {
        return Stream.of("<"
                + element.getName()
                + renderAttributes(element)
                + " />");
    }

    public Stream<String> renderWithChildren(XmlElement element) {
        return Stream.of(renderOpen(element), renderChildren(element), renderClose(element))
                .flatMap(s -> s);
    }

    private String renderAttributes(XmlElement element) {
        return element.getAttributes().stream()
                .sorted((a1, a2) -> a1.getName().compareTo(a2.getName()))
                .map(attributeRenderer::render)
                .collect(CustomCollectors.joining(" ", " ", ""));
    }

    private Stream<String> renderOpen(XmlElement element) {
        return Stream.of("<"
                + element.getName()
                + renderAttributes(element)
                + ">");
    }

    private Stream<String> renderChildren(XmlElement element) {
        return element.getElements().stream()
                .flatMap(this::renderChild)
                .map(this::indent);
    }

    private Stream<String> renderChild(VisitableElement child) {
        return child.accept(this);
    }

    private String indent(String s) {
        return "  " + s;
    }

    private Stream<String> renderClose(XmlElement element) {
        return Stream.of("</"
                + element.getName()
                + ">");
    }
}
