object Libs {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${CommonVersions.kotlin}"

    const val materialComponents = "com.google.android.material:material:1.4.0"

    object Apollo {
        const val runtime = "com.apollographql.apollo:apollo-runtime:${CommonVersions.apollo}"
        const val android = "com.apollographql.apollo:apollo-android-support:${CommonVersions.apollo}"
        const val coroutines = "com.apollographql.apollo:apollo-coroutines-support:${CommonVersions.apollo}"
    }

    object AndroidX {

        const val activityCompose = "androidx.activity:activity-compose:1.3.1"
        const val appCompat = "androidx.appcompat:appcompat:1.3.1"
        const val coreKtx = "androidx.core:core-ktx:1.6.0"

        object Compose {
            const val ui = "androidx.compose.ui:ui:${CommonVersions.compose}"
            const val material = "androidx.compose.material:material:${CommonVersions.compose}"
            const val uiTooling = "androidx.compose.ui:ui-tooling-preview:${CommonVersions.compose}"
        }

        object Lifecycle {
            private const val runtimeVersion = "2.4.0-alpha01"
            const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$runtimeVersion"
        }
    }
}
