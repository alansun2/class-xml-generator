package com.alan344;

public class OutputUtilities {

    /**
     * Utility class - no instances allowed.
     */
    private OutputUtilities() {
        super();
    }

    /**
     * Utility method that indents the buffer by the default amount for Java
     * (four spaces per indent level).
     *
     * @param sb
     *            a StringBuilder to append to
     * @param indentLevel
     *            the required indent level
     */
    public static void javaIndent(StringBuilder sb, int indentLevel) {
        for (int i = 0; i < indentLevel; i++) {
            sb.append("    ");
        }
    }

    /**
     * Utility method that indents the buffer by the default amount for XML (two
     * spaces per indent level).
     *
     * @param sb
     *            a StringBuilder to append to
     * @param indentLevel
     *            the required indent level
     */
    public static void xmlIndent(StringBuilder sb, int indentLevel) {
        for (int i = 0; i < indentLevel; i++) {
            sb.append("  ");
        }
    }
}
