package com.kalvatn.aoc.year2016

import com.kalvatn.aoc.common.model.Direction
import com.kalvatn.aoc.common.model.Point
import com.kalvatn.aoc.core.model.AbstractTask
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Input
import com.kalvatn.aoc.core.model.NoState
import com.kalvatn.aoc.core.model.Output
import com.kalvatn.aoc.core.model.PartResult
import com.kalvatn.aoc.core.model.Year
import com.kalvatn.aoc.exceptions.Impossiburu
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking

class Y2016D02 : AbstractTask<String, String, NoState>(Year.Y2016, Day.D02) {


  private fun parseInstructions(input: Input): List<List<Direction>> =
    input.lines().map { line ->
      line.map {
        when (it) {
          'U' -> Direction.NORTH
          'D' -> Direction.SOUTH
          'L' -> Direction.WEST
          'R' -> Direction.EAST
          else -> throw Impossiburu()
        }
      }
    }

  fun processInstructions(numpad: Array<CharArray>, start: Point, instructions: List<List<Direction>>) =
    instructions.fold("") { digits, directions ->
      val endpos = directions.fold(start) { current, direction ->
        val next = current.plus(direction.toPointDiff())
        val isWithinBounds = next.x >= 0 && next.y >= 0 && next.x < numpad.size && next.y < numpad.size
        val isBlank = isWithinBounds && numpad[next.y][next.x] == '.'
        if (isWithinBounds && !isBlank) {
          next
        } else {
          current
        }
      }
      digits + numpad[endpos.y][endpos.x]
    }


  override suspend fun p1(input: Input): PartResult<String> {
    return PartResult.of {
      val numpad = arrayOf(
        charArrayOf('1', '2', '3'),
        charArrayOf('4', '5', '6'),
        charArrayOf('7', '8', '9')
      )
      val start = Point(1, 1)
      val instructions = parseInstructions(input)
      processInstructions(numpad, start, instructions)
    }
  }

  override suspend fun p2(input: Input): PartResult<String> {
    return PartResult.of {
      val numpad: Array<CharArray> = arrayOf(
        charArrayOf('.', '.', '1', '.', '.'),
        charArrayOf('.', '2', '3', '4', '.'),
        charArrayOf('5', '6', '7', '8', '9'),
        charArrayOf('.', 'A', 'B', 'C', '.'),
        charArrayOf('.', '.', 'D', '.', '.'),
      )
      val start = Point(0, 2)
      val instructions = parseInstructions(input)
      processInstructions(numpad, start, instructions)
    }
  }

  override suspend fun solve(input: Input): Output<String, String> = coroutineScope {
    Output.of(year, day) {
      runBlocking {
        val (p1, p2) = awaitAll(
          async { p1(input) },
          async { p2(input) }
        )
        Pair(p1, p2)
      }
    }
  }

}

fun main() = runBlocking {
  val task = Y2016D02()
  task.solve(Input.forDay(Year.Y2016, Day.D02)).print()
}
