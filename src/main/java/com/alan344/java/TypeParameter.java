package com.alan344.java;

import com.alan344.java.render.TypeParameterRenderer;

import java.util.ArrayList;
import java.util.List;

public class TypeParameter {

    private final String name;

    private final List<FullyQualifiedJavaType> extendsTypes = new ArrayList<>();

    public TypeParameter(String name) {
        super();
        this.name = name;
    }

    public TypeParameter(String name, List<FullyQualifiedJavaType> extendsTypes) {
        super();
        this.name = name;
        this.extendsTypes.addAll(extendsTypes);
    }

    public String getName() {
        return name;
    }

    public List<FullyQualifiedJavaType> getExtendsTypes() {
        return extendsTypes;
    }

    @Override
    public String toString() {
        return new TypeParameterRenderer().render(this, null);
    }
}
