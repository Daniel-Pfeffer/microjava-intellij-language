package org.example.microjava

import com.intellij.lexer.FlexAdapter

class MicroJavaLexerAdapter : FlexAdapter(MicroJavaLexer(null))