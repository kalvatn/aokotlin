package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.year2019.AccessMode.*
import java.util.*

enum class State {
    RUNNING, WAITING, HALT
}

enum class AccessMode {
    READ,
    WRITE
}

enum class OpCode(val value: Int, vararg val accessModes: AccessMode) {
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
        fun fromInt(value: Int): OpCode {
            return BY_ID[value] ?: error("impossiburu")
        }
    }
}

class IntcodeComputer(
        private val program: List<Long>
) {

    private var pointer = 0
    var memory = initializeMemory()
        private set


    private val output = mutableListOf<Long>()
    private val input = ArrayDeque<Long>()
    internal var state = State.RUNNING

    var relativeBase = 0

    private fun initializeMemory() = program.toLongArray()

    private fun reset() {
        pointer = 0
        memory = initializeMemory()
        relativeBase = 0
    }

    fun input(input: Long) {
        this.input.add(input)
    }

    fun output() = this.output.toList()

    fun lastOutput() = this.output.last()

    fun findVerbNounPairThatProducesSolution(solution: Long): Pair<Int, Int> {
        for (verb in 1..99) {
            for (noun in 1..99) {
                if (findSolutionForVerbNounPair(verb, noun) == solution) {
                    return Pair(verb, noun)
                }
            }
        }
        throw IllegalArgumentException("no solution found")
    }

    fun findSolutionForVerbNounPair(verb: Int, noun: Int): Long {
        reset()
        memory[1] = verb.toLong()
        memory[2] = noun.toLong()
        return process().memory[0]
    }

    fun runDiagnostic(input: Long): Long {
        reset()
        this.input.add(input)
        return process().lastOutput()
    }

    private fun extendMemory(index: Int) {
        if (index > memory.size) {
            repeat((index + 2 - memory.size)) {
                memory += 0
            }
        }
    }

    private fun writeToMemory(index: Int, value: Long) {
        extendMemory(index)
        memory[index] = value
    }

    private fun readFromMemory(index: Int, mode: Int, accessMode: AccessMode = READ): Long {
        val valueAtIndex = memory[index]
        if (accessMode == READ && mode == 0 || mode == 2) {
            extendMemory(valueAtIndex.toInt())
        }
        return when (mode) {
            0 -> if (accessMode == READ) memory[valueAtIndex.toInt()] else valueAtIndex
            2 -> if (accessMode == READ) memory[valueAtIndex.toInt() + relativeBase] else valueAtIndex + relativeBase
            else -> valueAtIndex
        }
    }

    private fun step() {
        val instructionInt = memory[pointer]
        val padded = instructionInt.toString().padStart(5, '0')
        val opcode = padded.takeLast(2).toInt()
        val modes = padded.take(3).map { "$it".toInt() }.reversed()

        when (OpCode.fromInt(opcode)) {
            OpCode.HALT -> state = State.HALT
            OpCode.STORE -> {
                if (input.isNotEmpty()) {
                    val p1 = readFromMemory(pointer + 1, modes[0], WRITE)
                    writeToMemory(p1.toInt(), input.remove())
                    pointer += 2
                } else {
                    state = State.WAITING
                }
            }
            OpCode.OUTPUT -> {
                val p1 = readFromMemory(pointer + 1, modes[0])
                output.add(p1)
                pointer += 2
            }
            OpCode.ADD -> {
                val p1 = readFromMemory(pointer + 1, modes[0])
                val p2 = readFromMemory(pointer + 2, modes[1])
                val p3 = readFromMemory(pointer + 3, modes[2], WRITE)
                writeToMemory(p3.toInt(), p1 + p2)
                pointer += 4
            }
            OpCode.MULTIPLY -> {
                val p1 = readFromMemory(pointer + 1, modes[0])
                val p2 = readFromMemory(pointer + 2, modes[1])
                val p3 = readFromMemory(pointer + 3, modes[2], WRITE)
                writeToMemory(p3.toInt(), p1 * p2)
                pointer += 4
            }
            OpCode.JUMP_IF_TRUE -> {
                val p1 = readFromMemory(pointer + 1, modes[0])
                val p2 = readFromMemory(pointer + 2, modes[1])
                pointer = if (p1 != 0L) p2.toInt() else pointer + 3
            }
            OpCode.JUMP_IF_FALSE -> {
                val p1 = readFromMemory(pointer + 1, modes[0])
                val p2 = readFromMemory(pointer + 2, modes[1])
                pointer = if (p1 == 0L) p2.toInt() else pointer + 3
            }
            OpCode.JUMP_IF_LESS_THAN -> {
                val p1 = readFromMemory(pointer + 1, modes[0])
                val p2 = readFromMemory(pointer + 2, modes[1])
                val p3 = readFromMemory(pointer + 3, modes[2], WRITE)
                writeToMemory(p3.toInt(), if (p1 < p2) 1 else 0)
                pointer += 4
            }
            OpCode.JUMP_IF_EQUALS -> {
                val p1 = readFromMemory(pointer + 1, modes[0])
                val p2 = readFromMemory(pointer + 2, modes[1])
                val p3 = readFromMemory(pointer + 3, modes[2], WRITE)
                writeToMemory(p3.toInt(), if (p1 == p2) 1 else 0)
                pointer += 4
            }
            OpCode.ADJUST_BASE -> {
                val p1 = readFromMemory(pointer + 1, modes[0])
                relativeBase += p1.toInt()
                pointer += 2
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
