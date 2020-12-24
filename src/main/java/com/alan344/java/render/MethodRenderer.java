package com.alan344.java.render;

import com.alan344.java.CompilationUnit;
import com.alan344.java.JavaDomUtils;
import com.alan344.java.JavaVisibility;
import com.alan344.java.Method;
import com.alan344.util.CustomCollectors;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MethodRenderer {
    private final TypeParameterRenderer typeParameterRenderer = new TypeParameterRenderer();
    private final ParameterRenderer parameterRenderer = new ParameterRenderer();
    private final BodyLineRenderer bodyLineRenderer = new BodyLineRenderer();

    public List<String> render(Method method, boolean inInterface, CompilationUnit compilationUnit) {
        List<String> lines = new ArrayList<>();

        lines.addAll(method.getJavaDocLines());
        lines.addAll(method.getAnnotations());
        lines.add(getFirstLine(method, inInterface, compilationUnit));

        if (!method.isAbstract() && !method.isNative()) {
            lines.addAll(bodyLineRenderer.render(method.getBodyLines()));
            lines.add("}");
        }

        return lines;
    }

    private String getFirstLine(Method method, boolean inInterface, CompilationUnit compilationUnit) {
        StringBuilder sb = new StringBuilder();

        sb.append(renderVisibility(method, inInterface));

        if (method.isAbstract() && !inInterface) {
            sb.append("abstract ");
        }

        if (method.isDefault()) {
            sb.append("default ");
        }

        if (method.isStatic()) {
            sb.append("static ");
        }

        if (method.isFinal()) {
            sb.append("final ");
        }

        if (method.isSynchronized()) {
            sb.append("synchronized ");
        }

        if (method.isNative()) {
            sb.append("native ");
        }

        sb.append(renderTypeParameters(method, compilationUnit));

        if (!method.isConstructor()) {
            sb.append(method.getReturnType()
                    .map(t -> JavaDomUtils.calculateTypeName(compilationUnit, t))
                    .orElse("void"));

            sb.append(' ');
        }

        sb.append(method.getName());

        sb.append(renderParameters(method, compilationUnit));

        sb.append(renderExceptions(method, compilationUnit));

        if (method.isAbstract() || method.isNative()) {
            sb.append(';');
        } else {
            sb.append(" {");
        }
        return sb.toString();

    }

    private String renderVisibility(Method method, boolean inInterface) {
        if (inInterface && method.getVisibility() == JavaVisibility.PUBLIC) {
            return "";
        }

        return method.getVisibility().getValue();
    }

    /**
     * should return an empty string if no type parameters
     */
    private String renderTypeParameters(Method method, CompilationUnit compilationUnit) {
        return method.getTypeParameters().stream()
                .map(tp -> typeParameterRenderer.render(tp, compilationUnit))
                .collect(CustomCollectors.joining(", ", "<", "> "));
    }

    private String renderParameters(Method method, CompilationUnit compilationUnit) {
        return method.getParameters().stream()
                .map(p -> parameterRenderer.render(p, compilationUnit))
                .collect(Collectors.joining(", ", "(", ")"));
    }

    /**
     * should return an empty string if no exceptions
     */
    private String renderExceptions(Method method, CompilationUnit compilationUnit) {
        return method.getExceptions().stream()
                .map(jt -> JavaDomUtils.calculateTypeName(compilationUnit, jt))
                .collect(CustomCollectors.joining(", ", " throws ", ""));
    }
}
