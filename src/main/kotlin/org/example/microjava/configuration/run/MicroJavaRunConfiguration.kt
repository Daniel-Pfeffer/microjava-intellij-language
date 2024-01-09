package org.example.microjava.configuration.run

import com.intellij.execution.Executor
import com.intellij.execution.configurations.*
import com.intellij.execution.process.ProcessHandler
import com.intellij.execution.process.ProcessHandlerFactory
import com.intellij.execution.process.ProcessTerminatedListener
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.options.SettingsEditor
import com.intellij.openapi.project.Project

class MicroJavaRunConfiguration(
    project: Project,
    factory: ConfigurationFactory,
    name: String,
) : RunConfigurationBase<MicroJavaRunConfigurationOptions>(
    project,
    factory,
    name,
) {

    override fun getOptions(): MicroJavaRunConfigurationOptions {
        return super.getOptions() as MicroJavaRunConfigurationOptions
    }


    var programName: String?
        get() = options.programName
        set(value) {
            options.programName = value
        }

    override fun getState(executor: Executor, environment: ExecutionEnvironment): RunProfileState {
        return object : CommandLineState(environment) {
            override fun startProcess(): ProcessHandler {
                val commandLine = GeneralCommandLine(programName)

                return ProcessHandlerFactory.getInstance()
                    .createColoredProcessHandler(commandLine)
                    .also {
                        ProcessTerminatedListener.attach(it)
                    }
            }
        }
    }

    override fun getConfigurationEditor(): SettingsEditor<out RunConfiguration> {
        return MicroJavaRunConfigurationEditor()
    }

}
