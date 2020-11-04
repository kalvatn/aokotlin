package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2015
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking

class Y2015D08(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2015(Day.D08, input) {

  private val lines by lazy { this.input.lines }
  private val escapedPattern =
    """[\w]|\\"|\\x[0-9a-f]{2}|\\\\""".toRegex()

  private fun countAll(s: String) = s.length
  private fun countData(s: String) = escapedPattern.findAll(s).count()
  private fun escape(s: String) = s.replace("\\", "\\\\").replace("\"", "\\\"")
  private fun countEscaped(s: String) = escape(s).length + 2

  fun p1(vararg lines: String) = lines.map { countAll(it) - countData(it) }.sum()
  fun p2(vararg lines: String) = lines.map { countEscaped(it) - countAll(it) }.sum()

  override suspend fun partOne(): String {
    return p1(*lines.toTypedArray()).toString()
  }

  override suspend fun partTwo(): String {
    return p2(*lines.toTypedArray()).toString()
  }
}

fun main() = runBlocking {
  PuzzleRunner.run(Y2015D08())
}
