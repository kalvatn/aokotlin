package dependencies

object Libs {
  const val KOTLIN_COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.KOTLIN_COROUTINES}"
  const val KOTLINX_SERIALIZATION_JSON = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.KOTLINX_SERIALIZATION_JSON}"

  const val LOGBACK_CLASSIC = "ch.qos.logback:logback-classic:${Versions.LOGBACK}"
  const val KOTLIN_LOGGING = "io.github.microutils:kotlin-logging:${Versions.KOTLIN_LOGGING}"

  const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP}"
  const val OKHTTP_LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP}"

  object Versions {
    const val KOTLIN = BuildPlugins.Versions.KOTLIN
    const val KOTLIN_COROUTINES = "1.5.2"

    const val KLAXON = "5.0.12"
    const val OKHTTP = "4.0.0"

    const val LOGBACK = "1.2.3"
    const val KOTLIN_LOGGING = "1.7.9"

    const val KTLINT = "0.37.2"

    const val KOTLINX_SERIALIZATION_JSON = "1.0.1"

  }
}

