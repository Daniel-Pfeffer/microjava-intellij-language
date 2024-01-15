package org.example.microjava.reference

import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.diagnostic.Logger
import com.intellij.psi.*
import com.intellij.psi.util.PsiTreeUtil
import org.example.microjava.MicroJavaUtil
import org.example.microjava.psi.*

class MicroJavaSimpleNameReference(
    element: MicroJavaReferencable,
) : PsiReferenceBase<MicroJavaReferencable>(element), PsiPolyVariantReference {

    // only simple name -> should be implemented better for field access
    private val name = element.firstChild.text


    companion object {
        val LOG = Logger.getInstance(MicroJavaSimpleNameReference::class.java)
    }

    override fun resolve(): PsiElement? {
        val methods = multiResolve(false)
        return methods.firstOrNull()?.element
    }

    override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
        if (myElement is MicroJavaNamedElement) {
            return emptyArray()
        }
        val prevReferanceable = PsiTreeUtil.getPrevSiblingOfType(myElement, MicroJavaReferencable::class.java)
        if (prevReferanceable != null) {
            // is field access
            val referenceElement = prevReferanceable.references.firstOrNull()?.resolve() ?: return emptyArray()
            when (val parent = referenceElement.parent) {
                is MicroJavaVarDecl -> {
                    val type = parent.type
                    val actualClass = MicroJavaUtil.findClassFromType(type)
                        ?: return emptyArray()
                    val field = MicroJavaUtil.findFieldReferenceOfClass(actualClass, name) ?: return emptyArray()
                    return arrayOf(PsiElementResolveResult(field))
                }

                is MicroJavaFormPars -> {
                    val type =
                        PsiTreeUtil.getPrevSiblingOfType(referenceElement, MicroJavaType::class.java) ?: return emptyArray()
                    val actualClass = MicroJavaUtil.findClassFromType(type)
                        ?: return emptyArray()
                    val field = MicroJavaUtil.findFieldReferenceOfClass(actualClass, name) ?: return emptyArray()

                    return arrayOf(PsiElementResolveResult(field))
                }
            }
        }
        val references = MicroJavaUtil.findBacktrackingReferenceForReferenceable(
            myElement,
            name
        ) ?: return emptyArray()

        LOG.info("Resolving $element reference to ${(references as? MicroJavaNamedElement)?.name}")

        return arrayOf(PsiElementResolveResult(references))
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