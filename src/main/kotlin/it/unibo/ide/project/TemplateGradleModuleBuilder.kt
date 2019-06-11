package it.unibo.ide.project

import com.intellij.ide.util.projectWizard.ModuleWizardStep
import com.intellij.ide.util.projectWizard.WizardContext
import com.intellij.openapi.module.Module
import com.intellij.openapi.roots.ui.configuration.ModulesProvider
import org.jetbrains.plugins.gradle.service.project.wizard.GradleModuleBuilder
import org.jetbrains.plugins.gradle.util.GradleConstants
import org.reflections.Reflections
import org.reflections.scanners.ResourcesScanner
import java.io.File
import java.util.regex.Pattern
import javax.swing.Icon

abstract class TemplateGradleModuleBuilder(private val templateDirectoryPath: String) : GradleModuleBuilder() {

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
        // Delete groovy files in the project.
        File(rootDirectoryPath, GradleConstants.DEFAULT_SCRIPT_NAME).delete()
        File(rootDirectoryPath, GradleConstants.SETTINGS_FILE_NAME).delete()

        // Get all the resources from the template directory.
        Reflections(templateDirectoryPath, ResourcesScanner()).getResources(Pattern.compile(".*"))
            // Create a map that associates the relative path to the input stream of the resource.
            .associate { resourcesPath ->
                resourcesPath.removePrefix(templateDirectoryPath) to this::class.java.classLoader.getResourceAsStream(
                    resourcesPath
                )
                // For each resource, create the destination file (and all the necessary directories) then copy the data into it.
            }.forEach { (relativePath, data) ->
                File(rootDirectoryPath, relativePath).apply {
                    parentFile.mkdirs()
                    data.use {
                        outputStream().use { file ->
                            data.copyTo(file)
                        }
                    }
                }
            }
    }

}