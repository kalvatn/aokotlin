package com.kalvatn.aoc.year2021

import com.kalvatn.aoc.common.model.Point
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2021
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking
import java.lang.Integer.max
import kotlin.math.abs
import kotlin.math.sign

class Y2021D05(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2021(Day.D05, input) {

  private val re = "(\\d+),(\\d+) -> (\\d+),(\\d+)".toRegex()
  private val lines by lazy {
    this.input.lines.map { line ->
      val (x1, y1, x2, y2) = re.matchEntire(line)!!.destructured.toList().map { it.toInt() }
      Pair(Point(x1, y1), Point(x2, y2))
    }
  }

  private fun allPoints(onlyHorizontalOrVertical: Boolean): List<Point> {
    return lines
      .filter { !onlyHorizontalOrVertical || (it.first.x == it.second.x || it.first.y == it.second.y) }
      .flatMap {
        val diff = it.second - it.first
        val max = max(abs(diff.x), abs(diff.y))
        val direction = Point(diff.x.sign, diff.y.sign)
        (0..max).map { i -> it.first + direction * i }
      }
  }

  override suspend fun partOne(): String {
    val countOverlapping = allPoints(onlyHorizontalOrVertical = true)
      .groupingBy { it }
      .eachCount()
      .count { it.value >= 2 }
    return countOverlapping.toString()
  }

  override suspend fun partTwo(): String {
    val countOverlapping = allPoints(onlyHorizontalOrVertical = false)
      .groupingBy { it }
      .eachCount()
      .count { it.value >= 2 }
    return countOverlapping.toString()
  }
}

fun main() = runBlocking {
  PuzzleRunner.run(Y2021D05())
}
