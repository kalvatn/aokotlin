package com.kalvatn.aoc.core.model

import com.kalvatn.aoc.core.input.PuzzleInput

interface Puzzle {
  val year: Year
  val day: Day
  val input: PuzzleInput

  suspend fun partOne(): String
  suspend fun partTwo(): String

  suspend fun run(runBenchmark: Boolean = false)
}
