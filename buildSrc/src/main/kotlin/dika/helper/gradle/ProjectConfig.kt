package dika.helper.gradle

import org.gradle.api.JavaVersion

object ProjectConfig {
    const val COMPILE_SDK = 35
    const val MIN_SDK = 21
    val javaVersion = JavaVersion.VERSION_17
    const val JVM_TARGET = "17"

    const val TEST_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
}