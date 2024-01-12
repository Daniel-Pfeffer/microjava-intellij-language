package org.example.microjava.psi.manipulator

import com.intellij.openapi.util.TextRange
import com.intellij.psi.ElementManipulator
import org.example.microjava.psi.MicroJavaSimpleType
import org.example.microjava.psi.impl.MicroJavaElementFactory

class SimpleTypeManipulator : ElementManipulator<MicroJavaSimpleType> {
    override fun handleContentChange(
        element: MicroJavaSimpleType,
        range: TextRange,
        newContent: String?,
    ): MicroJavaSimpleType? {
        if (newContent == null) {
            return null
        }

        element.node.replaceChild(element.firstChild.node, MicroJavaElementFactory.createIdent(newContent))
        return element
    }

    override fun handleContentChange(element: MicroJavaSimpleType, newContent: String?): MicroJavaSimpleType? {
        if (newContent == null) {
            return null
        }
        element.node.replaceChild(element.firstChild.firstChild.node, MicroJavaElementFactory.createIdent(newContent))
        return element
    }

    override fun getRangeInElement(element: MicroJavaSimpleType): TextRange {
        return element.reference?.rangeInElement ?: TextRange.EMPTY_RANGE
    }
}