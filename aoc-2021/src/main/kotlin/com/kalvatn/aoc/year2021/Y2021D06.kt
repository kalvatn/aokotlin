package com.kalvatn.aoc.year2021

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2021
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking

class Y2021D06(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2021(Day.D06, input) {

  private val lines by lazy { this.input.lines }
  private val fish = lines.first().split(",").map { it.toInt() }

  private val spawnCountCache = mutableMapOf<Int, Long>()

  private fun spawnCount(daysLeft: Int): Long {
    if (spawnCountCache.containsKey(daysLeft)) {
      return spawnCountCache.getValue(daysLeft)
    }
    if (daysLeft <= 0) return 1

    val c1 = spawnCount(daysLeft - 9)
    val c2 = spawnCount(daysLeft - 7)
    val sum = c1 + c2
    spawnCountCache[daysLeft] = sum
    return c1 + c2
  }

  override suspend fun partOne(): String {
    return fish.sumOf { spawnCount(80 - it) }.toString()
  }

  override suspend fun partTwo(): String {
    return fish.sumOf { spawnCount(256 - it) }.toString()
  }
}

fun main() = runBlocking {
  PuzzleRunner.run(Y2021D06())
}
