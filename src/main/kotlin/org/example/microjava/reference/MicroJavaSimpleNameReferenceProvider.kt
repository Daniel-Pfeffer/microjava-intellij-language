package org.example.microjava.reference

import com.intellij.openapi.diagnostic.Logger
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.PsiReferenceProvider
import com.intellij.psi.util.elementType
import com.intellij.util.ProcessingContext
import org.example.microjava.psi.MicroJavaReferencable
import org.example.microjava.psi.MicroJavaTypes

class MicroJavaSimpleNameReferenceProvider : PsiReferenceProvider() {
    override fun getReferencesByElement(element: PsiElement, context: ProcessingContext): Array<PsiReference> {
        LOG.info("getReferencesByElement: $element")
        return when {
            element.elementType == MicroJavaTypes.IDENT
                && element.parent is MicroJavaReferencable -> element.parent.references

            element !is MicroJavaReferencable -> PsiReference.EMPTY_ARRAY
            else -> arrayOf(MicroJavaSimpleNameReference(element))
        }
    }

    companion object {
        val LOG = Logger.getInstance(MicroJavaSimpleNameReferenceProvider::class.java)
    }
}
