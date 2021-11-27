import dependencies.Libs
import dependencies.TestLibs
import environment.BuildEnv
import io.gitlab.arturbosch.detekt.Detekt
import org.gradle.api.tasks.wrapper.Wrapper.DistributionType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import project.getProjectModule

plugins {
  base
  jacoco
  `kotlin-dsl`
  kotlin("jvm") version BuildPlugins.Versions.KOTLIN apply false
  kotlin("plugin.serialization") version BuildPlugins.Versions.KOTLIN apply false
  id(BuildPlugins.DETEKT) version BuildPlugins.Versions.DETEKT
  id(BuildPlugins.KTLINT) version BuildPlugins.Versions.KTLINT
}

if (BuildEnv.IS_CI_ENVIRONMENT) {
  tasks.wrapper {
    gradleVersion = BuildEnv.GRADLE_VERSION
    distributionType = DistributionType.BIN
  }
} else {
  tasks.wrapper {
    gradleVersion = BuildEnv.GRADLE_VERSION
    distributionType = DistributionType.ALL
  }
}

allprojects {
  group = "com.kalvatn.aoc"
  version = "0.0.1"
  repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
  }
}

subprojects {
  apply {
    plugin(BuildPlugins.DETEKT)
    plugin(BuildPlugins.KTLINT)
    plugin(BuildPlugins.KOTLIN_JVM)
    plugin(BuildPlugins.JACOCO)
  }

  tasks.withType<Test> {
    exclude("**/*IntegrationTest.class")
    useJUnitPlatform()
    maxHeapSize = "2048m"
    ignoreFailures = true
    testLogging {
      events(
//        "passed",
        "skipped",
        "failed"
      )
    }
  }

  tasks.jacocoTestReport {
    reports {
      xml.isEnabled = true
      xml.destination = file("build/reports/jacoco/report.xml")
    }
  }

  tasks.withType<KotlinCompile> {
    kotlinOptions {
      jvmTarget = BuildEnv.JAVA_VERSION.toString()
      allWarningsAsErrors = false
      languageVersion = "1.5"
      apiVersion = "1.5"
    }
  }

  configure<JavaPluginExtension> {
    sourceCompatibility = BuildEnv.JAVA_VERSION
    targetCompatibility = BuildEnv.JAVA_VERSION
  }

  ktlint {
    version.set(Libs.Versions.KTLINT)
    debug.set(false)
    verbose.set(true)
    android.set(false)
    coloredOutput.set(true)
    outputToConsole.set(true)
    ignoreFailures.set(false)
    enableExperimentalRules.set(true)
    disabledRules.set(
      setOf(
        "comment-spacing"
      )
    )
    filter {
      include("**/src/main/kotlin/**")
      include("**/src/test/kotlin/**")
      exclude("**/build/**/*")
      exclude("**/generated/**/*")
      exclude { element -> element.file.path.contains("generated/") }
    }
  }

  detekt {
    input = project.files("src/main/kotlin")
    config = files("${project.rootDir}/.detekt.yml")
    ignoreFailures = true
    reports {
      xml.enabled = true
      html.enabled = true
      txt.enabled = true
    }
  }
  tasks.withType<Detekt> {
    jvmTarget = BuildEnv.JAVA_VERSION.toString()
  }

  val module = getProjectModule()

  dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation(Libs.LOGBACK_CLASSIC)
    implementation(Libs.KOTLIN_LOGGING)
    implementation(Libs.KOTLIN_COROUTINES)

    testImplementation(platform(TestLibs.JUNIT_PLATFORM))
    testImplementation(TestLibs.JUNIT_JUPITER)
    testImplementation(TestLibs.JUNIT_JUPITER_ENGINE)

    testImplementation(TestLibs.KOTLIN_TEST)
    testImplementation(TestLibs.KOTLIN_TEST_JUNIT)
    testImplementation(TestLibs.KOTLIN_COROUTINES_TEST)

    testImplementation(TestLibs.KOTEST)
    testImplementation(TestLibs.KOTEST_ASSERTIONS_CORE)
  }
}

dependencies {
  subprojects.forEach {
    api(it)
  }
}

tasks.named("clean", Delete::class.java) {
  doFirst {
    delete(rootProject.buildDir)
  }
}

tasks.register<JacocoReport>("jacocoMerged") {
  doFirst {
    println("running jacocoMerged task")
  }

  executionData.setFrom(fileTree(project.rootDir.absolutePath).include("**/build/jacoco/*.exec"))

  subprojects
    .forEach {
      this.sourceSets(it.sourceSets.main.get())
      this.dependsOn(it.tasks.test)
    }

  reports {
    xml.isEnabled = true
    xml.destination = file("${project.buildDir}/reports/jacoco/report.xml")
    html.isEnabled = true
    html.destination = file("${project.buildDir}/reports/jacoco/html")
  }
}

tasks.test {
  finalizedBy("jacocoMerged")
}
