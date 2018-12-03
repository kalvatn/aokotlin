package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.common.Day
import com.kalvatn.aoc.common.PuzzleInput
import com.kalvatn.aoc.extensions.reductions

class Day01 : Day {

    constructor() : super(2015, 1)
    constructor(input: PuzzleInput) : super(2018, 1, input)


    private fun parenToInt(paren: Char): Int {
        return when (paren) {
            '(' -> 1
            else -> -1
        }
    }

    private val changes = input.singleLine().map { parenToInt(it) }


    override fun partOne(): String {
        return changes.sum().toString()
    }


    override fun partTwo(): String {
        return changes.reductions(0) { acc, i -> acc + i }.takeWhile { it != -1 }.count().toString()
    }


}


