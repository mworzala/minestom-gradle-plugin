package com.minestom.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class DebugTask extends DefaultTask {
    @TaskAction
    void run() {
        def conf = project.extensions.getByType(MinestomExtension)
        println()
        println("===== Minestom Gradle Plugin =====")
        println("Minestom: ${conf.version} (latest ${HttpUtil.getLatestCommitId()})")
        if (conf.applyShadow)
            println("ShadowJar: Automatic (version 6.1.0)")
        else println("ShadowJar: Manual")
    }


}