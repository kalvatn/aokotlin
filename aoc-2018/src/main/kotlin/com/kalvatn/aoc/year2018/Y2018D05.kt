package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2018
import kotlinx.coroutines.runBlocking
import java.util.Stack

class Y2018D05(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2018(Day.D05, input) {

  private fun Char.reacts(b: Char): Boolean {
    return this != b && this.equals(b, ignoreCase = true)
  }

  private fun react(string: Iterable<Char>): Iterable<Char> {
    return string.fold(Stack()) { acc, c ->
      if (!acc.isEmpty() && acc.last().reacts(c)) {
        acc.pop()
      } else {
        acc.push(c)
      }
      acc
    }
  }

  private val polymer: String = this.input.singleLine()
  private val part1: Iterable<Char> = react(polymer.asIterable())

  override suspend fun partOne(): String {
    return react(polymer.asIterable()).count().toString()
  }

  override suspend fun partTwo(): String {
    return ('a'..'z').map { it ->
      react(part1.filter { c -> !c.equals(it, ignoreCase = true) }).count()
    }.minOrNull().toString()
  }
}

fun main() = runBlocking {
  Y2018D05().run()
}
