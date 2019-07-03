package it.unibo.alchemist.ide.project

import com.intellij.ide.util.projectWizard.WizardContext
import com.intellij.platform.ProjectTemplate
import com.intellij.platform.ProjectTemplatesFactory
import com.intellij.platform.templates.BuilderBasedTemplate
import icons.Icons
import io.github.classgraph.ClassGraph
import javax.swing.Icon

class AlchemistTemplatesFactory : ProjectTemplatesFactory() {

    companion object {

        private const val ALCHEMIST_GROUP = "Alchemist"

        private const val TEMPLATES_FOLDER = "it/unibo/alchemist/templates"

    }

    // Get the templates from the resources.
    override fun createTemplates(group: String?, context: WizardContext): Array<ProjectTemplate> =
        // Get all the resources from the templates directory.
        ClassGraph().whitelistPackages(TEMPLATES_FOLDER).scan().allResources.map { resource ->
            // Get sub-folders of templates directory
            resource.path.substring(0, resource.path.indexOf("/", TEMPLATES_FOLDER.length + 1))
            // Get the builders from those sub-folders anc create the templates.
        }.toSet().map { BuilderBasedTemplate(TemplateGradleModuleBuilder(it)) }.toTypedArray()

    override fun getGroups(): Array<String> = arrayOf(ALCHEMIST_GROUP)

    override fun getGroupIcon(group: String): Icon = Icons.ALCHEMIST_LOGO

}