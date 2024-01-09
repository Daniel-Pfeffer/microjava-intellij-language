package org.example.microjava.reference

import com.intellij.openapi.diagnostic.Logger
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.PsiReferenceProvider
import com.intellij.util.ProcessingContext
import org.example.microjava.psi.MicroJavaDesignator

class MicroJavaReferenceProvider : PsiReferenceProvider() {
    override fun getReferencesByElement(element: PsiElement, context: ProcessingContext): Array<PsiReference> {
        LOG.info("getReferencesByElement: $element")
        if (element !is MicroJavaDesignator) {
            return PsiReference.EMPTY_ARRAY
        }

        return arrayOf(MicroJavaReference(element))
    }

    companion object {
        val LOG = Logger.getInstance(MicroJavaReferenceProvider::class.java)
    }
}
