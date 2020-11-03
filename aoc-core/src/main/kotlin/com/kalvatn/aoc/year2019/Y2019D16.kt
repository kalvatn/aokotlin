package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2019
import com.kalvatn.aoc.core.runner.PuzzleRunner
import com.kalvatn.aoc.extensions.cycle
import kotlinx.coroutines.runBlocking
import kotlin.math.abs

class Y2019D16(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2019(Day.D16, input) {

  private val numbers by lazy { this.input.singleLineSplit("").map { it.toInt() } }

  private val pattern = listOf(0, 1, 0, -1)

  fun extendPattern(index: Int): Sequence<Int> {
    if (index == 0) {
      return pattern.cycle().drop(1)
    }
    val new = mutableListOf<Int>()
    pattern.forEach { v ->
      repeat(index + 1) {
        new.add(v)
      }
    }
    return new.cycle().drop(1)
  }

  fun phase(signal: List<Int>): List<Int> {
    return (signal.indices).map { i ->
      abs(
        extendPattern(i).take(signal.size).mapIndexed { pi, pv ->
          signal[pi] * pv
        }.sum()
      ) % 10
    }
  }

  override suspend fun partOne(): String {
    var output = numbers
    repeat(100) {
      output = phase(output)
    }
    return output.joinToString("").take(8)
  }

  override suspend fun partTwo(): String {
    val offset = this.input.singleLine().take(7).toInt()
    val output = numbers.cycle(10000).drop(offset).toMutableList()
    repeat(100) {
      (output.size - 2 downTo 0).forEach { index ->
        output[index] = (output[index] + output[index + 1]) % 10
      }
    }
    return output.take(8).joinToString("")
  }
}

fun main() = runBlocking {
  PuzzleRunner(listOf(Y2019D16())).run()
}
