package com.kalvatn.aoc.year2021

import com.kalvatn.aoc.common.model.Point
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2021
import com.kalvatn.aoc.core.runner.PuzzleRunner
import com.kalvatn.aoc.extensions.cartesianProduct
import kotlinx.coroutines.runBlocking

class Y2021D11(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2021(Day.D11, input) {

  companion object {
    const val OCTOPUS_MAX_LEVEL = 9
    const val STEPS_PART_ONE = 100
  }

  private val initialGrid by lazy { this.input.lines.map { line -> line.map { it.digitToInt() } } }

  private val bounds = Pair(initialGrid.first().indices, initialGrid.indices)

  private val points = cartesianProduct(bounds.first, bounds.second).map { (y, x) ->
    Point(x, y)
  }.toSet()

  private fun step(grid: List<MutableList<Int>>): Pair<List<MutableList<Int>>, Int> {
    val flashing = mutableSetOf<Point>()
    val queue = ArrayDeque<Point>()
    points.forEach {
      grid[it.y][it.x] += 1
    }
    queue.addAll(points.filter { grid[it.y][it.x] > OCTOPUS_MAX_LEVEL })
    while (queue.isNotEmpty()) {
      val pos = queue.removeFirst()
      val level = grid[pos.y][pos.x]
      grid[pos.y][pos.x] = when {
        level.inc() > OCTOPUS_MAX_LEVEL -> {
          flashing.add(pos)
          queue.addAll(pos.adj8(bounds))
          0
        }
        else -> level.inc()
      }
      queue.removeAll(flashing)
    }
    return Pair(grid, flashing.size)
  }

  override suspend fun partOne(): String {
    val grid = initialGrid.map { it.toMutableList() }
    val totalFlashes = generateSequence(Pair(grid, 0)) {
      val next = step(it.first)
      Pair(next.first, it.second + next.second)
    }.drop(STEPS_PART_ONE)
      .take(1).first().second
    return "$totalFlashes"
  }

  override suspend fun partTwo(): String {
    val grid = initialGrid.map { it.toMutableList() }
    val steps = generateSequence(Pair(grid, 0)) {
      val next = step(it.first)
      Pair(next.first, next.second)
    }.takeWhile { it.second != points.size }
      .toList().size
    return "$steps"
  }
}

fun main() = runBlocking {
  PuzzleRunner.run(Y2021D11())
}
