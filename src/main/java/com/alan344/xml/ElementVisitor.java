package com.alan344.xml;

public interface ElementVisitor<R> {
    R visit(TextElement element);

    R visit(XmlElement element);
}
