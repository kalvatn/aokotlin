import dependencies.Libs
import project.ProjectModule

dependencies {
  api(project(ProjectModule.AOC_CORE.modulePath()))
  implementation(Libs.KOTLINX_SERIALIZATION_JSON)
}
