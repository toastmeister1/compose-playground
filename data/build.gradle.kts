plugins {
    id ("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Configs.COMPILE_SDK
}

dependencies {
    implementation(project(":domain"))
    implementation(Dependency.Dagger.HILT_ANDROID)
    kapt(Dependency.Dagger.HILT_COMPILER)

    implementation(Dependency.Kotlin.COROUTINE_CORE)

    api(Dependency.Room.ROOM)
    implementation(Dependency.Room.KTX)
    kapt(Dependency.Room.COMPILER)

    api(Dependency.Square.RETROFIT)
    implementation(Dependency.Square.RETROFIT_MOSHI)
    implementation(Dependency.Square.OKHTTP_LOGGING)
    implementation(Dependency.Square.MOSHI)
    implementation(Dependency.Square.MOSHI_CODEGEN)
}