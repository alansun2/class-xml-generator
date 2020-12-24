package com.alan344.java.render;

import com.alan344.java.CompilationUnit;
import com.alan344.java.JavaDomUtils;
import com.alan344.java.TypeParameter;
import com.alan344.util.CustomCollectors;

public class TypeParameterRenderer {
    public String render(TypeParameter typeParameter, CompilationUnit compilationUnit) {
        return typeParameter.getName() + typeParameter.getExtendsTypes().stream()
                .map(t -> JavaDomUtils.calculateTypeName(compilationUnit, t))
                .collect(CustomCollectors.joining(" & ", " extends ", ""));
    }
}
