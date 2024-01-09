package org.example.microjava.psi.manipulator

import com.intellij.openapi.util.TextRange
import com.intellij.psi.ElementManipulator
import org.example.microjava.psi.MicroJavaDesignator
import org.example.microjava.psi.impl.MicroJavaElementFactory

class DesignatorManipulator : ElementManipulator<MicroJavaDesignator> {
    override fun handleContentChange(
        element: MicroJavaDesignator,
        range: TextRange,
        newContent: String?,
    ): MicroJavaDesignator? {
        if (newContent == null) {
            return null
        }

        element.node.replaceChild(element.firstChild.node, MicroJavaElementFactory.createIdent(newContent))
        return element
    }

    override fun handleContentChange(element: MicroJavaDesignator, newContent: String?): MicroJavaDesignator? {
        if (newContent == null) {
            return null
        }
        element.node.replaceChild(element.firstChild.firstChild.node, MicroJavaElementFactory.createIdent(newContent))
        return element
    }

    override fun getRangeInElement(element: MicroJavaDesignator): TextRange {
        return element.reference?.rangeInElement ?: TextRange.EMPTY_RANGE
    }
}