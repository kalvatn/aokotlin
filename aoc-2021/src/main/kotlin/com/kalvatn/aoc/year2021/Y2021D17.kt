package com.kalvatn.aoc.year2021

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2021
import com.kalvatn.aoc.core.model.Year
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking

class Y2021D17(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2021(Day.D17, input) {

  private val lines by lazy { this.input.lines }

  override suspend fun partOne(): String {
    lines.forEach {
      println(it)
    }
    return ""
  }

  override suspend fun partTwo(): String {
    return ""
  }

  companion object {
  }
}

fun main() = runBlocking {
  val input = PuzzleInput.p1Test(Year.Y2021, Day.D17)
  PuzzleRunner.run(Y2021D17(input))
  PuzzleRunner.run(Y2021D17())
}
