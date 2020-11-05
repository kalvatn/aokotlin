import dependencies.Libs
import project.ProjectModule

dependencies {
  api(project(ProjectModule.AOC_CORE.modulePath()))
  implementation("com.marcinmoskala:DiscreteMathToolkit:1.0.3")
}
