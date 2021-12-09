package com.kalvatn.aoc.year2021

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2021
import com.kalvatn.aoc.core.model.Year
import com.kalvatn.aoc.core.runner.PuzzleRunner
import com.kalvatn.aoc.year2021.Y2021D08.Pattern.Companion.SEGMENT_COUNT_CANDIDATE
import kotlinx.coroutines.runBlocking

//  0:      1:      2:      3:      4:      5:      6:      7:      8:      9:
// aaaa    ....    aaaa    aaaa    ....    aaaa    aaaa    aaaa    aaaa    aaaa
//b    c  .    c  .    c  .    c  b    c  b    .  b    .  .    c  b    c  b    c
//b    c  .    c  .    c  .    c  b    c  b    .  b    .  .    c  b    c  b    c
// ....    ....    dddd    dddd    dddd    dddd    dddd    ....    dddd    dddd
//e    f  .    f  e    .  .    f  .    f  .    f  e    f  .    f  e    f  .    f
//e    f  .    f  e    .  .    f  .    f  .    f  e    f  .    f  e    f  .    f
// gggg    ....    gggg    gggg    ....    gggg    gggg    ....    gggg    gggg


class Y2021D08(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2021(Day.D08, input) {

  private val lines by lazy { this.input.lines }

  data class Pattern(val signal: List<String>, val output: List<String>) {

    companion object {

      val SEGMENT_COUNT_CANDIDATE = mapOf(
        2 to listOf(1),
        3 to listOf(7),
        4 to listOf(4),
        5 to listOf(2, 3, 5),
        6 to listOf(0, 6, 9),
        7 to listOf(8)
      )
    }

  }

  override suspend fun partOne(): String {
    val count = lines.fold(0) { acc, line ->
      val (_, output) = line.split("|").map { side ->
        side.trim().split(" ").filter { it.isNotBlank() }
      }
      val single = output.mapNotNull { token ->
        SEGMENT_COUNT_CANDIDATE.filterValues { it.size == 1 }[token.count()]
      }.count()
      acc + single
    }
    return "$count"
  }

  override suspend fun partTwo(): String {
    return ""
  }
}

fun main() = runBlocking {
  val input = PuzzleInput.p1Test(Year.Y2021, Day.D08)
  PuzzleRunner.run(Y2021D08(input))
  PuzzleRunner.run(Y2021D08())
}
