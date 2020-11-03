package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2018
import com.kalvatn.aoc.extensions.cycle
import kotlinx.coroutines.runBlocking

class Y2018D01(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2018(Day.D01, input) {

  private val changes: List<Int> = this.input.asIntegers()

  private tailrec fun calibrate(
    changes: Iterator<Int>,
    seen: MutableSet<Int> = mutableSetOf(),
    current: Int = 0
  ): Int {
    return if (!seen.add(current)) current else calibrate(changes, seen, current + changes.next())
  }

  override suspend fun partOne(): String {
    return changes.sum().toString()
  }

  override suspend fun partTwo(): String {
    return calibrate(changes.cycle().iterator()).toString()
  }
}

fun main() = runBlocking {
  Y2018D01().run()
}
