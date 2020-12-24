package com.alan344.java;

public interface CompilationUnitVisitor<R> {
    R visit(TopLevelClass topLevelClass);

    R visit(TopLevelEnumeration topLevelEnumeration);

    R visit(Interface topLevelInterface);
}
