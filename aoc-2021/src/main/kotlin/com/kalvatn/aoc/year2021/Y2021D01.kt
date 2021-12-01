package com.kalvatn.aoc.year2021

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2021
import com.kalvatn.aoc.core.model.Year
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking

class Y2021D01(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2021(Day.D01, input) {

  private val lines by lazy { this.input.lines }

  override suspend fun partOne(): String {
    var count = 0
    val measurements = lines.map { it.toInt() }
    var prev = measurements.first()
    measurements.drop(1).forEach { measurement ->
      if (measurement > prev) {
        count = count.inc()
      }
      prev = measurement
    }
    return "$count"
  }

  override suspend fun partTwo(): String {
    var count = 0
    val measurements = lines.map { it.toInt() }.windowed(3, 1).map { it.sum() }
    var prev = measurements.first()
    measurements.drop(1).forEach { measurement ->
      if (measurement > prev) {
        count = count.inc()
      }
      prev = measurement
    }
    return "$count"
  }

  companion object {
  }
}

fun main() = runBlocking {
  val input = PuzzleInput.p1Test(Year.Y2021, Day.D01)
  PuzzleRunner.run(Y2021D01(input))
  PuzzleRunner.run(Y2021D01())
}
