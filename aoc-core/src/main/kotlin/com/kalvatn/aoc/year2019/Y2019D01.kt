package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2019
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking
import kotlin.math.floor

class Y2019D01(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2019(Day.D01, input) {

  val lines = this.input.asIntegers()

  fun fuelPartOne(mass: Int): Int {
    return (floor(mass / 3.0) - 2).toInt()
  }

  tailrec fun fuelPartTwo(mass: Int, total: Int = 0): Int {
    val fuel = fuelPartOne(mass)
    if (fuel <= 0) {
      return total
    }
    return fuelPartTwo(fuel, total + fuel)
  }

  override suspend fun partOne(): String {
    return lines.map { fuelPartOne(it) }.sum().toString()
  }

  override suspend fun partTwo(): String {
    return lines.map { fuelPartTwo(it) }.sum().toString()
  }
}

fun main() = runBlocking {
  PuzzleRunner(listOf(Y2019D01())).run()
}
