package com.alan344.xml.render;

import com.alan344.xml.DocType;
import com.alan344.xml.Document;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DocumentRenderer {

    public String render(Document document) {
        return Stream.of(renderXmlHeader(),
                renderDocType(document),
                renderRootElement(document))
                .flatMap(Function.identity())
                .collect(Collectors.joining(System.getProperty("line.separator")));
    }

    private Stream<String> renderXmlHeader() {
        return Stream.of("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    }

    private Stream<String> renderDocType(Document document) {
        return Stream.of("<!DOCTYPE "
                + document.getRootElement().getName()
                + document.getDocType().map(this::renderDocType).orElse("")
                + ">");
    }

    private String renderDocType(DocType docType) {
        return " " + docType.accept(new DocTypeRenderer());
    }

    private Stream<String> renderRootElement(Document document) {
        return document.getRootElement().accept(new ElementRenderer());
    }
}
