package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2015
import com.kalvatn.aoc.core.runner.PuzzleRunner
import com.kalvatn.aoc.extensions.doesNotContain
import com.kalvatn.aoc.extensions.hasConsecutiveLetters
import com.kalvatn.aoc.extensions.hasNVowels
import com.kalvatn.aoc.extensions.hasNonOverlappingPair
import com.kalvatn.aoc.extensions.hasXYX
import kotlinx.coroutines.runBlocking

class Y2015D05(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2015(Day.D05, input) {

  fun nicePartOne(string: String): Boolean {
    return string.hasNVowels(3) &&
      string.hasConsecutiveLetters(1) &&
      string.doesNotContain("ab", "cd", "pq", "xy")
  }

  fun nicePartTwo(string: String): Boolean {
    return string.hasNonOverlappingPair() &&
      string.hasXYX()
  }

  override suspend fun partOne(): String {
    return input.lines.filter { nicePartOne(it) }.count().toString()
  }

  override suspend fun partTwo(): String {
    return input.lines.filter { nicePartTwo(it) }.count().toString()
  }
}

fun main() = runBlocking {
  PuzzleRunner.run(Y2015D05())
}
