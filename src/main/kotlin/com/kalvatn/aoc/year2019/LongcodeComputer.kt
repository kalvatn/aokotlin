package com.kalvatn.aoc.year2019

import java.util.*


class LongcodeComputer(
        private val program: List<Long>
) {

    private var pointer = 0
    var memory = (program + (0..10000).map { 0L }).toMutableList()

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
        return when (mode) {
            0 -> memory[memory[index].toInt()]
            2 -> {
                memory[memory[index].toInt() + relativeBase]
//                memory[current.toInt() + relativeBase]
//                memory[memory[index+ relativeBase].toInt()]
            }
            1 -> memory[index]
            else -> error("invalid mode")
        }
    }

    fun write(to:Int, value:Long) {
        memory[to] = value
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
                    val p1 = getMemoryValue(pointer+1, modes[0])
                    println("$pointer $opcode $modes $p1 $relativeBase")
                    write(p1.toInt(), input.remove())
                    pointer += 2
                } else {
                    state = State.WAITING
                }
            }
            OpCode.OUTPUT -> {
                val p1 = getMemoryValue(pointer + 1, modes[0])
                println("$pointer $opcode $modes $p1 $relativeBase")
                output.add(p1)
                pointer += 2
            }
            OpCode.ADD -> {
                val p1 = getMemoryValue(pointer + 1, modes[0])
                val p2 = getMemoryValue(pointer + 2, modes[1])
                val p3 = getMemoryValue(pointer + 3, modes[2])
                println("$pointer $opcode $modes $p1 $p2 $p3 $relativeBase")
                write(p3.toInt(), p1 + p2)
                pointer += 4
            }
            OpCode.MULTIPLY -> {
                val p1 = getMemoryValue(pointer + 1, modes[0])
                val p2 = getMemoryValue(pointer + 2, modes[1])
                val p3 = getMemoryValue(pointer + 3, modes[2])
                println("$pointer $opcode $modes $p1 $p2 $p3 $relativeBase")
                write(p3.toInt(),p1 * p2)
                pointer += 4
            }
            OpCode.JUMP_IF_TRUE -> {
                val p1 = getMemoryValue(pointer + 1, modes[0])
                val p2 = getMemoryValue(pointer + 2, modes[1])
                println("$pointer $opcode $modes $p1 $p2 $relativeBase")
                pointer = if (p1 != 0L) {
                    p2.toInt()
                } else pointer + 3
            }
            OpCode.JUMP_IF_FALSE -> {
                val p1 = getMemoryValue(pointer + 1, modes[0])
                val p2 = getMemoryValue(pointer + 2, modes[1])
                println("$pointer $opcode $modes $p1 $p2 $relativeBase")
                pointer = if (p1 == 0L) {
                    p2.toInt()
                } else pointer + 3
            }
            OpCode.JUMP_IF_LESS_THAN -> {
                val p1 = getMemoryValue(pointer + 1, modes[0])
                val p2 = getMemoryValue(pointer + 2, modes[1])
                val p3 = getMemoryValue(pointer + 3, modes[2])
                println("$pointer $opcode $modes $p1 $p2 $p3 $relativeBase")
                write(p3.toInt(), if (p1 < p2) 1L else 0L)
                pointer += 4
            }
            OpCode.JUMP_IF_EQUALS -> {
                val p1 = getMemoryValue(pointer + 1, modes[0])
                val p2 = getMemoryValue(pointer + 2, modes[1])
                val p3 = getMemoryValue(pointer + 3, modes[2])
                println("$pointer $opcode $modes $p1 $p2 $p3 $relativeBase")
                write(p3.toInt(), if (p1 == p2) 1L else 0L)
                pointer += 4
            }
            OpCode.ADJUST_BASE -> {
                val p1 = getMemoryValue(pointer + 1, modes[0])
                println("$pointer $opcode $modes $p1 $relativeBase")
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
//        println(program)
//        println(output)
//        println(memory.toList())
        return this
    }
}
