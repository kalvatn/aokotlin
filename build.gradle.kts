import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import dependencies.Libs
import dependencies.TestLibs
import environment.BuildEnv
import io.gitlab.arturbosch.detekt.Detekt
import org.gradle.api.tasks.wrapper.Wrapper.DistributionType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import project.ProjectModule
import project.archivaMavenRepository
import project.getProjectModule
import project.gitlabMavenRepository
import repository.ArchivaRepository
import repository.GitlabProject

plugins {
  base
  `maven-publish`
  jacoco
  `kotlin-dsl`
//  id(BuildPlugins.KOTLIN_JVM) version BuildPlugins.Versions.KOTLIN apply false
  kotlin("jvm") version BuildPlugins.Versions.KOTLIN
  kotlin("plugin.serialization") version BuildPlugins.Versions.KOTLIN
  id(BuildPlugins.SHADOW) version BuildPlugins.Versions.SHADOW apply false
  id(BuildPlugins.DETEKT) version BuildPlugins.Versions.DETEKT
  id(BuildPlugins.SONARQUBE) version BuildPlugins.Versions.SONARQUBE
  id(BuildPlugins.KTLINT) version BuildPlugins.Versions.KTLINT
//  id(BuildPlugins.PROPERTIES) version BuildPlugins.Versions.PROPERTIES
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
    maven {
      url = uri("https://packages.confluent.io/maven/")
    }
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

  tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = BuildEnv.JAVA_VERSION.toString()
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
    ignoreFailures.set(true)
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

  if (module.publishTo.isNotEmpty()) {
    project.publish(module.publishTo)
  }
  if (module.createShadowJar) {
    project.shadowJar(module)
  }

  dependencies {
    implementation(Libs.LOGBACK_CLASSIC)
    implementation(Libs.KOTLIN_LOGGING)
    implementation(Libs.NEWRELIC_API)

    testImplementation(platform(TestLibs.JUNIT_PLATFORM))
    testImplementation(TestLibs.JUNIT_JUPITER)
    testImplementation(TestLibs.JUNIT_JUPITER_ENGINE)
    testImplementation(TestLibs.KOTLIN_TEST)
    testImplementation(TestLibs.KOTLIN_TEST_JUNIT)
    testImplementation(TestLibs.KOTLIN_COROUTINES_TEST)
    testImplementation(TestLibs.MOCKK)
  }
}

dependencies {
  subprojects.forEach {
    api(it)
  }
}

fun Project.publish(publishTo: List<repository.PublishTarget>) {
  apply {
    plugin("maven-publish")
  }
  configure<PublishingExtension> {
    publications {
      create<MavenPublication>(project.project.name) {
        from(project.components["java"])
        val sourcesJar by project.tasks.creating(Jar::class) {
          val sourceSets: SourceSetContainer by project
          from(sourceSets["main"].allJava)
          archiveClassifier.set("sources")
        }
        val javadocJar by project.tasks.creating(Jar::class) {
          from(project.tasks["javadoc"])
          archiveClassifier.set("javadoc")
        }
        artifact(sourcesJar)
        artifact(javadocJar)
      }
    }
    publishTo.forEach {
      when (it) {
        is ArchivaRepository -> this.repositories.add(archivaMavenRepository(it))
        is GitlabProject -> this.repositories.add(gitlabMavenRepository(it))
      }
    }
  }
}

fun Project.shadowJar(module: ProjectModule) {
  if (module.mainClassName.isNotBlank()) {
    apply {
      plugin(BuildPlugins.SHADOW)
    }
    tasks.withType<ShadowJar> {
      if (BuildEnv.IS_CI_ENVIRONMENT) {
        this.outputs.cacheIf {
          false
        }
      }
      archiveFileName.set("${archiveBaseName.get()}-shadow.${archiveExtension.get()}")
      manifest {
        attributes["Main-Class"] = module.mainClassName
        attributes["X-Compile-Source-JDK"] = project.java.sourceCompatibility
        attributes["X-Compile-Target-JDK"] = project.java.targetCompatibility
      }
    }
    tasks.withType<AbstractArchiveTask> {
      // https://imperceptiblethoughts.com/shadow/configuration/reproducible-builds/
      isPreserveFileTimestamps = false
      isReproducibleFileOrder = true
    }
  }
}

tasks.named("clean", Delete::class.java) {
  doFirst {
    delete(rootProject.buildDir)
  }
}

tasks.register("printProjectVersion") {
  doLast {
    println(project.version)
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

val newrelicAgent: Configuration by configurations.creating

dependencies {
  newrelicAgent(Libs.NEWRELIC_AGENT_JAVA) {
    isTransitive = false
  }
}

tasks.register<Copy>("copyNewrelicAgent") {
  group = "newrelic"
  description = "copies newrelic agent jar to $buildDir/libs"
  val newrelicAgentJar = zipTree(newrelicAgent.singleFile.toPath()).filter { it.name == "newrelic.jar" }.files.first()
  val destDir = "$buildDir/libs"
  from(newrelicAgentJar)
  into(destDir)
  doLast {
    println("copied $newrelicAgentJar to $destDir")
  }
}
