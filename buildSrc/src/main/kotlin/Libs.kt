object Libs {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${CommonVersions.kotlin}"

    const val materialComponents = "com.google.android.material:material:1.4.0"

    object Apollo {
        const val runtime = "com.apollographql.apollo3:apollo-runtime:${CommonVersions.apollo}"
        const val testingSupport = "com.apollographql.apollo3:apollo-testing-support:${CommonVersions.apollo}"
    }

    object AndroidX {

        const val activityCompose = "androidx.activity:activity-compose:1.3.1"
        const val appCompat = "androidx.appcompat:appcompat:1.3.1"
        const val coreKtx = "androidx.core:core-ktx:1.6.0"

        object Accompanist {
            private const val accompanistVersion = "0.24.7-alpha"
            const val animatedNavigation = "com.google.accompanist:accompanist-navigation-animation:$accompanistVersion"
        }

        object Compose {
            const val ui = "androidx.compose.ui:ui:${CommonVersions.compose}"
            const val material = "androidx.compose.material:material:${CommonVersions.compose}"
            const val uiTooling = "androidx.compose.ui:ui-tooling:${CommonVersions.compose}"
        }

        object Dagger {
            const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:1.0.0-alpha03"

            const val hiltAndroid = "com.google.dagger:hilt-android:${CommonVersions.hilt}"
            const val hiltAndroidCompilerKapt = "com.google.dagger:hilt-android-compiler:${CommonVersions.hilt}"

            private const val androidxHiltVersion = "1.0.0-alpha03"
            const val hiltLifecycleViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:$androidxHiltVersion"
            const val hiltCompilerKapt = "androidx.hilt:hilt-compiler:$androidxHiltVersion"
        }

        object Lifecycle {
            private const val version = "2.4.0-alpha03"
            const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
            const val viewModelSavedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:$version"
        }

        object Navigation {
            const val compose = "androidx.navigation:navigation-compose:2.4.0-alpha07"
        }
    }

    object Coil {
        const val compose = "io.coil-kt:coil-compose:1.3.2"
    }
}
