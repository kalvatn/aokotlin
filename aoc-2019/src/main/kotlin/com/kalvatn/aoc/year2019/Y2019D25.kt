package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2019
import com.kalvatn.aoc.core.runner.PuzzleRunner
import com.kalvatn.aoc.extensions.extractIntegers
import kotlinx.coroutines.runBlocking

fun List<Long>.toAscii() = this.map { it.toChar() }.joinToString("")

fun IntcodeComputer.inputCmd(s: String) {
  println("'$s'")
  s.map { it.toLong() }.also {
    println(it)
  }.forEach {
    input(it)
  }
  input('\n'.toLong())
  run()
}

class Y2019D25(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2019(Day.D25, input) {

  private val program by lazy { this.input.singleLineLongs() }

  override suspend fun partOne(): String {
    val pc = IntcodeComputer(program)
    pc.run()
//        // manual solution
//        loop@ while (true) {
//            val message = pc.output().toAscii()
//            println(message)
//            when (val cmd = readLine()!!) {
//                "reset" -> pc.reset()
//                "exit" -> break@loop
//                "help" -> println(message)
//                else -> pc.inputCmd(cmd)
//            }
//        }
    val cmds = listOf(
      "south",
      "take mouse",
      "north",
      "west",
      "north",
      "north",
      "west",
      "take semiconductor",
      "east",
      "south",
      "west",
      "south",
      "take hypercube",
      "north",
      "east",
      "south",
      "west",
      "take antenna",
      "west",
      "south",
      "south",
      "south"
    )
    cmds.forEach {
      pc.inputCmd(it)
    }
    return pc.output().toAscii().trim().split("\n").last().extractIntegers().first().toString()
  }

  override suspend fun partTwo(): String {
    return "https://adventofcode.com/2019/day/25/answer"
  }
}

fun main() = runBlocking {
  PuzzleRunner(listOf(Y2019D25())).run()
}
