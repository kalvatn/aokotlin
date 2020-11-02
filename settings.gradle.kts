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
  "aoc-core"
)
