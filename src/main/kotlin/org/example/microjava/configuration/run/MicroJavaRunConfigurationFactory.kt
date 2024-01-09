package org.example.microjava.configuration.run

import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.ConfigurationType
import com.intellij.execution.configurations.RunConfiguration
import com.intellij.openapi.components.BaseState
import com.intellij.openapi.project.Project

class MicroJavaRunConfigurationFactory(type: ConfigurationType) : ConfigurationFactory(type) {
    override fun createTemplateConfiguration(project: Project): RunConfiguration {
        return MicroJavaRunConfiguration(project, this, "MicroJava")
    }

    override fun getId(): String {
        return MicroJavaRunConfigurationType.ID
    }

    override fun getOptionsClass(): Class<out BaseState> {
        return MicroJavaRunConfigurationOptions::class.java
    }
}
