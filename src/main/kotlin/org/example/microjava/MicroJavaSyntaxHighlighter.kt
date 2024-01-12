package org.example.microjava

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import org.example.microjava.psi.MicroJavaSingleValueTokens
import org.example.microjava.psi.MicroJavaTokenSets
import org.example.microjava.psi.MicroJavaTypes

class MicroJavaSyntaxHighlighter : SyntaxHighlighterBase() {

    companion object {
        val COMMENT =
            createTextAttributesKey("MICROJAVA_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT)
        val KEYWORD = createTextAttributesKey("MICROJAVA_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)
        val IDENTIFIER =
            createTextAttributesKey("MICROJAVA_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER)
        val CHAR_CONST =
            createTextAttributesKey("MICROJAVA_CHAR_CONST", DefaultLanguageHighlighterColors.STRING)
        val PARENTHESIS =
            createTextAttributesKey("MICROJAVA_PARENTHESIS", DefaultLanguageHighlighterColors.PARENTHESES)
        val BRAKET = createTextAttributesKey("MICROJAVA_BRAKET", DefaultLanguageHighlighterColors.BRACKETS)
        val BRACE = createTextAttributesKey("MICROJAVA_BRACE", DefaultLanguageHighlighterColors.BRACES)
        val NUMBER = createTextAttributesKey("MICROJAVA_NUMBER", DefaultLanguageHighlighterColors.NUMBER)
        val OPERATOR =
            createTextAttributesKey("MICROJAVA_OPERATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN)
        val SEMICOLON =
            createTextAttributesKey("MICROJAVA_SEMICOLON", DefaultLanguageHighlighterColors.SEMICOLON)
        val DOT = createTextAttributesKey("MICROJAVA_DOT", DefaultLanguageHighlighterColors.DOT)
        val COMMA = createTextAttributesKey("MICROJAVA_COMMA", DefaultLanguageHighlighterColors.COMMA)
        val BAD_CHARACTER = createTextAttributesKey("MICROJAVA_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)

        val FUNCTION_CALL =
            createTextAttributesKey("MICROJAVA_FUNCTION_CALL", DefaultLanguageHighlighterColors.FUNCTION_CALL)

        val FUNCTION_DECLARATION =
            createTextAttributesKey("MICROJAVA_FUNCTION_DECL", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION)
        val CLASS_DECLARATION =
            createTextAttributesKey("MICROJAVA_CLASS_DECL", DefaultLanguageHighlighterColors.CLASS_NAME)
        val CLASS_TYPE =
            createTextAttributesKey("MICROJAVA_CLASS_REF", DefaultLanguageHighlighterColors.CLASS_NAME)
        val LOCAL_VARS =
            createTextAttributesKey("MICROJAVA_LOCAL_VARS", DefaultLanguageHighlighterColors.LOCAL_VARIABLE)
        val GLOBAL_VARS =
            createTextAttributesKey("MICROJAVA_GLOBAL_VARS", DefaultLanguageHighlighterColors.INSTANCE_FIELD)
        val CONSTANT = createTextAttributesKey("MICROJAVA_CONSTANT", DefaultLanguageHighlighterColors.CONSTANT)

        private val BAD_CHAR_KEYS = arrayOf(BAD_CHARACTER)
        private val COMMENT_KEYS = arrayOf(COMMENT)
        private val KEYWORD_KEYS = arrayOf(KEYWORD)
        private val IDENTIFIER_KEYS = arrayOf(IDENTIFIER)
        private val CHAR_CONST_KEYS = arrayOf(CHAR_CONST)
        private val PARENTHESIS_KEYS = arrayOf(PARENTHESIS)
        private val BRAKET_KEYS = arrayOf(BRAKET)
        private val BRACE_KEYS = arrayOf(BRACE)
        private val NUMBER_KEYS = arrayOf(NUMBER)
        private val OPERATOR_KEYS = arrayOf(OPERATOR)
        private val SEMICOLON_KEYS = arrayOf(SEMICOLON)
        private val DOT_KEYS = arrayOf(DOT)
        private val COMMA_KEYS = arrayOf(COMMA)
        private val EMPTY_KEYS = emptyArray<TextAttributesKey>()

    }

    override fun getHighlightingLexer(): Lexer {
        return MicroJavaLexerAdapter()
    }

    override fun getTokenHighlights(token: IElementType): Array<TextAttributesKey> {
        if (token == MicroJavaSingleValueTokens.DOT) {
            return DOT_KEYS
        }
        if (token == MicroJavaSingleValueTokens.SEMICOLON) {
            return SEMICOLON_KEYS
        }
        if (token == MicroJavaSingleValueTokens.COMMA) {
            return COMMA_KEYS
        }
        if (token == MicroJavaSingleValueTokens.LPAREN || token == MicroJavaSingleValueTokens.RPAREN) {
            return PARENTHESIS_KEYS
        }
        if (token == MicroJavaSingleValueTokens.LBRACE || token == MicroJavaSingleValueTokens.RBRACE) {
            return BRACE_KEYS
        }
        if (token == MicroJavaSingleValueTokens.LBRACKET || token == MicroJavaSingleValueTokens.RBRACKET) {
            return BRAKET_KEYS
        }
        if (token in MicroJavaTokenSets.OPERATORS) {
            return OPERATOR_KEYS
        }

        if (token == MicroJavaSingleValueTokens.CHAR_CONST) {
            return CHAR_CONST_KEYS
        }

        if (token == MicroJavaTypes.NUMBER) {
            return NUMBER_KEYS
        }

        if (token == MicroJavaTypes.IDENT) {
            return IDENTIFIER_KEYS
        }

        if (token == MicroJavaTypes.COMMENT) {
            return COMMENT_KEYS
        }

        if (token == MicroJavaTypes.CHAR) {
            return CHAR_CONST_KEYS
        }

        if (token in MicroJavaTokenSets.KEYWORDS) {
            return KEYWORD_KEYS
        }

        if (token == TokenType.BAD_CHARACTER) {
            return BAD_CHAR_KEYS
        }
        return EMPTY_KEYS
    }
}