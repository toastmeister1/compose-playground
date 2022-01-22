plugins {
    id ("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("kotlinx-serialization")
}

android {
    compileSdk = Configs.COMPILE_SDK

    defaultConfig {
        buildConfigField("String", "TMDB_API_KEY", getApiKey("TMDB_API_KEY"))
    }
}

fun getApiKey(propertyName: String): String {
    return com.android.build.gradle.internal.cxx.configure.gradleLocalProperties(rootDir).getProperty(propertyName) ?: ""
}

dependencies {
    implementation(project(":domain"))
    implementation(Dependency.Dagger.HILT_ANDROID)
    kapt(Dependency.Dagger.HILT_COMPILER)

    implementation(Dependency.Kotlin.COROUTINE_CORE)
    implementation(Dependency.Kotlin.SERIALIZATION)

    api(Dependency.Room.ROOM)
    implementation(Dependency.Room.KTX)
    kapt(Dependency.Room.COMPILER)

    api(Dependency.Square.RETROFIT)
    implementation(Dependency.Square.RETROFIT_MOSHI)
    implementation(Dependency.Square.OKHTTP_LOGGING)
    implementation(Dependency.Square.SERIALIZATION_CONVERTER)
}