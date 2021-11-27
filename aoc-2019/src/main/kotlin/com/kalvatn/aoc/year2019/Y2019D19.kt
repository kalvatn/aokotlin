package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.common.model.Point
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2019
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking

class Y2019D19(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2019(Day.D19, input) {

  private val program by lazy { this.input.singleLineLongs() }

  override suspend fun partOne(): String {
    val pc = IntcodeComputer(program)
    val points = mutableMapOf<Point, Char>()
    (0 until 50).forEach { y ->
      (0 until 50).forEach { x ->
        val point = Point(x, y)
        pc.input(point.x.toLong())
        pc.input(point.y.toLong())
        pc.run()
        points[point] = if (pc.outputLast() == 0L) '.' else '#'
        pc.reset()
      }
    }

//        points.print {
//            print(points[it])
//        }

    return points.filterValues { it == '#' }.count().toString()
  }

  private val pc = IntcodeComputer(program)
  private fun process(x: Long, y: Long): Long {
    pc.reset()
    pc.input(x)
    pc.input(y)
    pc.run()
    return pc.outputLast()
  }

  override suspend fun partTwo(): String {
    val size = 100

    val allCorners = mutableListOf<List<Point>>()

    //TODO figure out how to calculate initial y (hardcoded size*5)
    loop@ for (y in size * 5..size * 10) {
      for (x in y * 2..(y * 3)) {
        val topleft = Point(x - size + 1, y - size + 1)
        val bottomleft = Point(x - size + 1, y)
        val bottomright = Point(x, y)
        val topright = Point(x, y - size + 1)
        val corners = listOf(
          topleft,
          bottomleft,
          bottomright,
          topright
        ).associateWith { process(it.x.toLong(), it.y.toLong()) }
        if (corners.values.all { it == 1L }) {
          allCorners.add(corners.keys.toList())
          return ((topleft.x * 10000) + topleft.y).toString()
        }
      }
    }
//        val points = mutableMapOf<Point, Long>()
//        allCorners.flatten().forEach {
//            points[it] = 1L
//        }
//        points.print {
//            if (it in allCorners.flatten()) {
//                print('O')
//            } else {
//                print(if (points[it] == 1L) '#' else '.')
//            }
//        }
    return ""
  }
}

fun main() = runBlocking {
  PuzzleRunner(listOf(Y2019D19())).run()
}
