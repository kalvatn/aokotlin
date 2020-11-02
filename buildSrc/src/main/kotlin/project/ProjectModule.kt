package project

import repository.ArchivaRepository
import repository.PublishTarget

@Suppress("unused")
enum class ProjectModule(
  val moduleName: String,
  val mainClassName: String = "",
  val createShadowJar: Boolean = false,
  val publishTo: List<PublishTarget> = listOf()
) {
  AOC_CORE(
          moduleName = "aoc-core"
  ),
  AOC_2015(
    moduleName = "aoc-2015"
  ),
  ;

  fun modulePath() = ":$moduleName"

  companion object {
    private val byName = values().associateBy { it.moduleName }
    fun fromName(name: String) =
      byName[name] ?: throw IllegalArgumentException("no models.ProjectModule with name '$name'")
  }
}
