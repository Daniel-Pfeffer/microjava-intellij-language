package org.example.microjava.psi

object MicroJavaSingleValueTokens {
    @JvmField
    val PLUS = MicroJavaTokenType("+")
    @JvmField
    val MINUS = MicroJavaTokenType("-")
    @JvmField
    val MUL = MicroJavaTokenType("*")
    @JvmField
    val DIV = MicroJavaTokenType("/")
    @JvmField
    val MOD = MicroJavaTokenType("%")
    @JvmField
    val EXP = MicroJavaTokenType("**")

    @JvmField
    val INC = MicroJavaTokenType("++")
    @JvmField
    val DEC = MicroJavaTokenType("--")

    @JvmField
    val EQ = MicroJavaTokenType("==")
    @JvmField
    val NEQ = MicroJavaTokenType("!=")
    @JvmField
    val GT = MicroJavaTokenType(">")
    @JvmField
    val GTE = MicroJavaTokenType(">=")
    @JvmField
    val LT = MicroJavaTokenType("<")
    @JvmField
    val LTE = MicroJavaTokenType("<=")

    @JvmField
    val AND = MicroJavaTokenType("&&")
    @JvmField
    val OR = MicroJavaTokenType("||")

    @JvmField
    val LPAREN = MicroJavaTokenType("(")
    @JvmField
    val RPAREN = MicroJavaTokenType(")")
    @JvmField
    val LBRACE = MicroJavaTokenType("{")
    @JvmField
    val RBRACE = MicroJavaTokenType("}")
    @JvmField
    val LBRACKET = MicroJavaTokenType("[")
    @JvmField
    val RBRACKET = MicroJavaTokenType("]")

    @JvmField
    val ASSIGN = MicroJavaTokenType("=")
    @JvmField
    val PLUS_ASSIGN = MicroJavaTokenType("+=")
    @JvmField
    val MINUS_ASSIGN = MicroJavaTokenType("-=")
    @JvmField
    val MUL_ASSIGN = MicroJavaTokenType("*=")
    @JvmField
    val DIV_ASSIGN = MicroJavaTokenType("/=")
    @JvmField
    val MOD_ASSIGN = MicroJavaTokenType("%=")

    @JvmField
    val SEMICOLON = MicroJavaTokenType(";")
    @JvmField
    val COMMA = MicroJavaTokenType(",")
    @JvmField
    val DOT = MicroJavaTokenType(".")
    @JvmField
    val CHAR_CONST = MicroJavaTokenType("CHAR_CONST")
}