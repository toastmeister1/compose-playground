plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Configs.COMPILE_SDK

    defaultConfig {
        applicationId = Configs.APPLICATION_ID
        minSdk        = Configs.MIN_SDK
        targetSdk     = Configs.TARGET_SDK
        versionCode   = Configs.VERSION_CODE
        versionName   = Configs.VERSION_NAME

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Configs.COMPOSE_VERSION
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}



dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))

    implementation(Dependency.CORE)
    implementation(Dependency.APP_COMPAT)
    implementation(Dependency.Ktx.LIFECYCLE)

    implementation(Dependency.Google.MATERIAL)

    implementation(Dependency.Compose.UI)
    implementation(Dependency.Compose.MATERIAL)
    implementation(Dependency.Compose.TOOL_PREVIEW)
    implementation(Dependency.Compose.ACTIVITY)
    implementation(Dependency.Compose.COIL)
    implementation(Dependency.Compose.VIEWMODEL)
    implementation(Dependency.Compose.CONSTRAINT_LAYOUT)
    implementation(Dependency.Compose.NAVIGATION)
    androidTestImplementation(Dependency.Compose.JUNIT)
    debugImplementation(Dependency.Compose.TOOL_UI)

    implementation(Dependency.Dagger.HILT_ANDROID)
    implementation(Dependency.Dagger.HILT_LIFECYCLE_VIEWMODEL)
    implementation(Dependency.Dagger.HILT_NAVIGATION_COMPOSE)
    kapt(Dependency.Dagger.HILT_COMPILER)

    implementation(Dependency.Kotlin.COROUTINE_CORE)
    implementation(Dependency.Kotlin.COROUTINE_ANDROID)

    testImplementation(Dependency.Test.JUNIT)
    androidTestImplementation(Dependency.Test.ANDROID_JUNIT)
    androidTestImplementation(Dependency.Test.ESPRESSO_CORE)
}