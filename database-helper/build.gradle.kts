import dika.helper.gradle.ProjectConfig

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "dika.helper.database"

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

    annotationProcessor (libs.roomCompiler)
    annotationProcessor (libs.roomKtx)
    api (libs.roomRuntime)
    api (libs.roomRxJava)
    api (libs.kotlinReflect)

    api(project(":core-helper"))

}