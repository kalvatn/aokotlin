package com.kalvatn.aoc.year2019

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
        fun fromInt(value: Int): OpCode {
            return values().firstOrNull { it.value == value }
                    ?: throw IllegalArgumentException("no OpCode defined for value $value")
        }
    }
}

data class IntcodeProcessResult(
        val memory: List<Int>,
        val diagnosticCode: Int
) {
    fun getMemoryValue(key: Int): Int {
        return memory[key]
    }
}

class IntcodeComputer(
        private val program: List<Int>,
        private val initialPointerPosition: Int = 0
) {

    private var pointer = initialPointerPosition
    private var memory = program.toMutableList()

    private fun reset() {
        pointer = initialPointerPosition
        memory = program.toMutableList()
    }

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
        return process().getMemoryValue(0)
    }

    fun runDiagnostic(input: Int): Int {
        reset()
        return process(input).diagnosticCode
    }

    fun runPhase(first:Int, second:Int):Int {
        reset()
        return process(first, second).diagnosticCode
    }

    fun process(inputInstructionValue: Int = 1, input2:Int = -1): IntcodeProcessResult {
        var lastOutput = 0

        var input1Used = false

        while (pointer < memory.size) {
            val instructionInt = memory[pointer]
            val padded = instructionInt.toString().padStart(5, '0')
            val opcode = padded.takeLast(2).toInt()
            val modes = padded.take(3).map { "$it".toInt() }.reversed()

            when (OpCode.fromInt(opcode)) {
                OpCode.HALT -> {
                    return IntcodeProcessResult(
                            memory = memory,
                            diagnosticCode = lastOutput
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
                    memory[p1] = if (!input1Used) inputInstructionValue else input2
                    input1Used = true
                    pointer += 2

                }
                OpCode.OUTPUT -> {
                    val p1 = if (modes[0] == 0) memory[memory[pointer + 1]] else memory[pointer + 1]
                    lastOutput = p1
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
                memory = memory,
                diagnosticCode = lastOutput
        )
    }
}

