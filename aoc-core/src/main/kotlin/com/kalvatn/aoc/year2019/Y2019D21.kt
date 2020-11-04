package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2019
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking

class Y2019D21(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2019(Day.D21, input) {

  private val program by lazy { this.input.singleLineLongs() }

  override suspend fun partOne(): String {
    val pc = IntcodeComputer(program)
    val cmds = listOf(
      /**
       *  jump = if (hole1 || hole2 || hole3) and ground4
       *  jump = if (!A OR !B OR !C) AND D
       *  TODO FIXME XXX generate all permutations to not have to think about this.. feasible?
       */
      "NOT A J", // JUMP = HOLE1
      "NOT B T", // TEMP = HOLE2
      "OR T J", // JUMP = (HOLE1 OR HOLE2)
      "NOT C T", // TEMP = HOLE3
      "OR T J", // JUMP = (HOLE1 OR HOLE2 OR HOLE3)
      "AND D J", // JUMP = GROUND3 AND (HOLE1 OR HOLE2 OR HOLE3)
      "WALK"
    )
    pc.run()
    cmds.forEach { cmd ->
      cmd.map { it.toLong() }.forEach {
        pc.input(it)
      }
      pc.input('\n'.toLong())
    }
    pc.run()
    val message = pc.output().map { it.toChar() }.joinToString("")
    if (message.contains("Didn't make it across:")) {
      println(message)
    } else {
      return pc.outputLast().toString()
    }
    error(message)
  }

  override suspend fun partTwo(): String {
    val pc = IntcodeComputer(program)
    val cmds = listOf(
      /**
       * 12345678
       * ABCDEFGH
       *  jump = ((hole1 || hole2 || hole3) and ground4) and (ground5 || ground8)
       *  jump = ((!A OR !B OR !C) AND D) AND (E)
       *  TODO FIXME XXX generate all permutations to not have to think about this.. feasible?
       */
      "NOT A J", // JUMP = HOLE1
      "NOT B T", // TEMP = HOLE2
      "OR T J", // JUMP = (HOLE1 OR HOLE2)
      "NOT C T", // TEMP = HOLE3
      "OR T J", // JUMP = (HOLE1 OR HOLE2 OR HOLE3)
      "AND D J", // JUMP = GROUND3 AND (HOLE1 OR HOLE2 OR HOLE3)

      "NOT E T", // TEMP = HOLE5
      "NOT T T", // TEMP = GROUND5
      "OR H T", // TEMP = (GROUND5 OR GROUND8)
      "AND T J", // JUMP = GROUND3 AND (HOLE1 OR HOLE2 OR HOLE3) AND (GROUND5 OR GROUND8)
      "RUN"
    )
    pc.run()
    cmds.forEach { cmd ->
      cmd.map { it.toLong() }.forEach {
        pc.input(it)
      }
      pc.input('\n'.toLong())
    }
    pc.run()
    val message = pc.output().map { it.toChar() }.joinToString("")
    if (message.contains("Didn't make it across:")) {
      println(message)
    } else {
      return pc.outputLast().toString()
    }
    return "0"
  }
}

fun main() = runBlocking {
  PuzzleRunner(listOf(Y2019D21())).run()
}
