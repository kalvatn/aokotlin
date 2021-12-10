package com.kalvatn.aoc.year2021

import com.kalvatn.aoc.common.model.Point
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2021
import com.kalvatn.aoc.core.model.Year
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking

class Y2021D09(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2021(Day.D09, input) {

  private val lines by lazy { this.input.lines }

  private val grid = lines.map { line -> line.map { it.digitToInt() } }

  fun lowPoints() =
    (grid.indices).flatMap { y ->
      (0 until grid.first().size).map { x ->
        val height = grid[y][x]
        val c = Point(x, y)
        val adj = c.adj4().mapNotNull {
          try {
            grid[it.y][it.x]
          } catch (e: Exception) {
            null
          }
        }
        if (adj.all { it > height }) c else null
      }
    }.filterNotNull()

  override suspend fun partOne(): String {
    val risk = lowPoints().map {
      grid[it.y][it.x].inc()
    }.sumOf { it }

    return "$risk"
  }

  fun basin(start: Point): Set<Point> {
    val queue = ArrayDeque<Point>()
    val seen = mutableSetOf<Point>()
    queue.add(start)
    val basin = mutableSetOf<Point>()
    basin.add(start)
    while (queue.isNotEmpty()) {
      val current = queue.removeFirst()
      if (current in seen) {
        continue
      }
      seen.add(current)
      val h1 = height(current)
      current.adj4().mapNotNull {
          try {
            grid[it.y][it.x]
            it
          } catch (e: Exception) {
            null
          }
        }
        .filter {
        val h2 = height(it)
        h2 in (h1 + 1)..8
      }.map {
        queue.add(it)
        basin.add(it)
      }
    }

    return basin
  }

  fun height(point: Point) = grid[point.y][point.x]

  override suspend fun partTwo(): String {
    val lows = lowPoints()
    val basins = lows.map {
      basin(it)
    }
    basins.forEach {
      println(it)
    }
    return basins.sortedBy { it.size }
      .also { println("${it.size} $it") }
      .takeLast(3)
      .also { println(it) }
      .map { it.size }
      .reduce(Int::times).toString()
  }

  companion object {
  }
}

fun main() = runBlocking {
  val input = PuzzleInput.p1Test(Year.Y2021, Day.D09)
  PuzzleRunner.run(Y2021D09(input))
  PuzzleRunner.run(Y2021D09())
}
