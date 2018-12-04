package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.common.Day
import com.kalvatn.aoc.common.PuzzleInput

class Y2015D07(input: PuzzleInput? = null) : APuzzle2015(Day.D07, input) {
    private val reg = mutableMapOf<String, String>()
    private val results = mutableMapOf<String, Int>()

    private fun getWire(key: String): Int {
        if (key.toIntOrNull() != null) {
            return key.toInt()
        }
        if (!results.containsKey(key)) {
            val operation = reg[key]!!.split(" ")
            val result = if (operation.size == 1) {
                getWire(operation[0])
            } else {
                val op = operation[operation.size - 2]
                when (op) {
                    "AND" -> getWire(operation[0]).and(getWire(operation[2]))
                    "OR" -> getWire(operation[0]).or(getWire(operation[2]))
                    "LSHIFT" -> getWire(operation[0]).shl(getWire(operation[2]))
                    "RSHIFT" -> getWire(operation[0]).shr(getWire(operation[2]))
                    "NOT" -> getWire(operation[1]).inv().and(0xffff)
                    else -> 0
                }
            }
            results[key] = result
        }
        return results[key]!!

    }

    private fun initWires(overrides: Map<String, String> = mapOf()) {
        this.reg.clear()
        this.results.clear()
        this.input.lines.forEach {
            val (inputs, output) = it.split(" -> ")
            reg[output.trim()] = inputs.trim()
        }

        for (override in overrides) {
            reg[override.key] = override.value
        }

    }

    override fun partOne(): String {
        initWires()
        return getWire("a").toString()
    }


    override fun partTwo(): String {
        val b = partOne()
        initWires(mapOf("b" to b))
        return getWire("a").toString()
    }

}
