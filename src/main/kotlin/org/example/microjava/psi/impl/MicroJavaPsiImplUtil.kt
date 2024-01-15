package org.example.microjava.psi.impl

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry
import org.example.microjava.psi.*

object MicroJavaPsiImplUtil {
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
    fun getReferences(element: MicroJavaReferencable): Array<PsiReference> {
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
