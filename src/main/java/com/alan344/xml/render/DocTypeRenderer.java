package com.alan344.xml.render;

import com.alan344.xml.DocTypeVisitor;
import com.alan344.xml.PublicDocType;
import com.alan344.xml.SystemDocType;

public class DocTypeRenderer implements DocTypeVisitor<String> {

    @Override
    public String visit(PublicDocType docType) {
        return "PUBLIC \""
                + docType.getDtdName()
                + "\" \""
                + docType.getDtdLocation()
                + "\"";
    }

    @Override
    public String visit(SystemDocType docType) {
        return "SYSTEM \""
                + docType.getDtdLocation()
                + "\"";
    }
}
