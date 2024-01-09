package org.example.microjava.completion

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.util.ProcessingContext

class MicroJavaCompletionProvider : CompletionProvider<CompletionParameters>() {
    override fun addCompletions(
        parameters: CompletionParameters,
        context: ProcessingContext, completionResultSet: CompletionResultSet,
    ) {
        completionResultSet.addElement(LookupElementBuilder.create("This is a MicroJava completion"))
    }

}
