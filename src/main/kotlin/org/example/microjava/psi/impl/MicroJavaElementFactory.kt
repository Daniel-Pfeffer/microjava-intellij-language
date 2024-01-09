package org.example.microjava.psi.impl

import com.intellij.lang.ASTNode
import com.intellij.psi.impl.source.codeStyle.CodeEditUtil
import com.intellij.psi.impl.source.tree.LeafPsiElement
import org.example.microjava.psi.MicroJavaTypes

object MicroJavaElementFactory {
    fun createIdent(newName: String): ASTNode {
        val newNode =  object : LeafPsiElement(MicroJavaTypes.IDENT, newName) {
        }

        CodeEditUtil.setNodeGenerated(newNode, true)

        return newNode
    }
}
