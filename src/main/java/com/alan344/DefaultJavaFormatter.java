package com.alan344;

import com.alan344.java.*;
import com.alan344.java.render.TopLevelClassRenderer;
import com.alan344.java.render.TopLevelEnumerationRenderer;
import com.alan344.java.render.TopLevelInterfaceRenderer;

/**
 * This class is the default formatter for generated Java.  This class will use the
 * built in DOM renderers.
 *
 * @author Jeff Butler
 */
public class DefaultJavaFormatter implements CompilationUnitVisitor<String>, JavaFormatter {

    @Override
    public String getFormattedContent(CompilationUnit compilationUnit) {
        return compilationUnit.accept(this);
    }

    @Override
    public String visit(TopLevelClass topLevelClass) {
        return new TopLevelClassRenderer().render(topLevelClass);
    }

    @Override
    public String visit(TopLevelEnumeration topLevelEnumeration) {
        return new TopLevelEnumerationRenderer().render(topLevelEnumeration);
    }

    @Override
    public String visit(Interface topLevelInterface) {
        return new TopLevelInterfaceRenderer().render(topLevelInterface);
    }
}
