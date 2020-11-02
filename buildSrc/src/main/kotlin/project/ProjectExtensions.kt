package project

import environment.BuildEnv
import environment.Property
import environment.PropertyLoader
import org.gradle.api.Project
import org.gradle.api.artifacts.repositories.MavenArtifactRepository
import org.gradle.api.artifacts.repositories.PasswordCredentials
import org.gradle.api.credentials.HttpHeaderCredentials
import org.gradle.authentication.http.HttpHeaderAuthentication
import org.gradle.internal.credentials.DefaultPasswordCredentials
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.credentials
import repository.ArchivaRepository
import repository.GitlabProject

//fun Project.kotlinProject() {
//  apply {
//    plugin(BuildPlugins.KOTLIN_JVM)
//  }
//  dependencies {
//    "implementation"(kotlin("stdlib-jdk8"))
//  }
//}

fun Project.getProjectModule() = ProjectModule.fromName(this.name)

fun Project.gitlabToken(): Pair<String, String> {
  return if (BuildEnv.IS_CI_ENVIRONMENT) {
    Pair("Job-Token", System.getenv("CI_JOB_TOKEN"))
  } else {
    Pair("Private-Token", loadProperty(Property.GITLAB_ACCESS_TOKEN))
  }
}

fun Project.loadProperty(property: Property): String =
  PropertyLoader.instance(this).load(property)

fun Project.mavenPasswordCredentials(repo: ArchivaRepository): PasswordCredentials =
  DefaultPasswordCredentials(
    loadProperty(repo.propertyUsername),
    loadProperty(repo.propertyPassword)
  )


fun Project.gitlabMavenRepository(gitlabProject: GitlabProject): MavenArtifactRepository {
  val gitlabToken = gitlabToken()
  return repositories.maven {
    url = gitlabProject.mavenRepositoryUrl()
    credentials(HttpHeaderCredentials::class) {
      name = gitlabToken.first
      value = gitlabToken.second
    }
    authentication {
      create<HttpHeaderAuthentication>("header")
    }
  }
}

fun Project.archivaMavenRepository(archivaRepositoryRepo: ArchivaRepository): MavenArtifactRepository {
  val auth = mavenPasswordCredentials(archivaRepositoryRepo)
  return repositories.maven {
    url = archivaRepositoryRepo.url
    credentials(PasswordCredentials::class) {
      username = auth.username
      password = auth.password
    }
  }
}

