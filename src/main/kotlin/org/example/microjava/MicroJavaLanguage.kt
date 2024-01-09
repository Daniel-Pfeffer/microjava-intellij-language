package org.example.microjava

import com.intellij.lang.Language

object MicroJavaLanguage : Language("MicroJava") {
    private fun readResolve(): Any = MicroJavaLanguage
}