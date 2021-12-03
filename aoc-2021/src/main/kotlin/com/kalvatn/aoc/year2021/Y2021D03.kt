package com.kalvatn.aoc.year2021

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2021
import com.kalvatn.aoc.core.model.Year
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking

class Y2021D03(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2021(Day.D03, input) {

  private val lines by lazy { this.input.lines }

  override suspend fun partOne(): String {
    val indexToBit = mutableMapOf<Int, MutableList<Int>>()
    lines.flatMap { number ->
      number.mapIndexed { index, c -> index to c }
    }.map {
      indexToBit.computeIfAbsent(it.first) { mutableListOf() }.add(it.second.digitToInt())
    }
    var gamma = ""
    var epsilon = ""
    indexToBit.forEach { (_, v) ->
      if (v.count { it == 0 } > v.count { it == 1 }) {
        gamma += "0"
        epsilon += "1"
      } else {
        gamma += "1"
        epsilon += "0"
      }
    }
    val a = gamma.toInt(2)
    val b = epsilon.toInt(2)
    return (a * b).toString()
  }

  override suspend fun partTwo(): String {
    var i = 0
    val linesRemaining = lines.toMutableList()
    while (linesRemaining.size > 1) {
      val count0 = linesRemaining.map { it[i] }.count { it == '0' }
      val count1 = linesRemaining.map { it[i] }.count { it == '1' }
      if (count1 >= count0) {
        linesRemaining.removeAll { it[i] != '1' }
      } else {
        linesRemaining.removeAll { it[i] != '0' }
      }
      i++
    }
    val oxygen = linesRemaining.first().toInt(2)

    val linesRemaining2 = lines.toMutableList()
    i = 0
    while (linesRemaining2.size > 1) {
      val count0 = linesRemaining2.map { it[i] }.count { it == '0' }
      val count1 = linesRemaining2.map { it[i] }.count { it == '1' }
      println(linesRemaining2)
      println("$i $count1 $count0")
      if (count0 <= count1) {
        linesRemaining2.removeAll {
          it[i] != '0'
        }
      } else {
        linesRemaining2.removeAll {
          it[i] != '1'
        }
      }
      i++
    }
    val co2 = linesRemaining2.first().toInt(2)

    println("oxygen $oxygen")
    println("co2 $co2")

    return (oxygen * co2).toString()
  }
}

fun main() = runBlocking {
  val input = PuzzleInput.p1Test(Year.Y2021, Day.D03)
  PuzzleRunner.run(Y2021D03(input))
  PuzzleRunner.run(Y2021D03())
}
