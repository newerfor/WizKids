plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
}

android {
    namespace = "com.example.wizkids"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.wizkids"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":feature:feature-main"))
    implementation(project(":feature:feature-addNewOrChangeInfoChild"))
    implementation(project(":feature:feature-addNewOrChangeInfoUser"))
    implementation(project(":feature:feature-childInformation"))
    implementation(project(":feature:feature-userProfile"))
    implementation(project(":feature:feature-calendarScreen"))
    implementation(project(":feature:feature-upcomingDates"))
    implementation(project(":core:core-ui"))
    implementation(project(":core:core-data"))
    implementation(project(":core:core-domain"))
    implementation(project(":core:core-viewModel"))
    implementation(project(":core:core-navigation"))
    implementation(libs.converter.gson)
    implementation(libs.androidx.room.runtime.v261)
    implementation(libs.androidx.room.ktx.v261)
    implementation(libs.navigation.compose)
    kapt(libs.androidx.room.compiler.v261)
    kapt (libs.androidx.room.compiler)
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
    implementation(libs.coil.compose.v270)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.room.common.jvm)
    implementation(libs.room.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}