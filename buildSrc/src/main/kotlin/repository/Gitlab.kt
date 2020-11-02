package repository

import java.net.URI

const val GITLAB_ROOT_URL: String = "https://gitlab.sportradar.ag"

@Suppress("unused", "MemberVisibilityCanBePrivate")
enum class GitlabProject(
  val group: GitlabGroup,
  val projectId: Int,
  val projectName: String
) : PublishTarget {
  INTERNAL_PROTO(
    group = GitlabGroup.INTERNAL,
    projectId = 2818,
    projectName = "proto"
  ),
  SMAUG(
    group = GitlabGroup.SQUAD_SLOTHS,
    projectId = 3452,
    projectName = "smaug"
  );

  fun projectPath() =
    "${group.groupName}/$projectName"

  fun projectUrl() =
    "$GITLAB_ROOT_URL/${projectPath()}"

  fun mavenRepositoryUrl() =
    URI("$GITLAB_ROOT_URL/api/v4/projects/$projectId/packages/maven")
}

@Suppress("unused", "MemberVisibilityCanBePrivate")
enum class GitlabGroup(
  val groupId: Int,
  val groupName: String
) {
  LIVESCORE(
    groupId = 827,
    groupName = "livescore"
  ),
  INTERNAL(
    groupId = 801,
    groupName = "internal"
  ),
  SQUAD_SLOTHS(
    groupId = 1346,
    groupName = "squad-sloths"
  );

  fun mavenRepositoryUrl() =
    URI("$GITLAB_ROOT_URL/api/v4/groups/$groupName/-/packages/maven")
}
