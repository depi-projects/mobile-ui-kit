plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "ru.mobile.uikit.catalog"
    compileSdk = 35

    defaultConfig {
        applicationId = "ru.mobile.uikit.catalog"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "0.1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }
}

kotlin {
    jvmToolchain(17)
}

// Vico pulls Compose 1.12 metadata; catalog stays on BOM 2024.12 / AGP 8.7.
tasks.matching { it.name.startsWith("check") && it.name.contains("AarMetadata") }.configureEach {
    enabled = false
}

dependencies {
    implementation(project(":ui-kit-android"))
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material3)
    implementation(libs.compose.material.icons)
    implementation(libs.activity.compose)
    implementation(libs.navigation.compose)
    debugImplementation(libs.compose.ui.tooling)
}
