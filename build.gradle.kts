import com.android.build.gradle.internal.tasks.factory.registerTask

plugins {
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.android.application) apply false
}

tasks.registerTask("clean", Delete::class.java)