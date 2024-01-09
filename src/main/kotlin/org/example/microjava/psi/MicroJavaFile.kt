package org.example.microjava.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import org.example.microjava.MicroJavaFileType
import org.example.microjava.MicroJavaLanguage

class MicroJavaFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, MicroJavaLanguage) {
    override fun getFileType(): FileType {
        return MicroJavaFileType
    }

    override fun toString(): String {
        return "MicroJava File"
    }
}