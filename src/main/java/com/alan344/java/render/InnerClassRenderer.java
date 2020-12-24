package com.alan344.java.render;

import com.alan344.java.CompilationUnit;
import com.alan344.java.InnerClass;
import com.alan344.java.JavaDomUtils;
import com.alan344.util.CustomCollectors;

import java.util.ArrayList;
import java.util.List;

public class InnerClassRenderer {

    public List<String> render(InnerClass innerClass, CompilationUnit compilationUnit) {
        List<String> lines = new ArrayList<>();

        lines.addAll(innerClass.getJavaDocLines());
        lines.addAll(innerClass.getAnnotations());
        lines.add(renderFirstLine(innerClass, compilationUnit));
        lines.addAll(RenderingUtilities.renderFields(innerClass.getFields(), compilationUnit));
        lines.addAll(RenderingUtilities.renderInitializationBlocks(innerClass.getInitializationBlocks()));
        lines.addAll(RenderingUtilities.renderClassOrEnumMethods(innerClass.getMethods(), compilationUnit));
        lines.addAll(RenderingUtilities.renderInnerClasses(innerClass.getInnerClasses(), compilationUnit));
        lines.addAll(RenderingUtilities.renderInnerInterfaces(innerClass.getInnerInterfaces(), compilationUnit));
        lines.addAll(RenderingUtilities.renderInnerEnums(innerClass.getInnerEnums(), compilationUnit));

        lines = RenderingUtilities.removeLastEmptyLine(lines);

        lines.add("}");

        return lines;
    }

    private String renderFirstLine(InnerClass innerClass, CompilationUnit compilationUnit) {
        StringBuilder sb = new StringBuilder();

        sb.append(innerClass.getVisibility().getValue());

        if (innerClass.isAbstract()) {
            sb.append("abstract ");
        }

        if (innerClass.isStatic()) {
            sb.append("static ");
        }

        if (innerClass.isFinal()) {
            sb.append("final ");
        }

        sb.append("class ");
        sb.append(innerClass.getType().getShortName());
        sb.append(RenderingUtilities.renderTypeParameters(innerClass.getTypeParameters(), compilationUnit));
        sb.append(renderSuperClass(innerClass, compilationUnit));
        sb.append(renderSuperInterfaces(innerClass, compilationUnit));
        sb.append(" {");

        return sb.toString();
    }

    private String renderSuperClass(InnerClass innerClass, CompilationUnit compilationUnit) {
        return innerClass.getSuperClass()
                .map(sc -> " extends " + JavaDomUtils.calculateTypeName(compilationUnit, sc))
                .orElse("");
    }

    /**
     * should return an empty string if no super interfaces
     */
    private String renderSuperInterfaces(InnerClass innerClass, CompilationUnit compilationUnit) {
        return innerClass.getSuperInterfaceTypes().stream()
                .map(tp -> JavaDomUtils.calculateTypeName(compilationUnit, tp))
                .collect(CustomCollectors.joining(", ", " implements ", ""));
    }
}
