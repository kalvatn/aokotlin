package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2015
import com.kalvatn.aoc.core.runner.PuzzleRunner
import com.kalvatn.aoc.extensions.extractIntegers
import kotlinx.coroutines.runBlocking

class Y2015D02(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2015(Day.D02, input) {

  data class Present(val l: Int, val w: Int, val h: Int) {
    private fun sides(): List<Int> {
      return listOf(l * w, w * h, h * l)
    }

    private fun surface(): Int {
      return sides().sumOf { it * 2 }
    }

    private fun smallestSide(): Int {
      return sides().minOrNull()!!
    }

    fun paper(): Int {
      return surface() + smallestSide()
    }

    fun ribbon(): Int {
      val wrap = listOf(l, w, h).sorted().take(2).sumOf { it * 2 }
      val bow = l * w * h
      return wrap + bow
    }

    companion object {
      fun fromString(string: String): Present {
        val (l, w, h) = string.extractIntegers()
        return Present(l, w, h)
      }
    }
  }

  private val dimensions = this.input.map { Present.fromString(it) }

  override suspend fun partOne(): String {
    return dimensions.sumOf { it.paper() }.toString()
  }

  override suspend fun partTwo(): String {
    return dimensions.sumOf { it.ribbon() }.toString()
  }
}

fun main() = runBlocking {
  PuzzleRunner.run(Y2015D02())
}
