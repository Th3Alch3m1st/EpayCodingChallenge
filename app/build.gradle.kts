plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
    id("com.github.ben-manes.versions") version "0.39.0"
}

android {
    compileSdk = BuildConfig.compileSdkVersion
    buildToolsVersion = BuildConfig.buildToolsVersion

    defaultConfig {
        applicationId = BuildConfig.applicationID
        minSdk = BuildConfig.minSdkVersion
        targetSdk = BuildConfig.targetSdkVersion
        versionCode = BuildConfig.versionCode
        versionName = BuildConfig.versionName

        testInstrumentationRunner = "com.epay.codingchallenge.utils.HiltAppTestRunner"
    }

    buildFeatures {
        dataBinding = true
    }


    buildTypes {
        getByName("debug") {

        }
        getByName("release") {
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
}

dependencies {
    implementation(KotlinDependencies.kotlinStd)
    implementation(KotlinDependencies.coreKtx)

    implementation(AndroidXSupportDependencies.appCompat)
    implementation(AndroidXSupportDependencies.constraintLayout)
    implementation(AndroidXSupportDependencies.fragmentKtx)
    implementation(AndroidXSupportDependencies.lifecycleRuntimeKTX)

    //material
    implementation(MaterialDesignDependencies.materialDesign)

    //glide
    implementation(Libraries.glide)
    kapt(Libraries.glideKapt)

    //coroutine
    implementation(Libraries.coroutineAndroid)

    //hilt
    implementation(Libraries.hilt)
    kapt(Libraries.hiltAnnotationProcessor)
    implementation(Libraries.hiltNavigation)

    //For instrumentation tests
    androidTestImplementation(Libraries.hiltInstrumentation)
    kaptAndroidTest(Libraries.hiltInsAnnotationProcessor)

    //For local unit tests
    implementation(Libraries.hiltUnitTest)
    kapt(Libraries.hiltUnitTestAnnotationProcessor)

    //sdp-ssp
    implementation(Libraries.sdp)
    implementation(Libraries.ssp)

    //For instrumentation tests
    implementation(Libraries.hiltInstrumentation)
    kapt(Libraries.hiltInsAnnotationProcessor)

    implementation(Libraries.retrofit)
    implementation(Libraries.retrofitMoshiConverter)
    implementation(Libraries.retrofitRxAdapter)

    implementation(Libraries.moshi)
    kapt(Libraries.moshiKotlinCodeGen)

    //testing
    testImplementation(TestingDependencies.junit)
    testImplementation(TestingDependencies.assertj)
    testImplementation(TestingDependencies.androidArchCoreTesting)
    testImplementation(TestingDependencies.coroutineTest)
    testImplementation (TestingDependencies.mockitoKotlin)
    testImplementation (TestingDependencies.mockitoInline)
    androidTestImplementation(TestingDependencies.androidArchCoreTesting)
    debugImplementation (TestingDependencies.fragmentTesting)

    androidTestImplementation(TestingDependencies.androidExtJunit)
    androidTestImplementation(TestingDependencies.androidEspressoCore)
    androidTestImplementation(TestingDependencies.mockitoAndroid)
    androidTestImplementation(TestingDependencies.androidTestRunner)
    androidTestImplementation(TestingDependencies.androidTestRule)
    androidTestImplementation (TestingDependencies.navigationTesting)
    androidTestImplementation (TestingDependencies.espressoContrib)
    androidTestImplementation (TestingDependencies.espressoIntent)
    androidTestImplementation (TestingDependencies.espressoConcurrent)
    implementation (TestingDependencies.espressoIdling)

    implementation (Libraries.stetho)
    implementation (Libraries.stethoOkhttp)
    implementation (Libraries.stethoJSRhino)

    implementation (Libraries.lottie)

    implementation(project(":network"))
}