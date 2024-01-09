package org.example.microjava.psi

import com.intellij.psi.tree.TokenSet

interface MicroJavaTokenSets {
    companion object {
        val COMMENTS = TokenSet.create(MicroJavaTypes.COMMENT)
        val IDENTIFER = TokenSet.create(MicroJavaTypes.IDENT)
        val KEYWORDS = TokenSet.create(
            MicroJavaKeywordTokens.PROGRAM,
            MicroJavaKeywordTokens.CLASS_TYPE,
            MicroJavaKeywordTokens.IF,
            MicroJavaKeywordTokens.ELSE,
            MicroJavaKeywordTokens.WHILE,
            MicroJavaKeywordTokens.READ,
            MicroJavaKeywordTokens.PRINT,
            MicroJavaKeywordTokens.RETURN,
            MicroJavaKeywordTokens.BREAK,
            MicroJavaKeywordTokens.VOID,
            MicroJavaKeywordTokens.FINAL,
            MicroJavaKeywordTokens.NEW,
            MicroJavaKeywordTokens.INT,
            MicroJavaKeywordTokens.CHAR,
        )
        val OPERATORS = TokenSet.create(
            MicroJavaSingleValueTokens.PLUS,
            MicroJavaSingleValueTokens.MINUS,
            MicroJavaSingleValueTokens.MUL,
            MicroJavaSingleValueTokens.DIV,
            MicroJavaSingleValueTokens.MOD,
            MicroJavaSingleValueTokens.INC,
            MicroJavaSingleValueTokens.DEC,
            MicroJavaSingleValueTokens.EQ,
            MicroJavaSingleValueTokens.NEQ,
            MicroJavaSingleValueTokens.GT,
            MicroJavaSingleValueTokens.GTE,
            MicroJavaSingleValueTokens.LT,
            MicroJavaSingleValueTokens.LTE,
            MicroJavaSingleValueTokens.AND,
            MicroJavaSingleValueTokens.OR,
            MicroJavaSingleValueTokens.ASSIGN,
            MicroJavaSingleValueTokens.PLUS_ASSIGN,
            MicroJavaSingleValueTokens.MINUS_ASSIGN,
            MicroJavaSingleValueTokens.MUL_ASSIGN,
            MicroJavaSingleValueTokens.DIV_ASSIGN,
            MicroJavaSingleValueTokens.MOD_ASSIGN,
            MicroJavaSingleValueTokens.SEMICOLON,
            MicroJavaSingleValueTokens.COMMA,
            MicroJavaSingleValueTokens.DOT,
        )
        val CHAR_CONST = TokenSet.create(MicroJavaTypes.CHAR)
        val NUMBER = TokenSet.create(MicroJavaTypes.NUMBER)

        val LITERAL = TokenSet.create(MicroJavaTypes.CHAR, MicroJavaTypes.NUMBER)

        val ALL_PAREN = TokenSet.create(
            MicroJavaSingleValueTokens.LPAREN,
            MicroJavaSingleValueTokens.RPAREN,
            MicroJavaSingleValueTokens.LBRACE,
            MicroJavaSingleValueTokens.RBRACE,
            MicroJavaSingleValueTokens.LBRACKET,
            MicroJavaSingleValueTokens.RBRACKET,
        )
    }
}