package com.alan344.java.render;

import com.alan344.java.Interface;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.alan344.java.render.RenderingUtilities.*;

public class TopLevelInterfaceRenderer {

    public String render(Interface topLevelInterface) {
        List<String> lines = new ArrayList<>();

        lines.addAll(topLevelInterface.getFileCommentLines());
        lines.addAll(renderPackage(topLevelInterface));
        lines.addAll(renderStaticImports(topLevelInterface));
        lines.addAll(renderImports(topLevelInterface));
        lines.addAll(renderInnerInterfaceNoIndent(topLevelInterface, topLevelInterface));

        return lines.stream().collect(Collectors.joining(System.getProperty("line.separator")));
    }
}
