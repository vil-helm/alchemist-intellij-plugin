package it.unibo.ide.project

import com.intellij.ide.util.projectWizard.WizardContext
import com.intellij.platform.ProjectTemplate
import com.intellij.platform.ProjectTemplatesFactory

class AlchemistTemplatesFactory : ProjectTemplatesFactory() {

    companion object {
        private const val ALCHEMIST_GROUP = "Alchemist"
    }

    override fun createTemplates(group: String?, context: WizardContext): Array<ProjectTemplate> = TODO()

    override fun getGroups(): Array<String> = arrayOf(ALCHEMIST_GROUP)
}