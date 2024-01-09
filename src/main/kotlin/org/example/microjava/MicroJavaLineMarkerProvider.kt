package org.example.microjava

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder
import com.intellij.icons.AllIcons
import com.intellij.psi.PsiElement
import org.example.microjava.psi.MicroJavaMethodDecl

class MicroJavaLineMarkerProvider : RelatedItemLineMarkerProvider() {
    override fun collectNavigationMarkers(
        element: PsiElement,
        result: MutableCollection<in RelatedItemLineMarkerInfo<*>>,
    ) {
        if (element is MicroJavaMethodDecl) {
            val methodName = element.identifier.name
            if (methodName == "main") {
                val item = NavigationGutterIconBuilder
                    .create(AllIcons.Actions.Execute)
                    .setTargets(element)
                    .setTooltipText("Run main method")
                    .createLineMarkerInfo(element.identifier.nameIdentifier!!)
                result.add(item)
            }
        }
    }
}