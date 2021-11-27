package environment

import org.gradle.api.JavaVersion

object BuildEnv {
  const val GRADLE_VERSION = "7.3"
  val JAVA_VERSION = JavaVersion.VERSION_11
  val IS_CI_ENVIRONMENT = System.getenv().containsKey("CI_JOB_TOKEN")
}
