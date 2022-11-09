plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 33
    buildToolsVersion = "${rootProject.extra["buildToolsVersion"]}"

    defaultConfig {
        applicationId = "com.softwaret.mvi.android"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        dataBinding = true
        compose = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    composeOptions {
        kotlinCompilerExtensionVersion ="${rootProject.extra["compilerVer"]}"
    }

    namespace = "co.proexe"
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlinVersion"]}")
    //hilt
    implementation("com.google.dagger:hilt-android:${rootProject.extra["hiltPluginVersion"]}")
    implementation("androidx.hilt:hilt-navigation-compose:${rootProject.extra["hiltCompilerVersion"]}")
    kapt("com.google.dagger:hilt-android-compiler:${rootProject.extra["hiltPluginVersion"]}")
    kapt("androidx.hilt:hilt-compiler:${rootProject.extra["hiltCompilerVersion"]}")

    implementation("com.google.android.material:material:1.7.0")
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("com.google.accompanist:accompanist-swiperefresh:${rootProject.extra["swipeRefresh"]}")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("androidx.activity:activity-ktx:${rootProject.extra["activityVersion"]}")

    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    testImplementation("junit:junit:4.13.2")

    implementation("androidx.core:core-ktx:${rootProject.extra["coreKtxVersion"]}")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${rootProject.extra["lifecycleVersion"]}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${rootProject.extra["lifecycleVersion"]}")

    implementation("androidx.compose.ui:ui:${rootProject.extra["composeVersion"]}")
    implementation("androidx.compose.ui:ui-tooling:${rootProject.extra["composeVersion"]}")
    implementation("androidx.compose.ui:ui-util:${rootProject.extra["composeVersion"]}")

    implementation("androidx.compose.foundation:foundation:${rootProject.extra["composeVersion"]}")
    implementation("androidx.compose.foundation:foundation-layout:${rootProject.extra["composeVersion"]}")

    implementation("com.google.android.material:compose-theme-adapter:${rootProject.extra["composeThemeAdapterVersion"]}")
    implementation("androidx.compose.material:material:${rootProject.extra["composeVersion"]}")
    implementation("androidx.compose.material:material-icons-core:${rootProject.extra["composeVersion"]}")
    implementation("androidx.compose.material:material-icons-extended:${rootProject.extra["composeVersion"]}")

    implementation("androidx.activity:activity-compose:${rootProject.extra["composeActivityVersion"]}")
    implementation("androidx.compose.runtime:runtime:${rootProject.extra["composeVersion"]}")
    implementation("androidx.compose.runtime:runtime-livedata:${rootProject.extra["composeVersion"]}")
    implementation("androidx.compose.runtime:runtime-rxjava2:${rootProject.extra["composeVersion"]}")
    implementation("androidx.navigation:navigation-compose:${rootProject.extra["composeNavigationVersion"]}")
    implementation("androidx.constraintlayout:constraintlayout-compose:${rootProject.extra["composeConstraintLayoutVersion"]}")
}