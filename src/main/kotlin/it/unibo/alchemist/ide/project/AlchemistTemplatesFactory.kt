package it.unibo.alchemist.ide.project

import com.intellij.ide.util.projectWizard.WizardContext
import com.intellij.platform.ProjectTemplate
import com.intellij.platform.ProjectTemplatesFactory
import com.intellij.platform.templates.BuilderBasedTemplate
import icons.Icons
import org.reflections.Reflections
import org.reflections.scanners.ResourcesScanner
import java.util.regex.Pattern
import javax.swing.Icon

class AlchemistTemplatesFactory : ProjectTemplatesFactory() {

    companion object {

        private const val ALCHEMIST_GROUP = "Alchemist"

        private const val TEMPLATES_FOLDER = "it/unibo/alchemist/templates"

    }

    // Get the templates from the resources.
    override fun createTemplates(group: String?, context: WizardContext): Array<ProjectTemplate> =
        // Get all the resources from the templates directory.
        Reflections(TEMPLATES_FOLDER, ResourcesScanner()).getResources(Pattern.compile(".*")).map {
            // Get sub-folders of templates directory
            it.substring(0, it.indexOf("/", TEMPLATES_FOLDER.length + 1))
            // Get the builders from those sub-folders anc create the templates.
        }.toSet().map { BuilderBasedTemplate(TemplateGradleModuleBuilder(it)) }.toTypedArray()

    override fun getGroups(): Array<String> = arrayOf(ALCHEMIST_GROUP)

}