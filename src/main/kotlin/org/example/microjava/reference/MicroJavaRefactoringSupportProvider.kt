package org.example.microjava.reference

import com.intellij.lang.refactoring.RefactoringSupportProvider
import com.intellij.psi.PsiElement
import org.example.microjava.psi.MicroJavaNamedElement

class MicroJavaRefactoringSupportProvider : RefactoringSupportProvider() {
    override fun isMemberInplaceRenameAvailable(element: PsiElement, context: PsiElement?): Boolean {
        return element.parent is MicroJavaNamedElement
    }
}