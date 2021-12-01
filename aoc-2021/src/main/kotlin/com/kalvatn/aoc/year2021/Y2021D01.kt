package com.kalvatn.aoc.year2021

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2021
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking

class Y2021D01(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2021(Day.D01, input) {

  private val lines by lazy { this.input.lines }

  private fun countIncreases(measurements: List<Int>): Int =
    measurements.zipWithNext { prev, next -> next - prev }.count { it > 0 }

  override suspend fun partOne(): String {
    val measurements = lines.map { it.toInt() }
    return countIncreases(measurements).toString()
  }

  override suspend fun partTwo(): String {
    val windowSize = 3
    val measurements = lines.map { it.toInt() }
      .windowed(windowSize, 1)
      .map { it.sum() }
    return countIncreases(measurements).toString()
  }
}

fun main() = runBlocking {
  PuzzleRunner.run(Y2021D01())
}
