package org.example.microjava

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import org.example.microjava.parser.MicroJavaParser
import org.example.microjava.psi.MicroJavaFile
import org.example.microjava.psi.MicroJavaTokenSets
import org.example.microjava.psi.MicroJavaTypes

class MicroJavaParserDefinition : ParserDefinition {

    override fun createLexer(project: Project): Lexer {
        return MicroJavaLexerAdapter()
    }

    override fun createParser(project: Project): PsiParser {
        return MicroJavaParser()
    }

    override fun getFileNodeType(): IFileElementType {
        return FILE
    }

    override fun getCommentTokens(): TokenSet {
        return MicroJavaTokenSets.COMMENTS
    }

    override fun getStringLiteralElements(): TokenSet {
        return TokenSet.EMPTY
    }

    override fun createElement(node: ASTNode): PsiElement {
        return MicroJavaTypes.Factory.createElement(node)
    }

    override fun createFile(fileViewProvider: FileViewProvider): PsiFile {
        return MicroJavaFile(fileViewProvider)
    }
}

val FILE = IFileElementType(MicroJavaLanguage)