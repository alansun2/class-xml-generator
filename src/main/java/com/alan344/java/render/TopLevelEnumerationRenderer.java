package com.alan344.java.render;

import com.alan344.java.TopLevelEnumeration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.alan344.java.render.RenderingUtilities.*;

public class TopLevelEnumerationRenderer {

    public String render(TopLevelEnumeration topLevelEnumeration) {
        List<String> lines = new ArrayList<>();

        lines.addAll(topLevelEnumeration.getFileCommentLines());
        lines.addAll(renderPackage(topLevelEnumeration));
        lines.addAll(renderStaticImports(topLevelEnumeration));
        lines.addAll(renderImports(topLevelEnumeration));
        lines.addAll(renderInnerEnumNoIndent(topLevelEnumeration, topLevelEnumeration));

        return lines.stream().collect(Collectors.joining(System.getProperty("line.separator")));
    }
}
