package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.common.Day
import com.kalvatn.aoc.common.PuzzleInput
import com.kalvatn.extensions.cycle

class Day01 : Day(2018, 1) {

    @Suppress("RedundantOverride")
    override fun input(): PuzzleInput {
//        return PuzzleInput.ofCommaDelimited("+1, -1") // 0, 0
//        return PuzzleInput.ofCommaDelimited("+3, +3, +4, -2, -4") // 4, 10
//        return PuzzleInput.ofCommaDelimited("-6, +3, +8, +5, -6") // 4, 5
//        return PuzzleInput.ofCommaDelimited("+7, +7, -2, -7, -4") // 1, 14

        // https://www.reddit.com/r/adventofcode/comments/a20646/2018_day_1_solutions/eaukxu5/
//        return PuzzleInput.ofCommaDelimited("+10000000, -9999999") // 1, 10000000
        return super.input()
    }

    private val changes = input().asIntegerList()

    private tailrec fun calibrate(changes: Iterator<Int>, seen: MutableSet<Int> = mutableSetOf(), current: Int = 0): Int {
        return if (!seen.add(current)) current else calibrate(changes, seen, current + changes.next())
    }

    override fun partTwo(): String {
        return calibrate(changes.cycle()).toString()
    }

    override fun partOne(): String {
        return changes.sum().toString()
    }

}


