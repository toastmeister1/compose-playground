object Dependency {
    const val CORE = "androidx.core:core-ktx:1.7.0"
    const val APP_COMPAT = "androidx.appcompat:appcompat:1.4.1"

    const val INJECT = "javax.inject:javax.inject:1"

    object Kotlin {
        private const val version = "1.5.21"
        private const val coroutineVersion = "1.5.1"

        const val GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"

        const val COROUTINE_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion"
        const val COROUTINE_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion"
    }

    object Compose {
        private const val compose_version = "1.0.1"
        const val UI = "androidx.compose.ui:ui:$compose_version"
        const val MATERIAL = "androidx.compose.material:material:$compose_version"
        const val TOOL_PREVIEW = "androidx.compose.ui:ui-tooling:$compose_version"
        const val JUNIT = "androidx.compose.ui:ui-test-junit4:$compose_version"
        const val TOOL_UI = "androidx.compose.ui:ui-tooling:$compose_version"
        const val ACTIVITY = "androidx.activity:activity-compose:1.4.0"
    }

    object Dagger {
        private const val dagger_version = "2.40.1"
        const val HILT_ANDROID = "com.google.dagger:hilt-android:$dagger_version"
        const val HILT_COMPILER = "com.google.dagger:hilt-compiler:$dagger_version"
        const val HILT_GRADLE_PLUGIN = "com.google.dagger:hilt-android-gradle-plugin:$dagger_version"
        const val HILT_LIFECYCLE_VIEWMODEL = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"
        const val HILT_NAVIGATION_COMPOSE = "androidx.hilt:hilt-navigation-compose:1.0.0-alpha03"
    }

    object Room {
        private const val room_version = "2.4.0"
        const val ROOM = "androidx.room:room-runtime:$room_version"
        const val KTX = "androidx.room:room-ktx:$room_version"
        const val COMPILER = "androidx.room:room-compiler:$room_version"
    }

    object Ktx {
        const val LIFECYCLE = "androidx.lifecycle:lifecycle-runtime-ktx:2.4.0"
    }

    object Google {
        const val MATERIAL = "com.google.android.material:material:1.5.0"
    }

    object Square {
        const val RETROFIT = "com.squareup.retrofit2:retrofit:2.9.0"
        const val RETROFIT_MOSHI = "com.squareup.retrofit2:converter-moshi:2.9.0"
        const val OKHTTP_LOGGING = "com.squareup.okhttp3:logging-interceptor:4.9.1"

        const val MOSHI = "com.squareup.moshi:moshi:1.13.0"
        const val MOSHI_CODEGEN = "com.squareup.moshi:moshi-kotlin-codegen:1.13.0"
    }

    object Test {
        const val JUNIT = "junit:junit:4.+"
        const val ANDROID_JUNIT = "androidx.test.ext:junit:1.1.3"
        const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:3.4.0"
    }

}

