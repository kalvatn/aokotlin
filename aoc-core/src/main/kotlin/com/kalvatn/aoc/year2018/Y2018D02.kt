package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzleYearDay
import com.kalvatn.aoc.core.model.Year
import com.kalvatn.aoc.exceptions.Impossiburu
import kotlinx.coroutines.runBlocking

class Y2018D02(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzleYearDay(Year.Y2018, Day.D02, input) {

  private val boxIds = this.input.lines

  override suspend fun partOne(): String {
    val charCounts = boxIds.map { line -> line.groupingBy { it }.eachCount().values }
    return (charCounts.count { 2 in it } * charCounts.count { 3 in it }).toString()
  }

  override suspend fun partTwo(): String {
    for (s1 in boxIds) {
      for (s2 in boxIds) {
        val equalChars = (s2.indices)
          .asSequence()
          .filter { s1[it] == s2[it] }
          .map { s1[it] }
          .toList()
        if (equalChars.size == (s1.length - 1)) {
          return equalChars.joinToString("")
        }
      }
    }
    throw Impossiburu()
  }
}

fun main() = runBlocking {
  Y2018D02().run()
}
