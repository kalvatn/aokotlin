package com.kalvatn.aoc.core.model

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.utils.timeit
import com.kalvatn.aoc.utils.toHMS

abstract class GenericPuzzleYearDay(
  final override val year: Year,
  final override val day: Day,
  input: PuzzleInput
) : Puzzle {

  final override val input: PuzzleInput

  init {
    when (input) {
      PuzzleInput.NULL -> this.input = PuzzleInput.forDay(year, day)
      else -> this.input = input
    }
  }

  override suspend fun run(runBenchmark: Boolean) {
    val (p1Result, p1Time) = timeit { partOne() }
    val (p2Result, p2Time) = timeit { partTwo() }
    val total = p1Time.plus(p2Time)
    println(
      """
            |${toString()}
            |   part one : ${p1Result.padEnd(30)} ${p1Time.toHMS()}
            |   part two : ${p2Result.padEnd(30)} ${p2Time.toHMS()}
            |${total.toHMS()}""".trimMargin("|")
    )
  }

  override fun toString(): String {
    return "${year.intString()}-${day.intString()}"
  }
}
