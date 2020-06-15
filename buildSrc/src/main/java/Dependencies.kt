object Versions {
    const val kotlin = "1.3.72"

    const val activity = "1.1.0"
    const val android_gradle_plugin = "4.0.0"
    const val annotations = "1.0.0"
    const val appcompat = "1.2.0-alpha02"
    const val core = "1.2.0"
    const val arch_core = "2.1.0"
    const val atsl_core = "1.2.0"
    const val atsl_junit = "1.1.1"
    const val atsl_rules = "1.2.0"
    const val atsl_runner = "1.2.0"
    const val benchmark = "1.1.0-alpha01"
    const val cardview = "1.0.0"
    const val constraint_layout = "2.0.0-alpha2"
    const val coroutines = "1.3.7"
    const val dagger = "2.16"
    const val espresso = "3.2.0"
    const val fragment = "1.2.1"
    const val glide = "4.8.0"
    const val hamcrest = "1.3"
    const val junit = "4.12"
    const val lifecycle = "2.2.0"
    const val material = "1.0.0"
    const val mockito = "2.25.0"
    const val mockito_all = "1.10.19"
    const val mockito_android = "2.25.0"
    const val mockwebserver = "3.8.1"
    const val navigation = "2.3.0-alpha01"
    const val timber = "4.7.1"
    const val recyclerview = "1.0.0"
    const val robolectric = "4.2"
    const val rx_android = "2.0.1"
    const val rxjava2 = "2.1.3"
    const val transition = "1.3.0"
    const val truth = "1.0.1"
    const val retrofit = "2.9.0"
    const val loggingInterceptor = "3.12.0"
}

object Kotlin {
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val test = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
    const val plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val allopen = "org.jetbrains.kotlin:kotlin-allopen:${Versions.kotlin}"
}

object LifeCycle {
    const val runtime = "androidx.lifecycle:lifecycle-runtime:${Versions.lifecycle}"
    const val java8 = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"
    const val compiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    const val viewmodel_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val livedata_ktx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
}

object AndroidX {
    const val activityKtx = "androidx.activity:activity-ktx:${Versions.activity}"
    const val annotations = "androidx.annotation:annotation:${Versions.annotations}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.core}"
    const val archCoreRuntime = "androidx.arch.core:core-runtime:${Versions.arch_core}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout}"
    const val cardView = "androidx.cardview:cardview:${Versions.cardview}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val transition = "androidx.transition:transition:${Versions.transition}"
    const val truth = "com.google.truth:truth:${Versions.truth}"
    const val benchmark = "androidx.benchmark:benchmark-junit4:${Versions.benchmark}"
    const val benchmark_gradle = "androidx.benchmark:benchmark-gradle-plugin:${Versions.benchmark}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val lifecycle = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val material = "com.google.android.material:material:${Versions.material}"
}

object Navigation {
    const val runtime = "androidx.navigation:navigation-runtime:${Versions.navigation}"
    const val runtime_ktx = "androidx.navigation:navigation-runtime-ktx:${Versions.navigation}"
    const val fragment = "androidx.navigation:navigation-fragment:${Versions.navigation}"
    const val fragment_ktx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val testing = "androidx.navigation:navigation-testing:${Versions.navigation}"
    const val ui = "androidx.navigation:navigation-ui:${Versions.navigation}"
    const val ui_ktx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val safe_args_plugin =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
}

object Rx {
    const val android = "io.reactivex.rxjava2:rxandroid:${Versions.rx_android}"
    const val java2 = "io.reactivex.rxjava2:rxjava:${Versions.rxjava2}"
}

object Fragment {
    const val runtime = "androidx.fragment:fragment:${Versions.fragment}"
    const val runtime_ktx = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    const val testing = "androidx.fragment:fragment-testing:${Versions.fragment}"
}

object Dagger {
    const val runtime = "com.google.dagger:dagger:${Versions.dagger}"
    const val android = "com.google.dagger:dagger-android:${Versions.dagger}"
    const val android_support = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    const val compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val android_support_compiler =
        "com.google.dagger:dagger-android-processor:${Versions.dagger}"
}

object Gradle {
    const val plugin = "com.android.tools.build:gradle:${Versions.android_gradle_plugin}"
}

object Square {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.loggingInterceptor}"
    const val runtime = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val mock = "com.squareup.retrofit2:retrofit-mock:${Versions.retrofit}"
}

object Glide {
    const val runtime = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val compile = "com.github.bumptech.glide:compiler:${Versions.glide}"
}

object Mockito {
    const val core = "org.mockito:mockito-core:${Versions.mockito}"
    const val all = "org.mockito:mockito-all:${Versions.mockito_all}"
    const val android = "org.mockito:mockito-android:${Versions.mockito_android}"
}

object Espresso {
    const val core = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val contrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso}"
    const val intents = "androidx.test.espresso:espresso-intents:${Versions.espresso}"
}

object Atsl {
    const val core = "androidx.test:core:${Versions.atsl_core}"
    const val extJunit = "androidx.test.ext:junit:${Versions.atsl_junit}"
    const val runner = "androidx.test:runner:${Versions.atsl_runner}"
    const val rules = "androidx.test:rules:${Versions.atsl_rules}"
}

object ArchCore {
    const val runtime = "androidx.arch.core:core-runtime:${Versions.arch_core}"
    const val testing = "androidx.arch.core:core-testing:${Versions.arch_core}"
}

object Testing {
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    const val hamcrest = "org.hamcrest:hamcrest-all:${Versions.hamcrest}"
    const val junit = "junit:junit:${Versions.junit}"
    const val mock_web_server = "com.squareup.okhttp3:mockwebserver:${Versions.mockwebserver}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
}
