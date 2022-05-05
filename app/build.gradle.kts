plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "xyz.stylianosgakis.practicegraphql"
        minSdk = 23
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = true
            setProguardFiles(
                listOf(
                    getDefaultProguardFile("proguard-android.txt"),
                    "proguard-rules.pro"
                )
            )
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
        renderScript = false
        aidl = false
    }

    composeOptions {
        kotlinCompilerExtensionVersion = CommonVersions.compose
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/AL2.0"
            excludes += "/META-INF/LGPL2.1"
        }
    }
}

dependencies {
    testImplementation("junit:junit:4.13.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.1")
    implementation("org.jetbrains.kotlinx:atomicfu:0.17.0")

    implementation(project(":apollo"))
    testImplementation(project(":apollo"))

    implementation(Libs.AndroidX.appCompat)

    implementation(Libs.materialComponents)
    implementation(Libs.AndroidX.coreKtx)
    implementation(Libs.AndroidX.activityCompose)

    implementation(Libs.AndroidX.Compose.ui)
    implementation(Libs.AndroidX.Compose.material)
    implementation(Libs.AndroidX.Compose.uiTooling)

    // Apollo
    implementation(Libs.Apollo.runtime)
    testImplementation(Libs.Apollo.testingSupport)
    androidTestImplementation(Libs.Apollo.testingSupport)

    // DI
    implementation(Libs.AndroidX.Dagger.hiltAndroid)
    kapt(Libs.AndroidX.Dagger.hiltAndroidCompilerKapt)
    implementation(Libs.AndroidX.Dagger.hiltLifecycleViewModel)
    kapt(Libs.AndroidX.Dagger.hiltCompilerKapt)

    // Navigation
    implementation(Libs.AndroidX.Navigation.compose)
    implementation(Libs.AndroidX.Dagger.hiltNavigationCompose)
    implementation(Libs.AndroidX.Accompanist.animatedNavigation)

    // Image Loading
    implementation(Libs.Coil.compose)

    implementation(Libs.AndroidX.Lifecycle.runtime)
    implementation(Libs.AndroidX.Lifecycle.viewModelSavedState)
}
