//run this to get updated dependencies ./gradlew dependencyUpdates -DoutputDir=VersionReport
object Versions {
    const val gradlePlugin = "7.0.3"

    const val kotlin = "1.5.21"
    const val coreKtx = "1.7.0"

    const val appCompat = "1.4.0"
    const val constraintLayout = "2.1.2"
    const val fragmentKtx = "1.4.0"
    const val navigation = "2.4.0-beta02"
    const val lifecycleRuntimeKTX = "2.4.0"

    const val paging = "3.1.0"

    const val material = "1.4.0"

    const val hilt = "2.40.1"
    const val hiltNavigation = "1.0.0"

    const val coroutine = "1.5.2"

    const val retrofit = "2.9.0"
    const val loggingInterceptor = "3.1.0"
    const val moshi = "1.12.0"
    const val gson = "2.8.9"
    const val stetho = "1.6.0"

    const val sdpssp = "1.0.6"

    const val lottie = "4.2.1"

    const val jUnit = "4.13.2"
    const val extJunit = "1.1.3"
    const val espressoCore = "3.2.0"
    const val mockWebServer = "4.9.1"
    const val mockito = "3.8.0"
    const val mockitoKotlin = "4.0.0"
    const val mockitoInline = "3.2.4"
    const val assertj = "3.21.0"
    const val androidxTestRunner = "1.4.0"
    const val allOpen = "1.5.21"
    const val androidArchCore = "2.1.0"
    const val androidXTestRule = "1.4.0"
    const val navigationTesting = "2.4.0-beta02"
    const val espressoCContribAndIntent = "3.2.0"
    const val espressoIdlingAndConcurrent = "3.2.0"
}

/**
 * To define plugins
 */
object BuildPlugins {
    val androidBuildTools by lazy { "com.android.tools.build:gradle:${Versions.gradlePlugin}" }
    val kotlinPlugins by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}" }
    val hiltPlugin by lazy { "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}" }
    val navigationSafeArg by lazy { "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}" }
    val allOpenPlugin by lazy { "org.jetbrains.kotlin:kotlin-allopen:${Versions.allOpen}" }
}

/**
 * To define dependencies
 */
object KotlinDependencies {
    val kotlinStd by lazy { "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}" }
    val coreKtx by lazy { "androidx.core:core-ktx:${Versions.coreKtx}" }
}

object AndroidXSupportDependencies {
    val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.appCompat}" }
    val constraintLayout by lazy { "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}" }
    val fragmentKtx by lazy { "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}" }
    val navigationFragmentKtx by lazy { "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}" }
    val navigationUIKtx by lazy { "androidx.navigation:navigation-ui-ktx:${Versions.navigation}" }
    val lifecycleRuntimeKTX by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntimeKTX}" }

    val pagingRuntime by lazy { "androidx.paging:paging-runtime-ktx:${Versions.paging}" }
    val pagingRxJavaSupport by lazy { "androidx.paging:paging-rxjava3:${Versions.paging}" }

}

object MaterialDesignDependencies {
    val materialDesign by lazy { "com.google.android.material:material:${Versions.material}" }
}

object TestingDependencies {
    val junit by lazy { "junit:junit:${Versions.jUnit}" }
    val androidExtJunit by lazy { "androidx.test.ext:junit:${Versions.extJunit}" }
    val androidTestRunner by lazy { "androidx.test:runner:${Versions.androidxTestRunner}" }
    val androidTestRule by lazy { "androidx.test:rules:${Versions.androidXTestRule}" }
    val androidEspressoCore by lazy { "androidx.test.espresso:espresso-core:${Versions.espressoCore}" }
    val mockWebServer by lazy { "com.squareup.okhttp3:mockwebserver:${Versions.mockWebServer}" }
    val mockitoKotlin by lazy { "org.mockito.kotlin:mockito-kotlin:${Versions.mockitoKotlin}" }
    val mockitoInline by lazy { "org.mockito:mockito-inline:${Versions.mockitoInline}" }
    val mockitoAndroid by lazy { "org.mockito:mockito-android:${Versions.mockito}" }
    val assertj by lazy { "org.assertj:assertj-core:${Versions.assertj}" }
    val androidArchCoreTesting by lazy { "androidx.arch.core:core-testing:${Versions.androidArchCore}" }
    val fragmentTesting by lazy { "androidx.fragment:fragment-testing:${Versions.fragmentKtx}" }
    val navigationTesting by lazy { "androidx.navigation:navigation-testing:${Versions.navigationTesting}" }
    val espressoContrib by lazy { "androidx.test.espresso:espresso-contrib:${Versions.espressoCContribAndIntent}" }
    val espressoIntent by lazy { "androidx.test.espresso:espresso-intents:${Versions.espressoCContribAndIntent}" }
    val espressoConcurrent by lazy { "androidx.test.espresso.idling:idling-concurrent:${Versions.espressoIdlingAndConcurrent}" }
    val espressoIdling by lazy { "androidx.test.espresso:espresso-idling-resource:${Versions.espressoIdlingAndConcurrent}" }
    val coroutineTest by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutine}" }
}

object Libraries {
    val hilt by lazy { "com.google.dagger:hilt-android:${Versions.hilt}" }
    val hiltAnnotationProcessor by lazy { "com.google.dagger:hilt-android-compiler:${Versions.hilt}" }
    val hiltNavigation by lazy { "androidx.hilt:hilt-navigation-fragment:${Versions.hiltNavigation}" }

    //For instrumentation tests
    val hiltInstrumentation by lazy { "com.google.dagger:hilt-android-testing:${Versions.hilt}" }
    val hiltInsAnnotationProcessor by lazy { "com.google.dagger:hilt-android-testing:${Versions.hilt}" }

    //For local unit tests
    val hiltUnitTest by lazy { "com.google.dagger:hilt-android-testing:${Versions.hilt}" }
    val hiltUnitTestAnnotationProcessor by lazy { "com.google.dagger:hilt-compiler:${Versions.hilt}" }

    val coroutine by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine}" }
    val coroutineAndroid by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}" }

    //logging interceptor
    val loggingInterceptor by lazy { "com.github.ihsanbal:LoggingInterceptor:${Versions.loggingInterceptor}" }

    //network interceptor
    val stetho by lazy { "com.facebook.stetho:stetho:${Versions.stetho}" }
    val stethoOkhttp by lazy { "com.facebook.stetho:stetho-okhttp3:${Versions.stetho}" }
    val stethoJSRhino by lazy { "com.facebook.stetho:stetho-js-rhino:${Versions.stetho}" }

    //moshi
    val moshi by lazy { "com.squareup.moshi:moshi:${Versions.moshi}" }
    val moshiKotlinCodeGen by lazy { "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}" }

    val gson by lazy { "com.google.code.gson:gson:${Versions.gson}" }

    //retrofit
    val retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofit}" }
    val retrofitGsonConverter by lazy { "com.squareup.retrofit2:converter-gson:${Versions.retrofit}" }
    val retrofitMoshiConverter by lazy { "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}" }
    val retrofitRxAdapter by lazy { "com.squareup.retrofit2:adapter-rxjava3:${Versions.retrofit}" }

    val sdp by lazy { "com.intuit.sdp:sdp-android:${Versions.sdpssp}" }
    val ssp by lazy { "com.intuit.ssp:ssp-android:${Versions.sdpssp}" }

    val lottie by lazy { "com.airbnb.android:lottie:${Versions.lottie}" }
}