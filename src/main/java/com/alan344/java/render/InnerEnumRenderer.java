package com.alan344.java.render;

import com.alan344.java.CompilationUnit;
import com.alan344.java.InnerEnum;
import com.alan344.java.JavaDomUtils;
import com.alan344.util.CustomCollectors;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InnerEnumRenderer {

    public List<String> render(InnerEnum innerEnum, CompilationUnit compilationUnit) {
        List<String> lines = new ArrayList<>();

        lines.addAll(innerEnum.getJavaDocLines());
        lines.addAll(innerEnum.getAnnotations());
        lines.add(renderFirstLine(innerEnum, compilationUnit));
        lines.addAll(renderEnumConstants(innerEnum));
        lines.addAll(RenderingUtilities.renderFields(innerEnum.getFields(), compilationUnit));
        lines.addAll(RenderingUtilities.renderInitializationBlocks(innerEnum.getInitializationBlocks()));
        lines.addAll(RenderingUtilities.renderClassOrEnumMethods(innerEnum.getMethods(), compilationUnit));
        lines.addAll(RenderingUtilities.renderInnerClasses(innerEnum.getInnerClasses(), compilationUnit));
        lines.addAll(RenderingUtilities.renderInnerInterfaces(innerEnum.getInnerInterfaces(), compilationUnit));
        lines.addAll(RenderingUtilities.renderInnerEnums(innerEnum.getInnerEnums(), compilationUnit));

        lines = RenderingUtilities.removeLastEmptyLine(lines);

        lines.add("}");

        return lines;
    }

    private String renderFirstLine(InnerEnum innerEnum, CompilationUnit compilationUnit) {
        StringBuilder sb = new StringBuilder();

        sb.append(innerEnum.getVisibility().getValue());

        if (innerEnum.isStatic()) {
            sb.append("static ");
        }

        sb.append("enum ");
        sb.append(innerEnum.getType().getShortName());
        sb.append(renderSuperInterfaces(innerEnum, compilationUnit));
        sb.append(" {");

        return sb.toString();
    }

    private List<String> renderEnumConstants(InnerEnum innerEnum) {
        List<String> answer = new ArrayList<>();

        Iterator<String> iter = innerEnum.getEnumConstants().iterator();
        while (iter.hasNext()) {
            String enumConstant = iter.next();

            if (iter.hasNext()) {
                answer.add(RenderingUtilities.JAVA_INDENT + enumConstant + ",");
            } else {
                answer.add(RenderingUtilities.JAVA_INDENT + enumConstant + ";");
            }
        }

        answer.add("");
        return answer;
    }

    /**
     * should return an empty string if no super interfaces
     */
    private String renderSuperInterfaces(InnerEnum innerEnum, CompilationUnit compilationUnit) {
        return innerEnum.getSuperInterfaceTypes().stream()
                .map(tp -> JavaDomUtils.calculateTypeName(compilationUnit, tp))
                .collect(CustomCollectors.joining(", ", " implements ", "")); //$NON-NLS-2$ //$NON-NLS-3$
    }
}
