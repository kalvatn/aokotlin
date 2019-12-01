package com.kalvatn.aoc.year2019

enum class OpCode(
        val value: Int,
        val calc: (x: Int, y: Int) -> Int
) {
    ADD(1, fun(x, y) = x + y),
    MULTIPLY(2, fun(x, y) = x * y),
    HALT(99, fun(_, _) = 0);

    companion object {
        fun fromInt(value: Int): OpCode = values().firstOrNull { it.value == value }
                ?: throw IllegalArgumentException("no OpCode defined for value $value")
    }
}

class IntcodeComputer(
        private val initialProgram: List<Int> = listOf(),
        private val initialPointerPosition: Int = 0
) {

    private var pointer = initialPointerPosition

    private fun mutableMemory() = initialProgram.toMutableList()


    fun findVerbNounPairThatProducesSolution(solution:Int): Pair<Int, Int> {
        for (verb in 1..99) {
            for (noun in 1..99) {
                if (run(verb, noun) == solution) {
                    return Pair(verb, noun)
                }
            }
        }
        throw IllegalArgumentException("no solution found")
    }

    fun run(verb: Int, noun: Int): Int {
        return process(mapOf(1 to verb, 2 to noun))[pointer]
    }

    fun process(replace: Map<Int, Int> = mapOf()): List<Int> {
        val memory = mutableMemory()
        replace.forEach { (k, v) ->
            memory[k] = v
        }

        loop@ for (i in initialPointerPosition until memory.size - 1 step 4) {
            pointer = i
            when (val opCode = OpCode.fromInt(memory[pointer])) {
                OpCode.HALT ->
                    pointer = initialPointerPosition
                else -> {
                    val i1 = memory[pointer + 1]
                    val i2 = memory[pointer + 2]
                    val iOut = memory[pointer + 3]
                    memory[iOut] = opCode.calc(memory[i1], memory[i2])
                }
            }
        }
        return memory
    }
}