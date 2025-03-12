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
    implementation(fileTree(
        "dir" to "libs",
        "include" to listOf("*.jar", "*.aar")
    ))
    implementation (libs.coreKtx)
    implementation (libs.appCompat)
    implementation (libs.recyclerView)
    implementation (libs.material)
    implementation (libs.multidex)
    annotationProcessor (libs.roomCompiler)
    annotationProcessor (libs.roomKtx)
    implementation (libs.roomRuntime)
    implementation (libs.roomRxJava)
    implementation (libs.kotlinReflect)
    implementation (libs.playServicesAuth)
    implementation( libs.firebaseConfig)
    implementation( libs.firebaseCore)
    implementation (libs.firebaseMessaging)
    implementation (libs.firebaseAnalytics)
    implementation (libs.firebaseAnalytics)

    testImplementation (libs.junit)
    androidTestImplementation (libs.junitAndroidX)
    androidTestImplementation (libs.espressoCore)
    androidTestImplementation (libs.mockito)
    testImplementation (libs.mockito)

    api(project(":core-helper"))
    api(project(":data-helper"))
    api(project(":api-helper"))
}