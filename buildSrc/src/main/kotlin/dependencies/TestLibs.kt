package dependencies

object TestLibs {
  const val KOTLIN_TEST = "org.jetbrains.kotlin:kotlin-test:${Versions.KOTLIN}"
  const val KOTLIN_COROUTINES_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.KOTLIN_COROUTINES}"
  const val KOTLIN_TEST_JUNIT = "org.jetbrains.kotlin:kotlin-test-junit5:${Versions.KOTLIN}"

  const val KOTEST = "io.kotest:kotest-runner-junit5:${Versions.KOTEST}"
  const val KOTEST_ASSERTIONS_CORE = "io.kotest:kotest-assertions-core:${Versions.KOTEST}"

  const val JUNIT_PLATFORM = "org.junit:junit-bom:${Versions.JUNIT}"
  const val JUNIT_JUPITER = "org.junit.jupiter:junit-jupiter:${Versions.JUNIT}"
  const val JUNIT_JUPITER_ENGINE = "org.junit.jupiter:junit-jupiter-engine:${Versions.JUNIT}"

  const val MOCKK = "io.mockk:mockk:${Versions.MOCKK}"
  const val MOCKSERVER_NETTY = "org.mock-server:mockserver-netty:${Versions.MOCKSERVER}"

  const val TEST_CONTAINERS_NEO4J = "org.testcontainers:neo4j:${Versions.TEST_CONTAINERS}"
  const val TEST_CONTAINERS_JUNIT_JUPITER = "org.testcontainers:junit-jupiter:${Versions.TEST_CONTAINERS}"

  const val KTOR_SERVER_TEST_HOST = "io.ktor:ktor-server-test-host:${Versions.KTOR}"

  object Versions {
    // test scope
    const val JUNIT = "5.7.0"
    const val MOCKK = "1.9.3"
    const val MOCKSERVER = "5.9.0"
    const val TEST_CONTAINERS = "1.12.2"
    const val KOTLIN = Libs.Versions.KOTLIN
    const val KOTLIN_COROUTINES = Libs.Versions.KOTLIN_COROUTINES
    const val KTOR = Libs.Versions.KTOR
    const val KOTEST = "4.3.1"

  }
}
