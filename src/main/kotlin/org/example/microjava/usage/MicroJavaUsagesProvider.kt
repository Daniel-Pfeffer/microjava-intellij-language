package org.example.microjava.usage

import com.intellij.lang.cacheBuilder.DefaultWordsScanner
import com.intellij.lang.cacheBuilder.WordsScanner
import com.intellij.lang.findUsages.FindUsagesProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.util.childrenOfType
import org.example.microjava.MicroJavaLexerAdapter
import org.example.microjava.psi.*

class MicroJavaUsagesProvider : FindUsagesProvider {
    override fun getWordsScanner(): WordsScanner {
        return DefaultWordsScanner(
            MicroJavaLexerAdapter(),
            MicroJavaTokenSets.IDENTIFER,
            MicroJavaTokenSets.COMMENTS,
            MicroJavaTokenSets.LITERAL
        )
    }

    override fun canFindUsagesFor(psiElement: PsiElement): Boolean {
        return psiElement is MicroJavaNamedElement || psiElement.parent is MicroJavaClassDecl
    }

    override fun getHelpId(psiElement: PsiElement): String? {
        return null
    }

    override fun getType(element: PsiElement): String {
        return when (element) {
            is MicroJavaMethodDecl -> "method"
            is MicroJavaConstDecl -> "const"
            is MicroJavaVarDecl -> "var"
            is MicroJavaClassDecl -> "class"
            else -> ""
        }
    }

    override fun getDescriptiveName(element: PsiElement): String {
        return when (element) {
            is MicroJavaMethodDecl -> element.identifier.name + "(${element.formPars?.typeList?.joinToString(", ") { it.text }})"
            is MicroJavaConstDecl -> "constant ${element.identifier.name}"
            is MicroJavaVarDecl -> "mutable ${element.identifierList.joinToString(",") { it.name ?: "" }}"
            is MicroJavaClassDecl -> "class ${element.identifier.name}"
            else -> ""
        }
    }

    override fun getNodeText(element: PsiElement, useFullName: Boolean): String {
        val computeWith = if (element is MicroJavaIdentifier) {
            element.parent
        } else element

        val scopeName = when (val parent = computeWith.parent) {
            is MicroJavaMethodDecl -> prettyPrint(parent, true)
            is MicroJavaClassDecl -> "class ${parent.identifier.name}"
            is MicroJavaFile -> "program ${(parent.childrenOfType<MicroJavaIdentifier>().first()).name}"
            else -> ""
        }

        return when (computeWith) {
            is MicroJavaVarDecl -> "Local variable ${(element as MicroJavaNamedElement).name} in scope $scopeName"
            is MicroJavaMethodDecl -> "Method ${prettyPrint(computeWith)} in scope $scopeName"
            is MicroJavaConstDecl -> "Constant ${computeWith.identifier.name} in scope $scopeName"
            is MicroJavaFormPars -> "Parameter ${computeWith.identifierList.firstOrNull()?.name} in scope $scopeName"
            else -> "something else with scope $scopeName"
        }
    }

    private fun prettyPrint(element: MicroJavaMethodDecl, includeType: Boolean = false): String {
        val prefix = if (includeType) {
            element.type?.text ?: ""
        } else ""
        return prefix + " " + element.identifier.name + "(${prettyPrintType(element.formPars)})"
    }

    private fun prettyPrintType(element: MicroJavaFormPars?): String {
        if (element == null) {
            return ""
        }
        return element.typeList.joinToString(",") { it.text ?: "" }
    }

    private fun prettyPrint(element: MicroJavaVarDecl): String {
        return element.identifierList.joinToString(",") { it.name ?: "" }
    }
}