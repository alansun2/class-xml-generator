package com.alan344.xml;

public interface DocType {
    <R> R accept(DocTypeVisitor<R> visitor);
}
