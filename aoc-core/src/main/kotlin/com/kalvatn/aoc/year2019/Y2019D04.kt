package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2019
import com.kalvatn.aoc.core.runner.PuzzleRunner
import com.kalvatn.aoc.extensions.hasConsecutiveDigits
import kotlinx.coroutines.runBlocking

class Y2019D04(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2019(Day.D04, input) {

  private val range = this.input.singleLine().split("-").map { it.toInt() }

  private fun Int.hasNonDecreasingDigits(): Boolean {
    var last = 0
    for (digit in this.toString()) {
      val digitValue = "$digit".toInt()
      if (digitValue >= last) {
        last = digitValue
      } else {
        return false
      }
    }
    return true
  }

  private fun Int.valid() = hasNonDecreasingDigits() && toString().hasConsecutiveDigits()
  private fun Int.valid2() = hasNonDecreasingDigits() && toString().groupingBy { it }.eachCount().containsValue(2)

  fun valid(s: String) = s.toInt().valid()
  fun valid2(s: String) = s.toInt().valid2()

  override suspend fun partOne(): String {
    val (min, max) = range
    return (min..max).filter { it.valid() }.count().toString()
  }

  override suspend fun partTwo(): String {
    val (min, max) = range
    return (min..max).filter { it.valid2() }.count().toString()
  }
}

fun main() = runBlocking {
  PuzzleRunner(listOf(Y2019D04())).run()
}
