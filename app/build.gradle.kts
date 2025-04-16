plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-parcelize")
    id("com.google.devtools.ksp")
    id ("androidx.navigation.safeargs")
}

android {
    namespace = "com.example.hipica"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.hipica"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures{
        dataBinding = true
        viewBinding = true
    }
    buildToolsVersion = "35.0.0"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit.v115)
    androidTestImplementation(libs.androidx.espresso.core.v351)

    // ROOM
    // val roomVersion = "2.6.1"
    implementation (libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    // Coroutines
    implementation(libs.kotlinx.coroutines.android)
    implementation (libs.androidx.room.ktx)

    // val fragment_version = "1.8.6"
    // Kotlin
    implementation(libs.androidx.fragment.ktx)
    // Navigation
    // val navVersion = "2.8.9 "
    // implementation(libs.androidx.navigation.fragment.ktx)
    // implementation(libs.androidx.navigation.ui.ktx)
    // val nav_version = "2.8.9" // Or the latest version
    // implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    // implementation("androidx.navigation:navigation-ui-ktx:$nav_version")
    // Life Cycle Arch
    // val lifecycleVersion = "2.8.7"
    // ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    // LiveData
    implementation(libs.androidx.lifecycle.livedata.ktx)
    // Annotation processor
    ksp(libs.androidx.lifecycle.compiler)
}