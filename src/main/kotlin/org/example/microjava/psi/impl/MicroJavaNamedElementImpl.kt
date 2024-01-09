package org.example.microjava.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import org.example.microjava.psi.MicroJavaNamedElement

abstract class MicroJavaNamedElementImpl(node: ASTNode) :
    ASTWrapperPsiElement(node), MicroJavaNamedElement