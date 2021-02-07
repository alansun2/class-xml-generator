/*
 *    Copyright 2006-2020 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.alan344.kotlin.render;

import com.alan344.kotlin.KotlinModifier;
import com.alan344.util.CustomCollectors;

import java.util.List;

public class KotlinRenderingUtilities {

    private KotlinRenderingUtilities() {}

    public static final String KOTLIN_INDENT = "    "; //$NON-NLS-1$

    public static String renderModifiers(List<KotlinModifier> modifiers) {
        return modifiers.stream().map(KotlinModifier::getValue)
                .collect(CustomCollectors.joining(" ", "", " ")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

    public static String kotlinIndent(String in) {
        if (in.isEmpty()) {
            return in; // don't indent empty lines
        }

        return KOTLIN_INDENT + in;
    }
}
