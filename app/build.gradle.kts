plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.daggerHiltAndroid)
}

android {
    namespace = "com.net.movieapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.net.movieapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"


        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        /*def apiKey = project.hasProperty('api_key_token') ? project.property('api_key_token') : ""
        buildConfigField "String", "API_KEY_TOKEN", "\"${apiKey}\""

        def localProperties = new Properties()
        localProperties.load(new FileInputStream(rootProject.file("local.properties")))*/
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //Navigation
    implementation(libs.androidx.navigation.compose)

    //Room
//    implementation (libs.androidx.room)
//    kapt (libs.annotation.room)
    implementation (libs.androidx.room.paging)
    implementation (libs.androidx.room.ktx)
    kapt (libs.kapt.room.compiler)

    //Hilt
    implementation (libs.com.google.dagger.hilt)
    implementation (libs.androidx.hilt.navigation.compose)
    kapt (libs.kapt.hilt.compiler)
    kapt (libs.kapt.androidx.hilt.compiler)

    //Retrofit
    implementation (libs.com.squareup.retrofit)
    implementation (libs.com.squareup.converter.gson)
    implementation (libs.com.squareup.okhttp)
    implementation (libs.com.squareup.logging.interceptor)

    //Coil
    implementation (libs.io.coil.coil.compose)

    // Extended Icons
    implementation (libs.androidx.compose.material.icon.extended)

    // System UI Controller
//    implementation (libs.com.google.accopanist.systemuicontroller)
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.27.0")

}