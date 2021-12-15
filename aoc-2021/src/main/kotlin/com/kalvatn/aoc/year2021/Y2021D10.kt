package com.kalvatn.aoc.year2021

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2021
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking

class Y2021D10(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2021(Day.D10, input) {

  private val normalized by lazy { this.input.lines.map { normalize(it) } }

  companion object {
    private val BRACKET_PAIRS = listOf("()", "[]", "{}", "<>")
    private val SCORES_ILLEGAL = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
    private val SCORES_CLOSING = mapOf(')' to 1, ']' to 2, '}' to 3, '>' to 4)
  }

  private tailrec fun normalize(line: String): String {
    if (BRACKET_PAIRS.none { line.contains(it) }) {
      return line
    }
    val replaced = BRACKET_PAIRS.fold(line) { acc, pair -> acc.replace(pair, "") }
    return normalize(replaced)
  }

  private fun scoreIllegal(bracket: Char) = SCORES_ILLEGAL.getValue(bracket)
  private fun scoreClosing(bracket: Char) = SCORES_CLOSING.getValue(bracket)

  override suspend fun partOne(): String {
    return normalized
      .asSequence()
      .filter { BRACKET_PAIRS.any { pair -> it.contains(pair[1]) } }
      .map { corrupted ->
        val firstIllegalIndex = BRACKET_PAIRS.filter { pair ->
          val i = corrupted.indexOf(pair[1])
          i > 0 && corrupted[i.dec()] != pair[0]
        }.minOf { pair -> corrupted.indexOf(pair[1]) }
        corrupted[firstIllegalIndex]
      }.map { scoreIllegal(it) }
      .sumOf { it }.toString()
  }

  override suspend fun partTwo(): String {
    val scores = normalized
      .asSequence()
      .filterNot { BRACKET_PAIRS.any { pair -> it.contains(pair[1]) } }
      .map { incomplete ->
        incomplete.reversed().map { BRACKET_PAIRS.first { pair -> pair.contains(it) }[1] }
      }.map { completion ->
        completion.fold(0L) { score, bracket -> score * 5 + scoreClosing(bracket) }
      }.sorted()
      .toList()
    return scores[scores.size / 2].toString()
  }
}

fun main() = runBlocking {
  PuzzleRunner.run(Y2021D10())
}
