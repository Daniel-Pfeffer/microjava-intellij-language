package org.example.microjava

import com.intellij.openapi.fileTypes.LanguageFileType

object MicroJavaFileType : LanguageFileType(MicroJavaLanguage) {
    override fun getName() = "MicroJava"
    override fun getDescription() = "MicroJava language file"
    override fun getDefaultExtension() = "mj"
    override fun getIcon() = MicroJavaIcons.FILE
}