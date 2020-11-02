package environment

import org.gradle.api.Project
import java.io.File
import java.util.Properties

class PropertyLoader private constructor(rootDirectory: String) {
  private val propFilePath =
    "$rootDirectory/gradle-local.properties"
  private val properties: Properties = Properties().apply {
    File(propFilePath).let {
      if (it.exists()) {
        load(it.inputStream())
      } else {
        System.err.println("$propFilePath does not exist!")
        generateTemplate()
      }
    }
  }

  private fun generateTemplate() {
    println("generating template $propFilePath")
    if (File(propFilePath).createNewFile()) {
      File(propFilePath).printWriter().use { pw ->
        Property.values().forEach { prop ->
          pw.println("${prop.name}=")
        }
      }
    } else {
      System.err.println("could not generate template $propFilePath")
    }
  }

  fun load(property: Property): String =
    System.getenv(property.name) // try loading from environment variables first
      ?: properties.getProperty(property.name) // then try load from property file
      ?: "".also {
        System.err.println("cannot find secret with key '${property.name}' in neither System.ENV or $propFilePath")
      }

  companion object {
    @Volatile
    private var INSTANCE: PropertyLoader? = null

    fun instance(project: Project): PropertyLoader =
      INSTANCE ?: synchronized(this) {
        INSTANCE ?: PropertyLoader(project.rootProject.rootDir.absolutePath)
          .also { INSTANCE = it }
      }
  }
}
