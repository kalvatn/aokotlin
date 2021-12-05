package com.kalvatn.aoc.year2021

import com.kalvatn.aoc.common.model.Point
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2021
import com.kalvatn.aoc.core.model.Year
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking
import java.lang.Integer.max
import kotlin.math.abs
import kotlin.math.min
import kotlin.math.sign

class Y2021D05(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2021(Day.D05, input) {

  private val lines by lazy { this.input.lines }

  private val re = "(\\d+),(\\d+) -> (\\d+),(\\d+)".toRegex()

  data class Pipeline(val start: Point, val end: Point)

  override suspend fun partOne(): String {
    val pipelines = lines.map { line ->
      val (x1, y1, x2, y2) = re.matchEntire(line)!!.destructured.toList().map { it.toInt() }
      Pipeline(Point(x1, y1), Point(x2, y2))
    }
    val horizontalOrVertical = pipelines.filter { it.start.x == it.end.x || it.start.y == it.end.y }
    val total = mutableMapOf<Point, Int>()
    horizontalOrVertical.forEach {
      val x1 = min(it.start.x, it.end.x)
      val x2 = max(it.start.x, it.end.x)
      val y1 = min(it.start.y, it.end.y)
      val y2 = max(it.start.y, it.end.y)
      (x1..x2).map { x ->
        (y1..y2).map { y ->
          val point = Point(x, y)
          total.computeIfAbsent(point) { 0 }
          total[point] = total[point]!!.inc()
        }
      }
    }
    println(total)
    return total.count { it.value >= 2 }.toString()
  }

  override suspend fun partTwo(): String {
    val pipelines = lines.map { line ->
      val (x1, y1, x2, y2) = re.matchEntire(line)!!.destructured.toList().map { it.toInt() }
      Pipeline(Point(x1, y1), Point(x2, y2))
    }
    val points = pipelines
      .flatMap {
        val diff = it.end - it.start
        val max = max(abs(diff.x), abs(diff.y))
        val direction = Point(diff.x.sign, diff.y.sign)
        (0..max).map { i ->
          it.start + direction * i
        }
      }
      .groupingBy { it }
      .eachCount()
      .count { it.value >= 2 }
    return points.toString()
  }

}

fun main() = runBlocking {
  val input = PuzzleInput.p1Test(Year.Y2021, Day.D05)
  PuzzleRunner.run(Y2021D05(input))
  PuzzleRunner.run(Y2021D05())
}
