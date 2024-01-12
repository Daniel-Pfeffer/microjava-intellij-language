package org.example.microjava.reference

import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.diagnostic.Logger
import com.intellij.psi.*
import org.example.microjava.MicroJavaUtil
import org.example.microjava.psi.MicroJavaDesignator
import org.example.microjava.psi.MicroJavaFile
import org.example.microjava.psi.MicroJavaNamedElement

class MicroJavaDesignatorReference(
    element: MicroJavaDesignator,
) : PsiReferenceBase<MicroJavaDesignator>(element), PsiPolyVariantReference {

    // only simple name -> should be implemented better for field access
    private val name = element.firstChild.text


    companion object {
        val LOG = Logger.getInstance(MicroJavaDesignatorReference::class.java)
    }

    override fun resolve(): PsiElement? {
        val methods = multiResolve(false)
        return methods.firstOrNull()?.element
    }

    override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
        if (myElement is MicroJavaNamedElement) {
            return emptyArray()
        }
        val reference = MicroJavaUtil.findBacktrackingReferenceToDesignator(
            myElement,
            name
        ) ?: return emptyArray()

        LOG.info("Resolving $element reference to ${(reference as? MicroJavaNamedElement)?.name}")

        return arrayOf(PsiElementResolveResult(reference))
    }

    override fun getVariants(): Array<Any> {
        val references = MicroJavaUtil.findReferenceToNamedElement(
            myElement.containingFile as MicroJavaFile,
            name
        )

        return references.map {
            LookupElementBuilder.create(it)
                .withTypeText(it.containingFile.name)
        }.toTypedArray()
    }

    override fun isReferenceTo(element: PsiElement): Boolean {
        return multiResolve(false)
            .any { it.element == element }
    }
}