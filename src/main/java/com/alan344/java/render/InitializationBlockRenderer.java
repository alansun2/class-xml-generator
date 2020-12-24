package com.alan344.java.render;


import com.alan344.java.InitializationBlock;

import java.util.ArrayList;
import java.util.List;

public class InitializationBlockRenderer {

    private final BodyLineRenderer bodyLineRenderer = new BodyLineRenderer();

    public List<String> render(InitializationBlock initializationBlock) {
        List<String> lines = new ArrayList<>();

        lines.addAll(initializationBlock.getJavaDocLines());
        lines.add(renderFirstLine(initializationBlock));
        lines.addAll(bodyLineRenderer.render(initializationBlock.getBodyLines()));
        lines.add("}");

        return lines;
    }

    private String renderFirstLine(InitializationBlock initializationBlock) {
        if (initializationBlock.isStatic()) {
            return "static {";
        } else {
            return "{";
        }
    }
}
