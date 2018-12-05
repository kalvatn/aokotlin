package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.common.Day
import com.kalvatn.aoc.common.PuzzleInput
import com.kalvatn.aoc.extensions.reductions

class Y2015D01(input: PuzzleInput? = null) : APuzzle2015(Day.D01, input) {

    private val changes: List<Int> = this.input.singleLine().map { parenToInt(it) }

    private fun parenToInt(paren: Char): Int {
        return when (paren) {
            '(' -> 1
            else -> -1
        }
    }


    override fun partOne(): String {
        return changes.sum().toString()
    }


    override fun partTwo(): String {
        return changes.reductions(0) { acc, i -> acc + i }.takeWhile { it != -1 }.count().toString()
    }


}

