package com.kalvatn.aoc.year2021

import com.kalvatn.aoc.common.model.Point
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2021
import com.kalvatn.aoc.core.runner.PuzzleRunner
import com.kalvatn.aoc.exceptions.Impossiburu
import kotlinx.coroutines.runBlocking

class Y2021D02(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2021(Day.D02, input) {

  private val instructions by lazy {
    this.input.lines.map {
      val (dir, count) = it.split(" ")
      Pair(dir, count.toInt())
    }
  }

  override suspend fun partOne(): String {
    val pos = instructions.fold(Point(0, 0)) { acc, cmd ->
      val (dir, count) = cmd
      when (dir) {
        "forward" -> acc.copy(x = acc.x + count)
        "up" -> acc.copy(y = acc.y - count)
        "down" -> acc.copy(y = acc.y + count)
        else -> throw Impossiburu()
      }
    }
    return (pos.x * pos.y).toString()
  }

  override suspend fun partTwo(): String {
    val (pos, _) = instructions.fold(Pair(Point(0, 0), 0)) { acc, cmd ->
      val (pos, aim) = acc
      val (dir, count) = cmd
      when (dir) {
        "forward" -> {
          val updatedPos = pos.copy(
            x = pos.x + (count),
            y = pos.y + (count * aim)
          )
          Pair(updatedPos, aim)
        }
        "up" -> Pair(pos, aim - count)
        "down" -> Pair(pos, aim + count)
        else -> throw Impossiburu()
      }
    }
    return (pos.x * pos.y).toString()
  }
}

fun main() = runBlocking {
  PuzzleRunner.run(Y2021D02())
}
