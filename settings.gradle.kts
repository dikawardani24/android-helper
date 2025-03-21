@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

val modules = arrayOf(
    ":api-helper",
    ":ui-helper",
    ":core-helper",
    ":database-helper"
)
include (*modules)
rootProject.name = "Android-Helper"
