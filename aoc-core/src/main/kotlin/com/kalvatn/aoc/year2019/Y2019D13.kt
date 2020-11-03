package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.common.model.Point
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2019
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking

class Y2019D13(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2019(Day.D13, input) {

  private val program by lazy { this.input.singleLineLongs() }

  override suspend fun partOne(): String {
    val pc = IntcodeComputer(program).run()
    return outputToState(pc).values.count { it == 2 }.toString()
  }

  private fun outputToState(pc: IntcodeComputer): Map<Point, Int> {
    return pc.output().map { it.toInt() }.chunked(3).map {
      Point(it[0], it[1]) to it[2]
    }.toMap()
  }

  override suspend fun partTwo(): String {
    val pc = IntcodeComputer(program)
    pc.replaceIndex(0, 2)
    pc.run()
    val tiles = outputToState(pc).toMutableMap()

    pc.input(0)
    do {
      pc.run()
      outputToState(pc).map { (k, v) ->
        tiles[k] = v
      }
      val paddle = tiles.filter { it.value == 3 }.keys.first()
      val ball = tiles.filter { it.value == 4 }.keys.first()
      if (ball.x > paddle.x) {
        pc.input(1)
      } else {
        pc.input(-1)
      }
      pc.clearOutput()
//            printGame(tiles)
//            delay(100)
    } while (pc.state() != State.HALT)

    return tiles[Point(-1, 0)].toString()
  }

  @Suppress("unused")
  private fun printGame(tiles: Map<Point, Int>) {
    val xMax = tiles.keys.maxByOrNull { it.x }!!.x
    val yMax = tiles.keys.maxByOrNull { it.y }!!.y
    (0..yMax).forEach { y ->
      (0..xMax).forEach { x ->
        val tile = when (tiles[Point(x, y)]) {
          0 -> ' '
          1 -> if (y > 0) '█' else '_'
          2 -> '#'
          3 -> '_'
          4 -> 'o'
          else -> error("impossiburu")
        }
        print(tile)
      }
      println()
    }
  }
}

fun main() = runBlocking {
  PuzzleRunner(listOf(Y2019D13())).run()
}
