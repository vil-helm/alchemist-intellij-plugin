package it.unibo.alchemist.ide.project

import com.intellij.ide.util.projectWizard.ModuleWizardStep
import com.intellij.ide.util.projectWizard.WizardContext
import com.intellij.openapi.module.Module
import com.intellij.openapi.roots.ui.configuration.ModulesProvider
import com.intellij.openapi.util.IconLoader
import org.jetbrains.plugins.gradle.service.project.wizard.GradleModuleBuilder
import org.jetbrains.plugins.gradle.util.GradleConstants
import org.reflections.Reflections
import org.reflections.scanners.ResourcesScanner
import java.io.File
import java.util.regex.Pattern
import javax.swing.Icon

class TemplateGradleModuleBuilder(templateDirectoryPath: String) : GradleModuleBuilder() {

    // This value represents the path to the file that contains the template name.
    private val templateNamePath = """$templateDirectoryPath/name.txt"""
    // This value represents the path to the file that contains the template description.
    private val templateDescriptionPath = """$templateDirectoryPath/description.html"""
    // This value represents the path to the template icon.
    private val templateIconPath = """$templateDirectoryPath/icon.svg"""
    // This value represents the path to the template contents.
    private val templateContentsPath = """$templateDirectoryPath/contents/"""

    override fun getBuilderId(): String = """alchemist.template.builder [$presentableName]"""

    // This function returns the template name from the resource or an empty string.
    override fun getPresentableName(): String = templateNamePath.asResourceURL()?.readText() ?: ""

    // This function returns the template description from the resource or an empty string.
    override fun getDescription(): String = templateDescriptionPath.asResourceURL()?.readText() ?: ""

    // This function returns the template icon from the resource or a default icon.
    override fun getNodeIcon(): Icon = IconLoader.getIcon(templateIconPath) // TODO: Add missing icon check

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
        Reflections(templateContentsPath, ResourcesScanner()).getResources(Pattern.compile(".*"))
            // Create a map that associates the relative path to the input stream of the resource.
            .associate { resourcesPath ->
                resourcesPath.removePrefix(templateContentsPath) to resourcesPath.asResourceStream()
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

    // This function makes it easier to obtain a resource from a string.
    private fun String.asResourceURL() = this@TemplateGradleModuleBuilder::class.java.classLoader.getResource(this)

    // This function makes it easier to obtain a resource as a stream from a string.
    private fun String.asResourceStream() =
        this@TemplateGradleModuleBuilder::class.java.classLoader.getResourceAsStream(this)

}