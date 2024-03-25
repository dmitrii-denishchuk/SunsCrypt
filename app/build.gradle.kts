plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.com.google.dagger.hilt.android)
    alias(libs.plugins.com.google.devtools.ksp)
    alias(libs.plugins.org.jetbrains.dokka)
}

android {
    namespace = "ru.iji.sunscrypt"
    compileSdk = 34

    defaultConfig {
        applicationId = "ru.iji.sunscrypt"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
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

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":app:data"))
    implementation(project(":app:domain"))
    implementation(project(":app:presentation"))
    implementation(libs.hilt.android)
    implementation(libs.moshi.kotlin)
    implementation(libs.converter.moshi)
    ksp(libs.hilt.android.compiler)
    ksp(libs.moshi.kotlin.codegen)

    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.espresso.core)
}