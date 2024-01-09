package org.example.microjava.psi

object MicroJavaKeywordTokens {
    @JvmField
    val PROGRAM = MicroJavaTokenType("program")
    @JvmField
    val CLASS_TYPE = MicroJavaTokenType("class")
    @JvmField
    val IF = MicroJavaTokenType("if")
    @JvmField
    val ELSE = MicroJavaTokenType("else")
    @JvmField
    val WHILE = MicroJavaTokenType("while")
    @JvmField
    val READ = MicroJavaTokenType("read")
    @JvmField
    val PRINT = MicroJavaTokenType("print")
    @JvmField
    val RETURN = MicroJavaTokenType("return")
    @JvmField
    val BREAK = MicroJavaTokenType("break")
    @JvmField
    val VOID = MicroJavaTokenType("void")
    @JvmField
    val FINAL = MicroJavaTokenType("final")
    @JvmField
    val NEW = MicroJavaTokenType("new")

    @JvmField
    val INT = MicroJavaTokenType("int")
    @JvmField
    val CHAR = MicroJavaTokenType("char")
}