package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2015
import com.kalvatn.aoc.core.runner.PuzzleRunner
import com.kalvatn.aoc.exceptions.Impossiburu
import kotlinx.coroutines.runBlocking

class Y2015D07(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2015(Day.D07, input) {
  private val wires = mutableMapOf<String, String>()

  abstract class OP(val left: Int, val right: Int) {
    abstract fun apply(): Int
  }

  class VALUE(left: Int) : OP(left, 0) {
    override fun apply(): Int {
      return left
    }
  }

  class AND(left: Int, right: Int) : OP(left, right) {
    override fun apply(): Int {
      return left.and(right)
    }
  }

  class OR(left: Int, right: Int) : OP(left, right) {
    override fun apply(): Int {
      return left.or(right)
    }
  }

  class LSHIFT(left: Int, right: Int) : OP(left, right) {
    override fun apply(): Int {
      return left.shl(right)
    }
  }

  class RSHIFT(left: Int, right: Int) : OP(left, right) {
    override fun apply(): Int {
      return left.shr(right)
    }
  }

  class NOT(left: Int, right: Int = 0xffff) : OP(left, right) {
    override fun apply(): Int {
      return left.inv().and(right)
    }
  }

  private fun getWire(key: String): Int {
    if (key.toIntOrNull() != null) {
      return key.toInt()
    }

    val instruction = wires[key]!!.split(" ")

    val first = instruction.first()
    val last = instruction.last()
    val operation: OP = when (instruction.size) {
      1 -> VALUE(getWire(first))
      2 -> NOT(getWire(last), 0xffff)
      3 -> when (instruction[1]) {
        "AND" -> AND(getWire(first), getWire(last))
        "OR" -> OR(getWire(first), getWire(last))
        "LSHIFT" -> LSHIFT(getWire(first), getWire(last))
        "RSHIFT" -> RSHIFT(getWire(first), getWire(last))
        else -> throw Impossiburu()
      }
      else -> throw Impossiburu()
    }
    val result = operation.apply()
    wires[key] = result.toString()
    return result
  }

  private fun initialize(overrides: Map<String, String> = mapOf()) {
    this.input.lines.forEach {
      val (inputs, output) = it.split(" -> ")
      wires[output.trim()] = inputs.trim()
    }

    for (override in overrides) {
      wires[override.key] = override.value
    }
  }

  override suspend fun partOne(): String {
    initialize()
    return getWire("a").toString()
  }

  override suspend fun partTwo(): String {
    val b = partOne()
    initialize(mapOf("b" to b))
    return getWire("a").toString()
  }
}

fun main() = runBlocking {
  PuzzleRunner.run(Y2015D07())
}
