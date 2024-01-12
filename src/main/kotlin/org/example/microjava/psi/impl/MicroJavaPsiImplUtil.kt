package org.example.microjava.psi.impl

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry
import org.example.microjava.psi.*

object MicroJavaPsiImplUtil {

    @JvmStatic
    fun constGetName(element: MicroJavaConstDecl): String? {
        val identNode = element.node.findChildByType(MicroJavaTypes.IDENT)
        if (identNode != null) {
            return identNode.text
        }
        return null
    }

    @JvmStatic
    fun constGetValue(element: MicroJavaConstDecl): String? {
        val typeNode = element.type

        if (typeNode.simpleType.text == "char") {
            val charNode = element.node.findChildByType(MicroJavaTypes.CHAR_CONST)
            if (charNode != null) {
                return charNode.text
            }
        } else if (typeNode.simpleType.text == "int") {
            val numberNode = element.node.findChildByType(MicroJavaTypes.NUMBER)
            if (numberNode != null) {
                return numberNode.text
            }
        }
        return null
    }

    @JvmStatic
    fun getName(element: MicroJavaNamedElement): String? {
        return element.node.findChildByType(MicroJavaTypes.IDENT)
            ?.text
    }

    @JvmStatic
    fun setName(element: MicroJavaNamedElement, newName: String): PsiElement {
        val nameNode = element.node.findChildByType(MicroJavaTypes.IDENT)
        element.node.replaceChild(nameNode!!, MicroJavaElementFactory.createIdent(newName))
        return element
    }

    @JvmStatic
    fun getReferences(element: MicroJavaDesignator): Array<PsiReference> {
        return ReferenceProvidersRegistry.getReferencesFromProviders(element)
    }

    @JvmStatic
    fun getReferences(element: MicroJavaSimpleType): Array<PsiReference> {
        return ReferenceProvidersRegistry.getReferencesFromProviders(element)
    }


    @JvmStatic
    fun getNameIdentifier(element: MicroJavaNamedElement): PsiElement? {
        return element.node.findChildByType(MicroJavaTypes.IDENT)?.psi
    }

    @JvmStatic
    fun isGlobal(element: MicroJavaVarDecl): Boolean {
        return element.parent is MicroJavaFile
    }

    @JvmStatic
    fun isLocal(element: MicroJavaVarDecl): Boolean {
        return !isGlobal(element)
    }

}
