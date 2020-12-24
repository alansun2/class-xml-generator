package com.alan344.java.render;

import com.alan344.java.CompilationUnit;
import com.alan344.java.Field;
import com.alan344.java.JavaDomUtils;

import java.util.ArrayList;
import java.util.List;

public class FieldRenderer {

    public List<String> render(Field field, CompilationUnit compilationUnit) {
        List<String> lines = new ArrayList<>();

        lines.addAll(field.getJavaDocLines());
        lines.addAll(field.getAnnotations());
        lines.add(renderField(field, compilationUnit));

        return lines;
    }

    private String renderField(Field field, CompilationUnit compilationUnit) {
        StringBuilder sb = new StringBuilder();
        sb.append(field.getVisibility().getValue());

        if (field.isStatic()) {
            sb.append("static ");
        }

        if (field.isFinal()) {
            sb.append("final ");
        }

        if (field.isTransient()) {
            sb.append("transient ");
        }

        if (field.isVolatile()) {
            sb.append("volatile ");
        }

        sb.append(JavaDomUtils.calculateTypeName(compilationUnit, field.getType()));
        sb.append(' ');
        sb.append(field.getName());
        sb.append(renderInitializationString(field));
        sb.append(';');

        return sb.toString();
    }

    private String renderInitializationString(Field field) {
        return field.getInitializationString()
                .map(is -> " = " + is)
                .orElse("");
    }
}
