package com.kalvatn.aoc.year2019

import java.util.*


class LongcodeComputer(
        private val program: List<Long>
) {

    private var pointer = 0
    var memory = program.toMutableList()

    private val output = mutableListOf<Long>()
    private val input = ArrayDeque<Long>()
    internal var state = State.RUNNING

    var relativeBase = 0

    fun reset() {
        pointer = 0
        relativeBase = 0
        memory = program.toMutableList()
    }

    fun input(input: Long) {
        this.input.add(input)
    }

    fun output() = this.output


    fun getMemoryValue(index: Int, mode: Int): Long {
        val current = memory[index]
        return when (mode) {
            0 -> memory[current.toInt()]
            2 -> {
                memory[current.toInt() + relativeBase]
            }
            1 -> current
            else -> error("invalid mode")
        }
    }

    fun write(to:Int, value:Long) {
        if (to >= memory.size) {
            (0 until to-memory.size).forEach { _ ->
                memory.add(0)
            }
            memory.add(value)
        } else {
            memory[to] = value
        }
    }

    private fun step() {
        val instructionLong = memory[pointer]
        val padded = instructionLong.toString().padStart(5, '0')
        val opcode = padded.takeLast(2).toInt()
        val modes = padded.take(3).map { "$it".toInt() }.reversed()


        val fromInt = OpCode.fromInt(opcode)
        when (fromInt) {
            OpCode.HALT -> {
                state = State.HALT
            }
            OpCode.STORE -> {
                if (input.isNotEmpty()) {
//                    val p1 = getMemoryValue(pointer + 1, modes[0])
                    val p1 = memory[pointer+1]+relativeBase
//                    println(memory[pointer+1])
//                    println(memory[pointer+1]+relativeBase)
                    println("STORE ${input.first()} -> $p1")

                    println(memory.toList())
                    write(p1.toInt(), input.remove())
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
                val p3 = getMemoryValue(pointer + 3, if (modes[2] != 0) modes[2] else 1)
                println("ADD $p1 $p2 (${p1+p2}) -> $p3")
                write(p3.toInt(), p1 + p2)
                pointer += 4
            }
            OpCode.MULTIPLY -> {
                val p1 = getMemoryValue(pointer + 1, modes[0])
                val p2 = getMemoryValue(pointer + 2, modes[1])
                val p3 = getMemoryValue(pointer + 3, if (modes[2] != 0) modes[2] else 1)
                println("MUL $p1 $p2 (${p1*p2}) -> $p3")
                write(p3.toInt(),p1 * p2)
                pointer += 4
            }
            OpCode.JUMP_IF_TRUE -> {
                val p1 = getMemoryValue(pointer + 1, modes[0])
                val p2 = getMemoryValue(pointer + 2, modes[1])
                pointer = if (p1 != 0L) {
                    println("JUMP TRUE -> $p2")
                    p2.toInt()
                } else pointer + 3
            }
            OpCode.JUMP_IF_FALSE -> {
                val p1 = getMemoryValue(pointer + 1, modes[0])
                val p2 = getMemoryValue(pointer + 2, modes[1])
                pointer = if (p1 == 0L) {
                    println("JUMP FALSE -> $p2")
                    p2.toInt()
                } else pointer + 3
            }
            OpCode.JUMP_IF_LESS_THAN -> {
                val p1 = getMemoryValue(pointer + 1, modes[0])
                val p2 = getMemoryValue(pointer + 2, modes[1])
                val p3 = getMemoryValue(pointer + 3, if (modes[2] != 0) modes[2] else 1)
                println("LESS THAN $p1 $p2 -> $p3")
                write(p3.toInt(), if (p1 < p2) 1L else 0L)
                pointer += 4
            }
            OpCode.JUMP_IF_EQUALS -> {
                val p1 = getMemoryValue(pointer + 1, modes[0])
                val p2 = getMemoryValue(pointer + 2, modes[1])
                val p3 = getMemoryValue(pointer + 3, if (modes[2] != 0) modes[2] else 1)
                println("EQUALS $p1 $p2 -> $p3")
                write(p3.toInt(), if (p1 == p2) 1L else 0L)
                pointer += 4
            }
            OpCode.ADJUST_BASE -> {
                val p1 = getMemoryValue(pointer + 1, modes[0])
                println("RELATIVE BASE $relativeBase + $p1 -> ${relativeBase+p1}")
                relativeBase += p1.toInt()
                pointer += 2
            }
        }
//        println("$instructionLong -> ${memory.toList()}")
    }

    fun process(): LongcodeComputer {
        state = State.RUNNING
        while (state == State.RUNNING) {
            step()
        }
        println(program)
        println(output)
        println(memory.toList())
        return this
    }
}
