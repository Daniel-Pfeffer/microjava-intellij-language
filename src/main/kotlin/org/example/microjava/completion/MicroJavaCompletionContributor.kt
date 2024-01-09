package org.example.microjava.completion

import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.patterns.PlatformPatterns.psiElement
import org.example.microjava.MicroJavaLanguage

class MicroJavaCompletionContributor : CompletionContributor() {

    init {
        extend(
            CompletionType.BASIC,
            psiElement().withLanguage(MicroJavaLanguage),
            MicroJavaCompletionProvider()
        )
    }
}