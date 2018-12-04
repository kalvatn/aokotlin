package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.common.Day
import com.kalvatn.aoc.common.PuzzleInput
import com.kalvatn.aoc.extensions.cycle

class Day01 : Day {

    constructor() : super(2018, 1)
    constructor(input: PuzzleInput) : super(2018, 1, input)

    private val changes: List<Int> = input.asIntegers()

    private tailrec fun calibrate(
            changes: Iterator<Int>,
            seen: MutableSet<Int> = mutableSetOf(),
            current: Int = 0
    ): Int {
        return if (!seen.add(current)) current else calibrate(changes, seen, current + changes.next())
    }

    override fun partOne(): String {
        return changes.sum().toString()
    }

    override fun partTwo(): String {
        return calibrate(changes.cycle().iterator()).toString()
    }


}


