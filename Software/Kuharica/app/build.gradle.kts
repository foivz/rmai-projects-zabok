plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.devtools.ksp")
    id("kotlin-android")
    id ("kotlin-parcelize")
}

android {

    buildToolsVersion = "34.0.0"
    namespace = "com.example.kuharica"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.kuharica"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.activity)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    /*implementation(libs.androidx.core.ktx)
       implementation(libs.androidx.appcompat)
       implementation(libs.material)
       implementation(libs.androidx.activity)
       implementation(libs.androidx.constraintlayout)
       implementation(libs.androidx.room.common)
       testImplementation(libs.junit)
       androidTestImplementation(libs.androidx.junit)
       androidTestImplementation(libs.androidx.espresso.core)*/
    val room_version = "2.6.1"
    //implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk7:8.7")
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    ksp("androidx.room:room-compiler:$room_version")
    implementation ("androidx.room:room-runtime:$room_version")
    implementation ("androidx.room:room-ktx:$room_version")

    annotationProcessor ("androidx.room:room-compiler:$room_version")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")

}
/*
task("testClasses")*/
