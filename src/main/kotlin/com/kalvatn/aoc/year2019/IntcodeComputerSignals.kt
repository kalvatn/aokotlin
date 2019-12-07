package com.kalvatn.aoc.year2019

import kotlinx.coroutines.delay


class IntcodeComputerSignals(
        private val name: String,
        private val phase: Int,
        private val program: List<Int>,
        private val initialPointerPosition: Int = 0
) {

    private var pointer = initialPointerPosition
    private var memory = program.toMutableList()
    private var receiver: IntcodeComputerSignals? = null

    var receiveFrom:IntcodeComputerSignals? = null
    var sendTo:IntcodeComputerSignals? = null

    var input: Int? = null

    suspend fun receive(input: Int) {
//        println("$name received $input")
        this.input = input
    }
    suspend fun send(input:Int) {
//        println("$name sending $input to ${sendTo?.name}")
        sendTo?.receive(input)
    }

    suspend fun waitForInput(): Int? {
        while(this.input == null) {
            delay(1)
        }
        return this.input!!
    }

    suspend fun process(): IntcodeProcessResult {
        var lastOutput = 0

        var phaseUsed = false

        while (pointer < memory.size) {
            val instructionInt = memory[pointer]
            val padded = instructionInt.toString().padStart(5, '0')
            val opcode = padded.takeLast(2).toInt()
            val modes = padded.take(3).map { "$it".toInt() }.reversed()

            when (OpCode.fromInt(opcode)) {
                OpCode.HALT -> {
//                    println("$name $lastOutput")
                    return IntcodeProcessResult(
                            memory = memory,
                            diagnosticCode = lastOutput,
                            name = name
                    )
                }
                OpCode.ADD -> {
                    val p1 = if (modes[0] == 0) memory[memory[pointer + 1]] else memory[pointer + 1]
                    val p2 = if (modes[1] == 0) memory[memory[pointer + 2]] else memory[pointer + 2]
                    val p3 = memory[pointer + 3]
                    memory[p3] = p1 + p2
                    pointer += 4
                }
                OpCode.MULTIPLY -> {
                    val p1 = if (modes[0] == 0) memory[memory[pointer + 1]] else memory[pointer + 1]
                    val p2 = if (modes[1] == 0) memory[memory[pointer + 2]] else memory[pointer + 2]
                    val p3 = memory[pointer + 3]
                    memory[p3] = p1 * p2
                    pointer += 4
                }
                OpCode.STORE -> {
                    val p1 = memory[pointer + 1]
                    if (!phaseUsed) {
                        phaseUsed = true
                        memory[p1] = phase
//                        println("$name STORE phase $phase")
                    } else {
                        this.input = waitForInput()
                        memory[p1] = this.input!!
//                        println("$name STORE input ${this.input}")
                        this.input = null
                    }

                    pointer +=2

                }
                OpCode.OUTPUT -> {
                    val p1 = if (modes[0] == 0) memory[memory[pointer + 1]] else memory[pointer + 1]
                    lastOutput = p1
//                    println("$name output $p1")
                    send(p1)
                    pointer += 2
                }
                OpCode.JUMP_IF_TRUE -> {
                    val p1 = if (modes[0] == 0) memory[memory[pointer + 1]] else memory[pointer + 1]
                    val p2 = if (modes[1] == 0) memory[memory[pointer + 2]] else memory[pointer + 2]
                    pointer = if (p1 != 0) p2 else pointer + 3
                }
                OpCode.JUMP_IF_FALSE -> {
                    val p1 = if (modes[0] == 0) memory[memory[pointer + 1]] else memory[pointer + 1]
                    val p2 = if (modes[1] == 0) memory[memory[pointer + 2]] else memory[pointer + 2]
                    pointer = if (p1 == 0) p2 else pointer + 3
                }
                OpCode.JUMP_IF_LESS_THAN -> {
                    val p1 = if (modes[0] == 0) memory[memory[pointer + 1]] else memory[pointer + 1]
                    val p2 = if (modes[1] == 0) memory[memory[pointer + 2]] else memory[pointer + 2]
                    val p3 = memory[pointer + 3]
                    memory[p3] = if (p1 < p2) 1 else 0
                    pointer += 4
                }
                OpCode.JUMP_IF_EQUALS -> {
                    val p1 = if (modes[0] == 0) memory[memory[pointer + 1]] else memory[pointer + 1]
                    val p2 = if (modes[1] == 0) memory[memory[pointer + 2]] else memory[pointer + 2]
                    val p3 = memory[pointer + 3]
                    memory[p3] = if (p1 == p2) 1 else 0
                    pointer += 4
                }
            }
        }
        return IntcodeProcessResult(
                name = name,
                memory = memory,
                diagnosticCode = lastOutput
        )
    }
}

