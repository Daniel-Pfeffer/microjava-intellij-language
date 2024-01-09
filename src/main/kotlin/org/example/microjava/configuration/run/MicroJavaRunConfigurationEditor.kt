package org.example.microjava.configuration.run

import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.options.SettingsEditor
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.intellij.util.ui.FormBuilder
import javax.swing.JComponent
import javax.swing.JPanel

class MicroJavaRunConfigurationEditor : SettingsEditor<MicroJavaRunConfiguration>() {

    val panel: JPanel
    val programPathField: TextFieldWithBrowseButton

    init {
        programPathField = TextFieldWithBrowseButton()
        programPathField.addBrowseFolderListener(
            "Select MicroJava Program",
            "Select MicroJava program to run",
            null,
            FileChooserDescriptorFactory.createSingleFileDescriptor("mj")
        )
        panel = FormBuilder.createFormBuilder()
            .addLabeledComponent("MicroJava program", programPathField)
            .panel
    }

    override fun resetEditorFrom(configuration: MicroJavaRunConfiguration) {
        programPathField.text = configuration.programName.orEmpty()
    }

    override fun applyEditorTo(configuration: MicroJavaRunConfiguration) {
        configuration.programName = programPathField.text
    }

    override fun createEditor(): JComponent {
        return panel
    }
}
