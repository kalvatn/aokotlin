package com.kalvatn.aoc.year2021

import com.kalvatn.aoc.common.model.Point
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2021
import com.kalvatn.aoc.core.model.Year
import com.kalvatn.aoc.core.runner.PuzzleRunner
import com.kalvatn.aoc.exceptions.Impossiburu
import kotlinx.coroutines.runBlocking

class Y2021D11(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2021(Day.D11, input) {

  private val initialGrid by lazy { this.input.lines.map { line -> line.map { it.digitToInt() } } }

  override suspend fun partOne(): String {
    val grid = initialGrid.map { it.toMutableList() }
    val bounds = Pair(grid.first().indices, grid.indices)
    val points = bounds.second.flatMap { y ->
        bounds.first.map { x ->
          Point(x, y)
        }
      }.toSet()
    var count = 0
//    println("before")
//    grid.forEach {
//      println(it)
//    }
    repeat(100) {
      val queue = ArrayDeque<Point>()
      points.map { grid[it.y][it.x] = grid[it.y][it.x].inc() }
      val hasflashed = mutableSetOf<Point>()
      val initialFlash = points.filter { grid[it.y][it.x] > 9 }.toMutableSet()
      queue.addAll(initialFlash)
      while(queue.isNotEmpty()) {
        val pos = queue.removeFirst()
        if (pos in hasflashed) {
          continue
        }
        val value = grid[pos.y][pos.x]
        val new = when {
          pos in initialFlash -> {
            hasflashed.add(pos)
            queue.addAll(pos.adj8(bounds))
            0
          }
          value.inc() > 9 -> {
            hasflashed.add(pos)
            queue.addAll(pos.adj8(bounds))
            0
          }
          else -> value.inc()
        }
        grid[pos.y][pos.x] = new
      }

//      println("step ${it.inc()}")
//      grid.forEach {
//        println(it)
//      }
      count+= hasflashed.size
    }
    return "$count"
  }

  override suspend fun partTwo(): String {
    val grid = initialGrid.map { it.toMutableList() }
    val bounds = Pair(grid.first().indices, grid.indices)
    val points = bounds.second.flatMap { y ->
      bounds.first.map { x ->
        Point(x, y)
      }
    }.toSet()
    var count = 0
//    println("before")
//    grid.forEach {
//      println(it)
//    }
    repeat(1000) {
      val queue = ArrayDeque<Point>()
      points.map { grid[it.y][it.x] = grid[it.y][it.x].inc() }
      val hasflashed = mutableSetOf<Point>()
      val initialFlash = points.filter { grid[it.y][it.x] > 9 }.toMutableSet()
      queue.addAll(initialFlash)
      while(queue.isNotEmpty()) {
        val pos = queue.removeFirst()
        if (pos in hasflashed) {
          continue
        }
        val value = grid[pos.y][pos.x]
        val new = when {
          pos in initialFlash -> {
            hasflashed.add(pos)
            queue.addAll(pos.adj8(bounds))
            0
          }
          value.inc() > 9 -> {
            hasflashed.add(pos)
            queue.addAll(pos.adj8(bounds))
            0
          }
          else -> value.inc()
        }
        grid[pos.y][pos.x] = new
      }

//      println("step ${it.inc()}")
//      grid.forEach {
//        println(it)
//      }
      if (hasflashed.size == points.size) {
        return it.inc().toString()
      }
      count+= hasflashed.size
    }
    throw Impossiburu()
  }

  companion object {
  }
}

fun main() = runBlocking {
  val input = PuzzleInput.p1Test(Year.Y2021, Day.D11)
  PuzzleRunner.run(Y2021D11(input))
//  PuzzleRunner.run(Y2021D11())
}
