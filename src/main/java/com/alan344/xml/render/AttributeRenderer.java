package com.alan344.xml.render;

import com.alan344.xml.Attribute;

public class AttributeRenderer {

    public String render(Attribute attribute) {
        return attribute.getName()
                + "=\""
                + attribute.getValue()
                + "\"";
    }
}
