package org.example.microjava.psi.manipulator

import com.intellij.openapi.util.TextRange
import com.intellij.psi.ElementManipulator
import org.example.microjava.psi.MicroJavaReferencable
import org.example.microjava.psi.impl.MicroJavaElementFactory

class ReferencableManipulator : ElementManipulator<MicroJavaReferencable> {
    override fun handleContentChange(
        element: MicroJavaReferencable,
        range: TextRange,
        newContent: String?,
    ): MicroJavaReferencable? {
        if (newContent == null) {
            return null
        }

        element.node.replaceChild(element.firstChild.node, MicroJavaElementFactory.createIdent(newContent))
        return element
    }

    override fun handleContentChange(element: MicroJavaReferencable, newContent: String?): MicroJavaReferencable? {
        if (newContent == null) {
            return null
        }
        element.node.replaceChild(element.firstChild.firstChild.node, MicroJavaElementFactory.createIdent(newContent))
        return element
    }

    override fun getRangeInElement(element: MicroJavaReferencable): TextRange {
        return element.reference?.rangeInElement ?: TextRange.EMPTY_RANGE
    }
}