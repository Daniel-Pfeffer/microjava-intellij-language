package org.example.microjava.configuration.run

import com.intellij.execution.configurations.RunConfigurationOptions

class MicroJavaRunConfigurationOptions : RunConfigurationOptions() {
    private val _programName = string("")
        .provideDelegate(this, "scriptName")

    var programName: String?
        get() = _programName.getValue(this)
        set(value) {
            _programName.setValue(this, value)
        }
}
