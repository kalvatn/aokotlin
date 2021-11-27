package dependencies

object TestLibs {
  const val KOTLIN_TEST = "org.jetbrains.kotlin:kotlin-test:${Versions.KOTLIN}"
  const val KOTLIN_COROUTINES_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.KOTLIN_COROUTINES}"
  const val KOTLIN_TEST_JUNIT = "org.jetbrains.kotlin:kotlin-test-junit5:${Versions.KOTLIN}"

  const val KOTEST = "io.kotest:kotest-runner-junit5-jvm:${Versions.KOTEST}"
  const val KOTEST_ASSERTIONS_CORE = "io.kotest:kotest-assertions-core-jvm:${Versions.KOTEST}"

  const val JUNIT_PLATFORM = "org.junit:junit-bom:${Versions.JUNIT}"
  const val JUNIT_JUPITER = "org.junit.jupiter:junit-jupiter:${Versions.JUNIT}"
  const val JUNIT_JUPITER_ENGINE = "org.junit.jupiter:junit-jupiter-engine:${Versions.JUNIT}"


  object Versions {
    // test scope
    const val JUNIT = "5.8.1"
    const val KOTLIN = Libs.Versions.KOTLIN
    const val KOTLIN_COROUTINES = Libs.Versions.KOTLIN_COROUTINES
    const val KOTEST = "4.6.3"

  }
}
