package com.kalvatn.aoc.year2021

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2021
import com.kalvatn.aoc.core.model.Year
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking
import kotlin.math.abs

class Y2021D07(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2021(Day.D07, input) {

  private val lines by lazy { this.input.lines }

  override suspend fun partOne(): String {
    val numbers = lines.first().split(",").map { it.toInt() }
    val (min, max) = listOf(numbers.minOf { it }, numbers.maxOf { it })
    val minFuel = (min..max).map { toPos ->
      numbers.sumOf { fromPos ->
        abs(fromPos - toPos)
      }
    }.minOf { it }
    return "$minFuel"
  }

  override suspend fun partTwo(): String {
    return ""
  }

}

fun main() = runBlocking {
  val input = PuzzleInput.p1Test(Year.Y2021, Day.D07)
  PuzzleRunner.run(Y2021D07(input))
  PuzzleRunner.run(Y2021D07())
}
