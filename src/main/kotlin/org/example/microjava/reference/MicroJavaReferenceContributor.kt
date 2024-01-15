package org.example.microjava.reference

import com.intellij.openapi.diagnostic.Logger
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.PsiReferenceContributor
import com.intellij.psi.PsiReferenceRegistrar

class MicroJavaReferenceContributor : PsiReferenceContributor() {
    override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
        LOG.info("registerReferenceProviders")
        registrar.registerReferenceProvider(
            PlatformPatterns.psiElement(),
            MicroJavaSimpleNameReferenceProvider()
        )
    }

    companion object {
        val LOG = Logger.getInstance(MicroJavaReferenceContributor::class.java)
    }
}