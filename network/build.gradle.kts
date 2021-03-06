plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = BuildConfig.compileSdkVersion

    defaultConfig {
        minSdk = BuildConfig.minSdkVersion
        targetSdk = BuildConfig.targetSdkVersion


        testInstrumentationRunner = BuildConfig.testRunner
    }

    buildTypes {
        getByName("debug"){
            buildConfigField("String", "AUTH_TOKEN", "\"78a07164952e030a671b9350f648cd70\"")
            buildConfigField("String", "BASE_URL", "\"https://api.openweathermap.org\"")
            buildConfigField("String", "ICON_URL", "\"https://openweathermap.org/img/wn\"")
        }
        getByName("release") {
            buildConfigField("String", "AUTH_TOKEN", "\"78a07164952e030a671b9350f648cd70\"")
            buildConfigField("String", "BASE_URL", "\"https://api.openweathermap.org\"")
            buildConfigField("String", "ICON_URL", "\"https://openweathermap.org/img/wn\"")
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

    //hilt
    implementation(Libraries.hilt)
    kapt(Libraries.hiltAnnotationProcessor)

    //For instrumentation tests
    implementation(Libraries.hiltInstrumentation)
    kapt(Libraries.hiltInsAnnotationProcessor)

    //For local unit tests
    implementation(Libraries.hiltUnitTest)
    kapt(Libraries.hiltUnitTestAnnotationProcessor)

    implementation(Libraries.retrofit)
    implementation(Libraries.retrofitMoshiConverter)

    implementation(Libraries.moshi)
    kapt(Libraries.moshiKotlinCodeGen)

    implementation(Libraries.loggingInterceptor) {
        this.exclude("org.json", "json")
    }

    //coroutine
    implementation(Libraries.coroutineAndroid)

    //Stetho https://github.com/facebook/stetho
    implementation (Libraries.stetho)
    implementation (Libraries.stethoOkhttp)
    implementation (Libraries.stethoJSRhino)

    testImplementation(TestingDependencies.coroutineTest)

    //testing
    testImplementation(TestingDependencies.junit)
    testImplementation(TestingDependencies.mockWebServer)
    testImplementation(TestingDependencies.assertj)
    testImplementation (TestingDependencies.mockitoKotlin)
    testImplementation (TestingDependencies.mockitoInline)
}