package com.minestom.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.jvm.tasks.Jar
import org.gradle.jvm.toolchain.JavaToolchainService
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class MinestomGradlePlugin implements Plugin<Project> {
    private def logger

    @Override
    void apply(Project project) {
        logger = LoggerFactory.getLogger("Minestom")

        project.extensions.add("minestom", MinestomExtension as Object)

        project.task("minestomDebug", type: DebugTask)

        project.afterEvaluate {
            def conf = project.extensions.getByType(MinestomExtension)

            // Minestom
            configureMinestom(project)

            // ShadowJar
            if (conf.applyShadow) {
                project.pluginManager.apply('com.github.johnrengelman.shadow')
            }
        }
    }

    void configureMinestom(Project project) {
        def conf = project.extensions.getByType(MinestomExtension)

        def targetVersion = conf.version
        def latestVersion = HttpUtil.getLatestCommitId()

        // Use latest instead of -SNAPSHOT
        if (targetVersion == "-SNAPSHOT")
            targetVersion = latestVersion

        // Warn if behind
        if (targetVersion != latestVersion && conf.versionWarning)
            logger.warn("Using an outdated Minestom version (current = ${targetVersion}, latest = ${latestVersion}")

        project.repositories {
            mavenCentral()

            maven { url 'https://repo.spongepowered.org/maven' }
            maven { url 'https://repo.velocitypowered.com/snapshots/' }
            maven { url 'https://libraries.minecraft.net' }
            maven { url 'https://jitpack.io' }
        }

        project.dependencies {
            compile 'com.github.Minestom:Minestom:' + conf.version
        }
    }
}
