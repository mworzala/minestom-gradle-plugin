package com.minestom.gradle

class MinestomExtension {
    // Minestom
    def version = "-SNAPSHOT"
    def mainClass = null

    // External plugins
    def applyShadow = true
    def configureJar = true

    // Metadata
    def versionWarning = true
}
