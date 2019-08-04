package it.unibo.alchemist.ide.project

import com.intellij.ide.util.projectWizard.ModuleWizardStep
import com.intellij.ide.util.projectWizard.WizardContext
import com.intellij.openapi.module.Module
import com.intellij.openapi.roots.ui.configuration.ModulesProvider
import com.intellij.openapi.util.IconLoader
import icons.Icons
import io.github.classgraph.ClassGraph
import org.jetbrains.plugins.gradle.service.project.wizard.GradleModuleBuilder
import org.jetbrains.plugins.gradle.util.GradleConstants
import java.io.File
import javax.swing.Icon

class AlchemistTemplateModuleBuilder(private val templateDirectoryPath: String) : GradleModuleBuilder() {

    override fun getBuilderId(): String = """alchemist.template.builder [$presentableName]"""

    // This function returns the template name from the resource or an empty string.
    override fun getPresentableName(): String = readResourceText("""$templateDirectoryPath/name.txt""")

    // This function returns the template description from the resource or an empty string.
    override fun getDescription(): String = readResourceText("""$templateDirectoryPath/description.html""")

    // This function returns the template icon from the resource or a default icon.
    override fun getNodeIcon(): Icon = with("""$templateDirectoryPath/icon.svg""") {
        if (getResource(this) != null) IconLoader.getIcon(this) else Icons.ALCHEMIST_LOGO
    }

    // This override removes the project id step from the wizard.
    override fun createWizardSteps(
        wizardContext: WizardContext,
        modulesProvider: ModulesProvider
    ): Array<ModuleWizardStep> = super.createWizardSteps(wizardContext, modulesProvider).copyOfRange(1, 2)

    // This override copies the template files in the new module.
    override fun setupModule(module: Module) = super.setupModule(module).also {
        // Store the module directory.
        val rootDirectoryPath = contentEntryPath ?: return

        // Delete groovy files in the project.
        File(rootDirectoryPath, GradleConstants.DEFAULT_SCRIPT_NAME).delete()
        File(rootDirectoryPath, GradleConstants.SETTINGS_FILE_NAME).delete()

        // The path to the template contents.
        val templateContentsPath = """$templateDirectoryPath/contents"""

        // Get all the resources from the template directory.
        ClassGraph().whitelistPackages(templateContentsPath).scan().allResources
            // For each resource...
            .forEach { resource ->
                // ...create the destination file...
                File(rootDirectoryPath, resource.path.removePrefix(templateContentsPath)).apply {
                    // ...(and all the necessary directories)...
                    parentFile.mkdirs()
                    // ...then copy the data into the file.
                    resource.use { resourceData ->
                        outputStream().use { file ->
                            resourceData.open().copyTo(file)
                        }
                    }
                }
            }
    }

    // This function makes it easier to obtain a resource from a string path.
    private fun getResource(path: String) = this::class.java.classLoader.getResource(path)

    // This function returns the text from a resource or an empty string.
    private fun readResourceText(path: String) = getResource(path)?.readText() ?: ""

}