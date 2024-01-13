package org.example.microjava.highlighting

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.psi.PsiElement
import com.intellij.psi.impl.source.tree.LeafPsiElement
import org.example.microjava.psi.*

class MicroJavaHighlightingAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element is LeafPsiElement) {
            return
        }
        when {
            element is MicroJavaDesignator -> {
                val reference = element.references.firstOrNull()?.resolve()?.parent ?: return

                when (reference) {
                    is MicroJavaMethodDecl -> {
                        // this is function
                        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                            .range(element.textRange)
                            .textAttributes(MicroJavaSyntaxHighlighter.FUNCTION_CALL)
                            .create()
                    }

                    is MicroJavaVarDecl -> {
                        val colour = getColourForVarDecl(reference)

                        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                            .range(element.firstChild.textRange)
                            .textAttributes(colour)
                            .create()
                    }

                    is MicroJavaConstDecl -> {
                        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                            .range(element.textRange)
                            .textAttributes(MicroJavaSyntaxHighlighter.CONSTANT)
                            .create()
                    }
                }
            }


            element is MicroJavaMethodDecl -> {
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(element.identifier.textRange)
                    .textAttributes(MicroJavaSyntaxHighlighter.FUNCTION_DECLARATION)
                    .create()
            }

            element is MicroJavaClassDecl -> {
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(element.identifier.textRange)
                    .textAttributes(MicroJavaSyntaxHighlighter.CLASS_DECLARATION)
                    .create()
            }

            element is MicroJavaSimpleType && element.references.isNotEmpty() -> {
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(element.textRange)
                    .textAttributes(MicroJavaSyntaxHighlighter.CLASS_DECLARATION)
                    .create()
            }

            element is MicroJavaIdentifier -> {
                if (element.parent is MicroJavaVarDecl) {
                    val colour = getColourForVarDecl(element.parent as MicroJavaVarDecl)
                    holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                        .range(element.textRange)
                        .textAttributes(colour)
                        .create()
                }
            }


            element is MicroJavaConstDecl -> {
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(element.identifier.textRange)
                    .textAttributes(MicroJavaSyntaxHighlighter.CONSTANT)
                    .create()
            }
        }
    }

    private fun getColourForVarDecl(element: MicroJavaVarDecl): TextAttributesKey {
        // TODO: add more types
        return if (element.isLocal) {
            val varDeclParent = element.parent
            if (varDeclParent is MicroJavaClassDecl) MicroJavaSyntaxHighlighter.FIELD
            else MicroJavaSyntaxHighlighter.LOCAL_VARS
        } else {
            MicroJavaSyntaxHighlighter.GLOBAL_VARS
        }
    }
}