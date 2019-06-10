package it.unibo.ide.project

import com.intellij.ide.util.projectWizard.ModuleBuilder
import com.intellij.ide.util.projectWizard.WizardContext
import com.intellij.openapi.extensions.ExtensionPointName
import com.intellij.platform.ProjectTemplate
import com.intellij.platform.ProjectTemplatesFactory
import com.intellij.platform.templates.BuilderBasedTemplate

class AlchemistTemplatesFactory : ProjectTemplatesFactory() {

    companion object {
        private const val ALCHEMIST_GROUP = "Alchemist"

        // Get template builders from the extensions of the <plugin.xml> file and convert them into templates.
        private val TEMPLATES = ExtensionPointName.create<ModuleBuilder>("it.unibo.alchemist.templateBuilder")
            .extensions.map { BuilderBasedTemplate(it) }
    }

    override fun createTemplates(group: String?, context: WizardContext): Array<ProjectTemplate> =
        if (group in groups) TEMPLATES.toTypedArray() else throw  IllegalArgumentException("The passed group is not correct for this templates factory")

    override fun getGroups(): Array<String> = arrayOf(ALCHEMIST_GROUP)

}