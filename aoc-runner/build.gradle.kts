import project.ProjectModule

dependencies {
  api(project(ProjectModule.AOC_CORE.modulePath()))
  implementation(project(ProjectModule.AOC_2015.modulePath()))
  implementation(project(ProjectModule.AOC_2016.modulePath()))
  implementation(project(ProjectModule.AOC_2017.modulePath()))
  implementation(project(ProjectModule.AOC_2018.modulePath()))
  implementation(project(ProjectModule.AOC_2019.modulePath()))
  implementation(project(ProjectModule.AOC_2020.modulePath()))
}
