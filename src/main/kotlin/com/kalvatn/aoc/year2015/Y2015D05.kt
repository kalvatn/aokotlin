package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.common.Day
import com.kalvatn.aoc.common.PuzzleInput
import com.kalvatn.aoc.extensions.*

class Y2015D05(input: PuzzleInput? = null) : APuzzle2015(Day.D05, input) {


    fun nicePartOne(string: String): Boolean {
        return string.hasNVowels(3)
                && string.hasConsecutiveLetters(1)
                && string.doesNotContain("ab", "cd", "pq", "xy")
    }

    fun nicePartTwo(string: String): Boolean {
        return string.hasNonOverlappingPair()
                && string.hasXYX()
    }

    override fun partOne(): String {
        return input.lines.filter { nicePartOne(it) }.count().toString()
    }


    override fun partTwo(): String {
        return input.lines.filter { nicePartTwo(it) }.count().toString()
    }


}


