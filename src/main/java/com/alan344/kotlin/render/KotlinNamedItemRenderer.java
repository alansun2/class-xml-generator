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


import com.alan344.kotlin.*;

import java.util.List;

public class KotlinNamedItemRenderer implements KotlinNamedItemVisitor<List<String>> {

    public List<String> render(KotlinNamedItem namedItem) {
        return namedItem.accept(this);
    }

    @Override
    public List<String> visit(KotlinType kotlinType) {
        return new KotlinTypeRenderer().render(kotlinType);
    }

    @Override
    public List<String> visit(KotlinProperty kotlinProperty) {
        return new KotlinPropertyRenderer().render(kotlinProperty);
    }

    @Override
    public List<String> visit(KotlinFunction kotlinFunction) {
        return new KotlinFunctionRenderer().render(kotlinFunction);
    }
}
