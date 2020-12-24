package com.alan344.java.render;

import com.alan344.OutputUtilities;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class BodyLineRenderer {

    public List<String> render(List<String> bodyLines) {
        List<String> lines = new ArrayList<>();
        int indentLevel = 1;
        StringBuilder sb = new StringBuilder();

        ListIterator<String> listIter = bodyLines.listIterator();
        while (listIter.hasNext()) {
            sb.setLength(0);
            String line = listIter.next();
            if (line.startsWith("}")) {
                indentLevel--;
            }

            OutputUtilities.javaIndent(sb, indentLevel);
            sb.append(line);
            lines.add(sb.toString());

            if (isCodeBlockStartExceptSwitchStatement(line) || line.endsWith(":")) {
                indentLevel++;
            }

            if (line.startsWith("break")) {
                // if the next line is '}', then don't outdent
                if (listIter.hasNext()) {
                    String nextLine = listIter.next();
                    if (nextLine.startsWith("}")) {
                        indentLevel++;
                    }

                    // set back to the previous element
                    listIter.previous();
                }
                indentLevel--;
            }
        }

        return lines;
    }

    private boolean isCodeBlockStartExceptSwitchStatement(String line) {
        return line.endsWith("{") && !line.startsWith("switch");
    }
}
