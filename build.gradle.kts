import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    jacoco
    id("io.gitlab.arturbosch.detekt")
    id("org.jlleitschuh.gradle.ktlint")
    id("org.jetbrains.dokka")
    id("org.jetbrains.intellij")
    id("org.danilopianini.git-sensitive-semantic-versioning")
}

repositories {
    jcenter()
}

dependencies {
    implementation("io.github.classgraph:classgraph:_")
}

intellij {
    setPlugins("java", "gradle", "gradle-java")
}

gitSemVer {
    version = computeGitSemVer()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.jacocoTestReport {
    reports {
        xml.isEnabled = true
    }
}
