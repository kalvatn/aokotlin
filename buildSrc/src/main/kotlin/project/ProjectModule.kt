package project

import repository.PublishTarget

@Suppress("unused")
enum class ProjectModule(
  val moduleName: String,
  val mainClassName: String = "",
  val createShadowJar: Boolean = false,
  val publishTo: List<PublishTarget> = listOf()
) {
  AOC_CORE(moduleName = "aoc-core"),
  AOC_2015(moduleName = "aoc-2015"),
  AOC_2016(moduleName = "aoc-2016"),
  AOC_2017(moduleName = "aoc-2017"),
  AOC_2018(moduleName = "aoc-2018"),
  AOC_2019(moduleName = "aoc-2019"),
  AOC_2020(moduleName = "aoc-2020"),
  AOC_2021(moduleName = "aoc-2021"),
  AOC_RUNNER(moduleName = "aoc-runner"),
  ;

  fun modulePath() = ":$moduleName"

  companion object {
    private val byName = values().associateBy { it.moduleName }
    fun fromName(name: String) =
      byName[name] ?: throw IllegalArgumentException("no models.ProjectModule with name '$name'")
  }
}
