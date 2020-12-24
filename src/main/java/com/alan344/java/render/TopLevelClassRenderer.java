package com.alan344.java.render;

import com.alan344.java.TopLevelClass;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.alan344.java.render.RenderingUtilities.*;

public class TopLevelClassRenderer {

    public String render(TopLevelClass topLevelClass) {
        List<String> lines = new ArrayList<>();

        lines.addAll(topLevelClass.getFileCommentLines());
        lines.addAll(renderPackage(topLevelClass));
        lines.addAll(renderStaticImports(topLevelClass));
        lines.addAll(renderImports(topLevelClass));
        lines.addAll(renderInnerClassNoIndent(topLevelClass, topLevelClass));

        return lines.stream().collect(Collectors.joining(System.getProperty("line.separator")));
    }
}
