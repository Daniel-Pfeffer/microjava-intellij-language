package org.example.microjava.psi.impl

import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import kotlin.reflect.KClass

object MicroJavaPsiUtil {
    fun getSiblingOfType(element: PsiElement, forward: Boolean = true, type: KClass<out PsiElement>): PsiElement? {
        var sibling = nextSibling(element, forward)
        while (sibling != null && sibling::class != type) {
            sibling = nextSibling(sibling, forward)
        }
        return sibling
    }

    inline fun <reified T : PsiElement> getSiblingOfType(element: PsiElement, forward: Boolean = true): PsiElement? {
        return getSiblingOfType(element, forward, T::class)
    }

    private fun nextSibling(element: PsiElement, forward: Boolean = true): PsiElement? {
        return if (forward) {
            element.nextSibling
        } else {
            element.prevSibling
        }
    }
}