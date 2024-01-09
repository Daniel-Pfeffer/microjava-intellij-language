package org.example.microjava.psi

import com.intellij.psi.tree.IElementType
import org.example.microjava.MicroJavaLanguage


class MicroJavaTokenType(debugName: String) : IElementType(debugName, MicroJavaLanguage) {

    override fun toString(): String {
        return "MicroJavaTokenType." + super.toString()
    }
}