package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2015
import com.kalvatn.aoc.core.runner.PuzzleRunner
import com.kalvatn.aoc.extensions.reductions
import kotlinx.coroutines.runBlocking

class Y2015D01(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2015(Day.D01, input) {

  private val changes: List<Int> = this.input.singleLine().map { parenToInt(it) }

  private fun parenToInt(paren: Char): Int {
    return when (paren) {
      '(' -> 1
      else -> -1
    }
  }

  override suspend fun partOne(): String {
    return changes.sum().toString()
  }

  override suspend fun partTwo(): String {
    return changes.reductions(0) { acc, i -> acc + i }.takeWhile { it != -1 }.count().toString()
  }
}

fun main() = runBlocking {
  PuzzleRunner.run(Y2015D01())
}
