package com.kalvatn.aoc.year2021

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2021
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking

class Y2021D03(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2021(Day.D03, input) {

  companion object {
    const val BIT_ONE = '1'
    const val BIT_ZERO = '0'
  }

  private val lines by lazy { this.input.lines }
  private val indices = lines.first().indices

  private fun List<String>.countMatchingAtIndex(c: Char, index: Int) =
    this.map { it[index] }.count { it == c }

  private fun List<String>.countOnesAndZerosAtIndex(index: Int) =
    Pair(
      countMatchingAtIndex(BIT_ZERO, index),
      countMatchingAtIndex(BIT_ONE, index)
    )

  override suspend fun partOne(): String {
    val gamma = indices.fold("") { acc, index ->
      val (count0, count1) = lines.countOnesAndZerosAtIndex(index)
      acc + if (count0 > count1) BIT_ZERO else BIT_ONE
    }.toInt(2)
    val epsilon = gamma.xor((1 shl indices.last.inc()) - 1)
    return (gamma * epsilon).toString()
  }

  private fun filter(numbers: List<String>, oneBitDecider: (Int, Int) -> Boolean): Int {
    return indices.fold(numbers) { acc, index ->
      val (count0, count1) = acc.countOnesAndZerosAtIndex(index)
      when {
        acc.size == 1 -> acc
        oneBitDecider(count0, count1) -> acc.filter { it[index] == BIT_ONE }
        else -> acc.filter { it[index] == BIT_ZERO }
      }
    }.first().toInt(2)
  }

  override suspend fun partTwo(): String {
    val oxygen = filter(lines) { count0, count1 -> count1 >= count0 }
    val co2 = filter(lines) { count0, count1 -> count1 < count0 }
    return (oxygen * co2).toString()
  }
}

fun main() = runBlocking {
  PuzzleRunner.run(Y2021D03())
}
