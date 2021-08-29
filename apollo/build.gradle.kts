plugins {
    id("com.android.library")
    id("kotlin-android")
    id("com.apollographql.apollo")
}

apollo {
    generateKotlinModels.set(true)
}

android {
    compileSdk = 31

    defaultConfig {
        minSdk = 23
        targetSdk = 30
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        buildConfig = false
        viewBinding = false
        dataBinding = false
        aidl = false
        renderScript = false
        resValues = false
        shaders = false
    }
}

dependencies {
    implementation(Libs.kotlin)

    api(Libs.Apollo.runtime)
    api(Libs.Apollo.android)
    api(Libs.Apollo.coroutines)
}
