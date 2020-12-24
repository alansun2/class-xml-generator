package com.alan344.java.render;

import com.alan344.java.CompilationUnit;
import com.alan344.java.JavaDomUtils;
import com.alan344.java.Parameter;
import com.alan344.util.CustomCollectors;

public class ParameterRenderer {

    public String render(Parameter parameter, CompilationUnit compilationUnit) {
        return renderAnnotations(parameter)
                + JavaDomUtils.calculateTypeName(compilationUnit, parameter.getType())
                + " "
                + (parameter.isVarargs() ? "... " : "")
                + parameter.getName();
    }

    /**
     * should return empty string if no annotations
     */
    private String renderAnnotations(Parameter parameter) {
        return parameter.getAnnotations().stream()
                .collect(CustomCollectors.joining(" ", "", " "));
    }
}
