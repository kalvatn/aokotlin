val isCI = System.getenv().containsKey("CI_JOB_TOKEN")
buildCache {
  local {
    directory = File(rootDir, ".gradle-build-cache")
    isEnabled = !isCI
  }
//    remote(HttpBuildCache::class) {
//        url = uri("http://gradle-build-cache.importers.sdm-stg.srcloud.io/cache/")
//        isEnabled = isCI
//        isPush = isCI
//    }
}

rootProject.name = "aokotlin"
include(
  // must be defined in buildSrc/models/ProjectModule
  "aoc-core",
  "aoc-2015",
  "aoc-2016",
  "aoc-2017",
  "aoc-2018",
  "aoc-2019",
  "aoc-2020",
  "aoc-2021",
  "aoc-runner"
)
