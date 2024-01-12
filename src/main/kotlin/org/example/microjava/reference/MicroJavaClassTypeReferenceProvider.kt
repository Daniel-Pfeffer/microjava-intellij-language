package org.example.microjava.reference

import com.intellij.openapi.diagnostic.Logger
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.PsiReferenceProvider
import com.intellij.util.ProcessingContext
import org.example.microjava.psi.MicroJavaSimpleType

class MicroJavaClassTypeReferenceProvider : PsiReferenceProvider() {
    override fun getReferencesByElement(element: PsiElement, context: ProcessingContext): Array<PsiReference> {
        LOG.info("getReferencesByElement: $element")

        if (element !is MicroJavaSimpleType) {
            return PsiReference.EMPTY_ARRAY
        }

        val typeDecl = element.firstChild.text

        if (typeDecl == "int" || typeDecl == "char") {
            return PsiReference.EMPTY_ARRAY
        }

        return arrayOf(MicroJavaClassTypeReference(element))
    }

    companion object {
        val LOG = Logger.getInstance(MicroJavaClassTypeReferenceProvider::class.java)
    }
}
