import dika.helper.gradle.ProjectConfig

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "dika.helper.android"

    defaultConfig {
        minSdk = ProjectConfig.MIN_SDK
        compileSdk = ProjectConfig.COMPILE_SDK
        testInstrumentationRunner = ProjectConfig.TEST_RUNNER
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = ProjectConfig.javaVersion
        targetCompatibility = ProjectConfig.javaVersion
    }
    kotlinOptions {
        jvmTarget = ProjectConfig.JVM_TARGET
    }
}

dependencies {

    implementation(libs.coreKtx)
    implementation(libs.appCompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.junitAndroidX)
    androidTestImplementation(libs.espressoCore)

    implementation(project(":ui-helper"))
    implementation(project(":database-helper"))
    implementation(project(":api-helper"))
}