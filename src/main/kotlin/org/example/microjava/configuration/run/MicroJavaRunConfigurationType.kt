package org.example.microjava.configuration.run

import com.intellij.execution.configurations.ConfigurationTypeBase
import com.intellij.icons.AllIcons
import com.intellij.openapi.util.NotNullLazyValue

class MicroJavaRunConfigurationType : ConfigurationTypeBase(
    ID,
    "MicroJava",
    "Run MicroJava program",
    NotNullLazyValue.createValue { AllIcons.Nodes.Console }
) {
    init {
        addFactory(MicroJavaRunConfigurationFactory(this))
    }

    companion object {
        const val ID = "MicroJavaRunConfigurationType"
    }
}