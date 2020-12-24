package com.alan344.java;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class contains a list of Java reserved words.
 *
 * @author Jeff Butler
 */
public class JavaReservedWords {

    private static final Set<String> reservedWords;

    static {
        String[] words = {"abstract",
                "assert",
                "boolean",
                "break",
                "byte",
                "case",
                "catch",
                "char",
                "class",
                "const",
                "continue",
                "default",
                "do",
                "double",
                "else",
                "enum",
                "extends",
                "final",
                "finally",
                "float",
                "for",
                "goto",
                "if",
                "implements",
                "import",
                "instanceof",
                "int",
                "interface",
                "long",
                "native",
                "new",
                "package",
                "private",
                "protected",
                "public",
                "return",
                "short",
                "static",
                "strictfp",
                "super",
                "switch",
                "synchronized",
                "this",
                "throw",
                "throws",
                "transient",
                "try",
                "void",
                "volatile",
                "while"
        };

        reservedWords = Arrays.stream(words).collect(Collectors.toSet());
    }

    public static boolean containsWord(String word) {
        boolean rc;

        if (word == null) {
            rc = false;
        } else {
            rc = reservedWords.contains(word);
        }

        return rc;
    }

    /**
     * Utility class - no instances allowed.
     */
    private JavaReservedWords() {
    }
}
