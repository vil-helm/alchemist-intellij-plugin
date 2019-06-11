package it.unibo.ide.project

import com.intellij.ide.util.projectWizard.ModuleWizardStep
import com.intellij.ide.util.projectWizard.WizardContext
import com.intellij.openapi.module.Module
import com.intellij.openapi.roots.ui.configuration.ModulesProvider
import org.jetbrains.plugins.gradle.service.project.wizard.GradleModuleBuilder
import javax.swing.Icon

abstract class TemplateGradleModuleBuilder : GradleModuleBuilder() {

    abstract override fun getBuilderId(): String

    abstract override fun getPresentableName(): String

    abstract override fun getDescription(): String

    abstract override fun getNodeIcon(): Icon

    // This override removes the project id step from the wizard.
    override fun createWizardSteps(
        wizardContext: WizardContext,
        modulesProvider: ModulesProvider
    ): Array<ModuleWizardStep> = super.createWizardSteps(wizardContext, modulesProvider).copyOfRange(1, 2)

    // This override copies the template files in the new module.
    override fun setupModule(module: Module) = super.setupModule(module).apply {
        // Copy the template files.
        copyTemplateFilesTo(contentEntryPath ?: return)
    }

    // This function copies the template files to the created root directory.
    private fun copyTemplateFilesTo(rootDirectoryPath: String) {
        TODO()
    }

}