plugins {
    id("com.android.application")
    id("kotlin-android")
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
    implementation(project(":apollo"))

    implementation(Libs.AndroidX.appCompat)

    implementation(Libs.materialComponents)
    implementation(Libs.AndroidX.coreKtx)
    implementation(Libs.AndroidX.activityCompose)

    implementation(Libs.AndroidX.Compose.ui)
    implementation(Libs.AndroidX.Compose.material)
    implementation(Libs.AndroidX.Compose.uiTooling)

    implementation(Libs.AndroidX.Lifecycle.runtime)
}
