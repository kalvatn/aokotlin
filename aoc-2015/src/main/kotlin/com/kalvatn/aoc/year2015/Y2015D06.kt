package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2015
import com.kalvatn.aoc.core.runner.PuzzleRunner
import com.kalvatn.aoc.exceptions.Impossiburu
import com.kalvatn.aoc.utils.flatten
import com.kalvatn.aoc.utils.intArray2D
import kotlinx.coroutines.runBlocking

class Y2015D06(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2015(Day.D06, input) {
  enum class Action {
    ON, OFF, TOGGLE;

    companion object {
      fun fromString(string: String): Action {
        return when (string) {
          "turn on" -> ON
          "turn off" -> OFF
          "toggle" -> TOGGLE
          else -> {
            throw Impossiburu()
          }
        }
      }
    }
  }

  data class Command(val action: Action, val start: Pair<Int, Int>, val end: Pair<Int, Int>) {
    companion object {
      fun fromString(string: String): Command {
        return "^(turn on|toggle|turn off) (\\d+),(\\d+) through (\\d+),(\\d+)"
          .toRegex()
          .matchEntire(string)
          ?.destructured
          ?.let { (action, sx, sy, ex, ey) ->
            Command(Action.fromString(action), Pair(sx.toInt(), sy.toInt()), Pair(ex.toInt(), ey.toInt()))
          }!!
      }
    }
  }

  private fun applyCommands(valueFn: (Action, Int) -> Int): Array<IntArray> {
    val commands = this.input.lines.map { Command.fromString(it) }
    val intArray2D = intArray2D(1000)
    for (command in commands) {
      (command.start.first..command.end.first).forEach { i ->
        (command.start.second..command.end.second).forEach { j ->
          intArray2D[i][j] = valueFn(command.action, intArray2D[i][j])
        }
      }
    }
    return intArray2D
  }

  override suspend fun partOne(): String {
    return applyCommands { action: Action, current: Int ->
      when (action) {
        Action.ON -> 1
        Action.OFF -> 0
        Action.TOGGLE -> when (current) {
          0 -> 1
          else -> 0
        }
      }
    }.flatten().count { it == 1 }.toString()
  }

  override suspend fun partTwo(): String {
    return applyCommands { action: Action, current: Int ->
      when (action) {
        Action.ON -> current.inc()
        Action.OFF -> when (current) {
          0 -> 0
          else -> current.dec()
        }
        Action.TOGGLE -> current + 2
      }
    }.flatten().sum().toString()
  }
}

fun main() = runBlocking {
  PuzzleRunner.run(Y2015D06())
}
