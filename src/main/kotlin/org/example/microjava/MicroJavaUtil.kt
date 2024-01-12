package org.example.microjava

import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.util.childrenOfType
import org.example.microjava.psi.*

object MicroJavaUtil {

    fun findSimpleType(microJavaFile: MicroJavaFile, name: String? = null): List<MicroJavaSimpleType> {
        return PsiTreeUtil.getChildrenOfType(microJavaFile, MicroJavaSimpleType::class.java)
            ?.filter { name == null || name == it.text }
            ?.map { it }
            ?: emptyList()
    }

    fun findReferenceToNamedElement(
        parentScope: MicroJavaMethodDecl,
        name: String? = null,
    ): List<PsiElement> {
        return findDesignatorsInBlock(parentScope.block)
            .filter { name == null || name == it.text }
    }

    fun findReferenceToClassDecl(
        element: PsiElement,
        name: String? = null,
    ): PsiElement? {
        val file = element.containingFile as MicroJavaFile

        return file.childrenOfType<MicroJavaClassDecl>().firstOrNull {
                it.identifier.name == name
            }?.identifier
    }

    fun findBacktrackingReferenceToDesignator(
        element: MicroJavaDesignator,
        name: String? = null,
    ): PsiElement? {
        // search for local occurrence
        val method = PsiTreeUtil.getParentOfType(element, MicroJavaMethodDecl::class.java)
        if (method != null) {
            val localVariables = method.varDeclList
            localVariables.forEach { varDecl ->
                findFromVarDecl(varDecl, name)?.let { return it }
            }
            val parametersInMethod = method.formPars
            if (parametersInMethod != null) {
                val formParsWhole = parametersInMethod.identifierList.firstOrNull { it.name == name }
                if (formParsWhole != null) {
                    return formParsWhole
                }
            }
        }
        // find for global scope
        val file = PsiTreeUtil.getParentOfType(element, MicroJavaFile::class.java)!!
        // find global vars
        PsiTreeUtil.getChildrenOfType(file, MicroJavaVarDecl::class.java)
            ?.forEach { varDecl ->
                findFromVarDecl(varDecl, name)?.let { return it }
            }

        // find global constants
        PsiTreeUtil.getChildrenOfType(file, MicroJavaConstDecl::class.java)
            ?.firstOrNull { it.identifier.name == name }
            ?.let { return it.identifier }

        // find global methods
        PsiTreeUtil.getChildrenOfType(file, MicroJavaMethodDecl::class.java)
            ?.firstOrNull { it.identifier.name == name }
            ?.let { return it.identifier }

        // find global class definitions
        PsiTreeUtil.getChildrenOfType(file, MicroJavaClassDecl::class.java)
            ?.firstOrNull { it.identifier.name == name }
            ?.let { return it.identifier }
        return null
    }

    private fun findFromVarDecl(varDecl: MicroJavaVarDecl, name: String?): PsiElement? {
        return varDecl.identifierList.firstOrNull { it.name == name }
    }

    fun findReferenceToNamedElement(
        parentScope: MicroJavaFile,
        name: String? = null,
    ): List<PsiElement> {
        return PsiTreeUtil.getChildrenOfType(parentScope, MicroJavaMethodDecl::class.java)
            ?.flatMap {
                findReferenceToNamedElement(it, name)
            } ?: emptyList()
    }

    private fun findDesignatorsInExpr(expr: MicroJavaExpr): List<MicroJavaDesignator> {
        return expr.termList.flatMap { term ->
            term.factorList.flatMap { factor ->
                if (factor.designator != null) {
                    listOf(factor.designator!!)
                } else if (factor.expr != null) {
                    findDesignatorsInExpr(factor.expr!!)
                } else emptyList()
            }
        }
    }

    private fun findDesignatorsInCondition(condition: MicroJavaCondition): List<MicroJavaDesignator> {
        return condition.condTermList.flatMap { condTerm ->
            condTerm.condFactList.flatMap { condFact ->
                condFact.exprList.flatMap { expr ->
                    findDesignatorsInExpr(expr)
                }
            }
        }
    }

    private fun findDesignatorsInBlock(block: MicroJavaBlock): List<MicroJavaDesignator> {
        return block.statementList.flatMap {
            findDesignatorsInStatement(it)
        }
    }

    private fun findDesignatorsInStatement(statement: MicroJavaStatement): List<MicroJavaDesignator> {
        val toReturn = mutableListOf<MicroJavaDesignator>()
        if (statement.expr != null) toReturn.addAll(findDesignatorsInExpr(statement.expr!!))
        if (statement.designator != null) toReturn.addAll(listOf(statement.designator!!))
        if (statement.condition != null) toReturn.addAll(findDesignatorsInCondition(statement.condition!!))
        if (statement.block != null) toReturn.addAll(findDesignatorsInBlock(statement.block!!))
        if (statement.statementList.isNotEmpty()) toReturn.addAll(statement.statementList.flatMap {
            findDesignatorsInStatement(it)
        })
        if (statement.actPars != null) toReturn.addAll(findDesignatorsInActPars(statement.actPars!!))
        return toReturn
    }

    private fun findDesignatorsInActPars(methodCall: MicroJavaActPars): List<MicroJavaDesignator> {
        return methodCall.exprList.flatMap { expr ->
            findDesignatorsInExpr(expr)
        }
    }
}