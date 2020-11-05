package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.year2019.AccessMode.READ
import com.kalvatn.aoc.year2019.AccessMode.WRITE
import com.kalvatn.aoc.year2019.ParameterMode.IMMEDIATE
import com.kalvatn.aoc.year2019.ParameterMode.POSITION
import com.kalvatn.aoc.year2019.ParameterMode.RELATIVE
import java.util.ArrayDeque

enum class State {
  RUNNING, WAITING_FOR_INPUT, HALT
}

enum class AccessMode {
  READ,
  WRITE
}

enum class ParameterMode(val value: Int) {
  POSITION(0),
  IMMEDIATE(1),
  RELATIVE(2);

  companion object {
    private val BY_ID = values().map { it.value to it }.toMap()
    fun fromInt(value: Int) = BY_ID[value] ?: error("no parameter mode with value $value")
  }
}

enum class Operation(val value: Int, vararg val accessModes: AccessMode) {
  ADD(1, READ, READ, WRITE),
  MULTIPLY(2, READ, READ, WRITE),
  STORE(3, WRITE),
  OUTPUT(4, READ),
  JUMP_IF_TRUE(5, READ, READ),
  JUMP_IF_FALSE(6, READ, READ),
  JUMP_IF_LESS_THAN(7, READ, READ, WRITE),
  JUMP_IF_EQUALS(8, READ, READ, WRITE),
  ADJUST_BASE(9, READ),
  HALT(99);

  companion object {
    private val BY_ID = values().map { it.value to it }.toMap()
    fun fromInt(value: Int) = BY_ID[value] ?: error("no opcode with value $value")
  }
}

class IntcodeComputer(private val program: List<Long>) {

  private val output: MutableList<Long> = mutableListOf()
  private val input: ArrayDeque<Long> = ArrayDeque()

  private var memory: LongArray = initializeMemory()
  private var pointer = 0L
  private var state = State.HALT
  private var relativeBase = 0

  fun state() = state
  fun memory() = memory.toList()
  fun output() = this.output.toList()
  fun inputQueue(): List<Long> = this.input.toList()
  fun outputLast() = this.output.last()
  fun clearOutput() = this.output.clear()

  fun input(input: Long): IntcodeComputer {
    this.input.add(input)
    return this
  }

  fun runDiagnostic(input: Long): Long {
    reset()
    input(input)
    return run().outputLast()
  }

  fun findVerbNounPairThatProducesSolution(solution: Long): Pair<Int, Int> {
    for (verb in 1..Operation.HALT.value) {
      for (noun in 1..Operation.HALT.value) {
        if (findSolutionForVerbNounPair(verb, noun) == solution) {
          return Pair(verb, noun)
        }
      }
    }
    throw IllegalArgumentException("no solution found")
  }

  fun findSolutionForVerbNounPair(verb: Int, noun: Int): Long {
    reset()
    writeToMemory(1, verb.toLong())
    writeToMemory(2, noun.toLong())
    return run().memory[0]
  }

  private fun initializeMemory() = program.toLongArray()

  fun reset() {
    pointer = 0
    memory = initializeMemory()
    relativeBase = 0
  }

  fun replaceIndex(index: Long, value: Long) {
    writeToMemory(index, value)
  }

  private fun writeToMemory(index: Long, value: Long) {
    try {
      extendMemory(index)
      memory[index.toInt()] = value
    } catch (e: IndexOutOfBoundsException) {
      println("$index $value ${memory.size}")
    }
  }

  private fun readFromMemory(index: Long): Long {
    extendMemory(index)
    return memory[index.toInt()]
  }

  private fun extendMemory(index: Long) {
    if (index >= memory.size) {
      repeat((index.toInt() + 2 - memory.size)) {
        memory += 0
      }
    }
  }

  private fun getParameters(operation: Operation, modes: List<Int>): List<Long> {
    val params = mutableListOf<Long>(0, 0, 0)
    operation.accessModes.forEachIndexed { i, rw ->
      val value = readFromMemory(pointer + i + 1)
      params[i] = when (val mode = ParameterMode.fromInt(modes[i])) {
        RELATIVE, POSITION -> {
          val newIndex = if (mode == RELATIVE) value + relativeBase else value
          if (rw == READ) readFromMemory(newIndex) else newIndex
        }
        IMMEDIATE -> value
      }
    }
    return params
  }

  private fun step() {
    val instructionInt = readFromMemory(pointer)
    val padded = instructionInt.toString().padStart(5, '0')
    val opcode = padded.takeLast(2).toInt()
    val modes = padded.take(3).map { Character.getNumericValue(it) }.reversed()

    val operation = Operation.fromInt(opcode)
    val (p1, p2, p3) = getParameters(operation, modes)

    val pointerIncrement = operation.accessModes.size + 1
    pointer += pointerIncrement
    when (operation) {
      Operation.STORE -> {
        if (input.isEmpty()) {
          pointer -= pointerIncrement
          state = State.WAITING_FOR_INPUT
        } else {
          writeToMemory(p1, input.remove())
        }
      }
      Operation.OUTPUT -> output.add(p1)
      Operation.ADD -> writeToMemory(p3, p1 + p2)
      Operation.MULTIPLY -> writeToMemory(p3, p1 * p2)
      Operation.JUMP_IF_TRUE -> if (p1 != 0L) pointer = p2
      Operation.JUMP_IF_FALSE -> if (p1 == 0L) pointer = p2
      Operation.JUMP_IF_LESS_THAN -> writeToMemory(p3, if (p1 < p2) 1 else 0)
      Operation.JUMP_IF_EQUALS -> writeToMemory(p3, if (p1 == p2) 1 else 0)
      Operation.ADJUST_BASE -> relativeBase += p1.toInt()
      Operation.HALT -> state = State.HALT
    }
  }

  fun run(): IntcodeComputer {
    state = State.RUNNING
    while (state == State.RUNNING) {
      step()
    }
    return this
  }
}
