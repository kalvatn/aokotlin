package com.kalvatn.aoc.year2020

class StringInput(
  private val content: String,
  private val splitOn: String = ""
) : Input {
  override fun toString(): String {
    return if (splitOn.isNotEmpty())
      "string ${content.length} split='$splitOn'"
    else "string ${content.length}"
  }

  override fun lines(): List<String> {
    if (splitOn.isEmpty()) {
      return listOf(content)
    }
    return content.split(splitOn)
      .filter { !it.isBlank() }
      .map { it.trim() }
  }
}
