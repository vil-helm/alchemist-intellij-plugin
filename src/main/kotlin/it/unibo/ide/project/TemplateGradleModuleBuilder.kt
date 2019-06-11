package it.unibo.ide.project

import org.jetbrains.plugins.gradle.service.project.wizard.GradleModuleBuilder
import javax.swing.Icon

abstract class TemplateGradleModuleBuilder : GradleModuleBuilder() {

    abstract override fun getBuilderId(): String

    abstract override fun getPresentableName(): String

    abstract override fun getDescription(): String

    abstract override fun getNodeIcon(): Icon

}