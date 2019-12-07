package com.kalvatn.aoc.year2019

import java.util.*

enum class State {
    RUNNING, WAITING, HALT
}

enum class OpCode(val value: Int) {
    ADD(1),
    MULTIPLY(2),
    STORE(3),
    OUTPUT(4),
    JUMP_IF_TRUE(5),
    JUMP_IF_FALSE(6),
    JUMP_IF_LESS_THAN(7),
    JUMP_IF_EQUALS(8),
    HALT(99);

    companion object {
        private val BY_ID = values().map { it.value to it }.toMap()
        fun fromInt(value: Int): OpCode {
            return BY_ID[value] ?: error("impossiburu")
        }
    }
}

class IntcodeComputer(
        private val program: List<Int>
) {

    private var pointer = 0
    var memory = program.toIntArray()
        private set

    private val output = mutableListOf<Int>()
    private val input = ArrayDeque<Int>()
    internal var state = State.RUNNING

    private fun reset() {
        pointer = 0
        memory = program.toIntArray()
    }

    fun input(input:Int) {
        this.input.add(input)
    }
    fun output() = this.output.last()

    fun findVerbNounPairThatProducesSolution(solution: Int): Pair<Int, Int> {
        for (verb in 1..99) {
            for (noun in 1..99) {
                if (findSolutionForVerbNounPair(verb, noun) == solution) {
                    return Pair(verb, noun)
                }
            }
        }
        throw IllegalArgumentException("no solution found")
    }

    fun findSolutionForVerbNounPair(verb: Int, noun: Int): Int {
        reset()
        memory[1] = verb
        memory[2] = noun
        return process().memory[0]
    }

    fun runDiagnostic(input: Int): Int {
        reset()
        this.input.add(input)
        return process().output()
    }

    fun getMemoryValue(index: Int, mode: Int): Int {
        return if (mode == 0) memory[memory[index]] else memory[index]
    }

    private fun step() {
        val instructionInt = memory[pointer]
        val padded = instructionInt.toString().padStart(5, '0')
        val opcode = padded.takeLast(2).toInt()
        val modes = padded.take(3).map { "$it".toInt() }.reversed()

        when (OpCode.fromInt(opcode)) {
            OpCode.HALT -> {
                state = State.HALT
            }
            OpCode.STORE -> {
                if (input.isNotEmpty()) {
                    val p1 = getMemoryValue(pointer + 1, 1)
                    memory[p1] = input.remove()
                    pointer += 2
                } else {
                    state = State.WAITING
                }
            }
            OpCode.OUTPUT -> {
                val p1 = getMemoryValue(pointer + 1, modes[0])
                output.add(p1)
                pointer += 2
            }
            OpCode.ADD -> {
                val p1 = getMemoryValue(pointer + 1, modes[0])
                val p2 = getMemoryValue(pointer + 2, modes[1])
                val p3 = getMemoryValue(pointer + 3, 1)
                memory[p3] = p1 + p2
                pointer += 4
            }
            OpCode.MULTIPLY -> {
                val p1 = getMemoryValue(pointer + 1, modes[0])
                val p2 = getMemoryValue(pointer + 2, modes[1])
                val p3 = getMemoryValue(pointer + 3, 1)
                memory[p3] = p1 * p2
                pointer += 4
            }
            OpCode.JUMP_IF_TRUE -> {
                val p1 = getMemoryValue(pointer + 1, modes[0])
                val p2 = getMemoryValue(pointer + 2, modes[1])
                pointer = if (p1 != 0) p2 else pointer + 3
            }
            OpCode.JUMP_IF_FALSE -> {
                val p1 = getMemoryValue(pointer + 1, modes[0])
                val p2 = getMemoryValue(pointer + 2, modes[1])
                pointer = if (p1 == 0) p2 else pointer + 3
            }
            OpCode.JUMP_IF_LESS_THAN -> {
                val p1 = getMemoryValue(pointer + 1, modes[0])
                val p2 = getMemoryValue(pointer + 2, modes[1])
                val p3 = getMemoryValue(pointer + 3, 1)
                memory[p3] = if (p1 < p2) 1 else 0
                pointer += 4
            }
            OpCode.JUMP_IF_EQUALS -> {
                val p1 = getMemoryValue(pointer + 1, modes[0])
                val p2 = getMemoryValue(pointer + 2, modes[1])
                val p3 = getMemoryValue(pointer + 3, 1)
                memory[p3] = if (p1 == p2) 1 else 0
                pointer += 4
            }
        }
    }

    fun process(): IntcodeComputer {
        state = State.RUNNING
        while (state == State.RUNNING) {
            step()
        }
        return this
    }
}
