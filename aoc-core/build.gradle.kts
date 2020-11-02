import dependencies.Libs
import dependencies.TestLibs


dependencies {
    implementation(Libs.KOTLIN_COROUTINES)
    implementation(Libs.OKHTTP)
    implementation(Libs.OKHTTP_LOGGING_INTERCEPTOR)
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")
    implementation("com.marcinmoskala:DiscreteMathToolkit:1.0.3")
    testImplementation(TestLibs.KOTEST)
//    testImplementation(TestLibs.KOTEST_ASSERTIONS_CORE)
}
