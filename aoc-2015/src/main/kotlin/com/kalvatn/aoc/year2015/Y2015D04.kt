package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2015
import com.kalvatn.aoc.core.runner.PuzzleRunner
import com.kalvatn.aoc.exceptions.Impossiburu
import com.kalvatn.aoc.extensions.md5
import kotlinx.coroutines.runBlocking

class Y2015D04(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2015(Day.D04, input) {

  private val key = this.input.singleLine()

  private fun findSecret(startingWith: String): Int {
    (0..Integer.MAX_VALUE).forEach {
      if ("$key$it".md5().startsWith(startingWith)) return it
    }
    throw Impossiburu()
  }

  override suspend fun partOne(): String {
    return findSecret("0".repeat(5)).toString()
  }

  override suspend fun partTwo(): String {
    return findSecret("0".repeat(6)).toString()
  }
}

fun main() = runBlocking {
  PuzzleRunner.run(Y2015D04())
}
