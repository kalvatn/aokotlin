package com.kalvatn.aoc.year2021

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2021
import com.kalvatn.aoc.core.runner.PuzzleRunner
import com.kalvatn.aoc.exceptions.Impossiburu
import kotlinx.coroutines.runBlocking

class Y2021D10(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2021(Day.D10, input) {

  private val lines by lazy { this.input.lines }

  private val brackets = listOf(
    "()", "[]", "{}", "<>"
  )

  private tailrec fun normalize(line: String): String {
    if (brackets.none { line.contains(it) }) {
      return line
    }
    val replaced = brackets.fold(line) { acc, bracket ->
      acc.replace(bracket, "")
    }
    return normalize(replaced)
  }

  private fun score(bracket: Char) = when (bracket) {
    ')' -> 3
    ']' -> 57
    '}' -> 1197
    '>' -> 25137
    else -> throw Impossiburu()
  }


  override suspend fun partOne(): String {
    return lines.map {
      it to normalize(it)
    }.filter { (_, normalized) ->
      brackets.any { normalized.contains(it[1]) }
    }.map { (original, normalized) ->
      val i = brackets.filter {
        val i = normalized.indexOf(it[1])
        i > 0 && normalized[i.dec()] != it[0]
      }.minOf {
        normalized.indexOf(it[1])
      }
      val was = normalized[i]
      val expected = brackets.first { it.contains(normalized[i.dec()]) }[1]
      println("$original $normalized , expected '$expected' but found '$was' instead")
      was
    }.map {
      score(it)
    }.sumOf { it }.toString()
  }

  override suspend fun partTwo(): String {
    return ""
  }

}

fun main() = runBlocking {
//  val input = PuzzleInput.p1Test(Year.Y2021, Day.D10)
//  PuzzleRunner.run(Y2021D10(input))
  PuzzleRunner.run(Y2021D10())
}
