package com.alan344;

import com.alan344.xml.Document;
import com.alan344.xml.render.DocumentRenderer;

/**
 * This class is the default formatter for generated XML.  This class will use the
 * built in document renderer.
 *
 * @author Jeff Butler
 */
public class DefaultXmlFormatter implements XmlFormatter {

    @Override
    public String getFormattedContent(Document document) {
        return new DocumentRenderer().render(document);
    }
}
