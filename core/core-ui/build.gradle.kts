plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.core_ui"
    compileSdk = 36

    defaultConfig {
        minSdk = 26
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    val composeBom = platform("androidx.compose:compose-bom:2024.05.00")
    implementation(composeBom)
    implementation(libs.androidx.compose.material.icons.core)
    implementation(libs.androidx.compose.ui.ui)
    implementation(libs.navigation.compose)
    implementation(project(":core:core-navigation"))
    implementation(project(":core:core-domain"))
    implementation(libs.coil.compose)
    implementation(project(":core:core-util"))
    implementation(project(":core:core-viewModel"))
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.runtime)
    implementation(libs.androidx.activity.compose.v190)
    debugImplementation(libs.androidx.compose.ui.tooling)
}