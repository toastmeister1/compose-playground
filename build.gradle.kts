// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.2.2")
        classpath(Dependency.Kotlin.GRADLE_PLUGIN)
        classpath(Dependency.Kotlin.SERIALIZATION_PLUGIN)
        classpath(Dependency.Dagger.HILT_GRADLE_PLUGIN)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}