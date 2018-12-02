package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.common.Day
import com.kalvatn.aoc.common.PuzzleInput

class Day02 : Day(2018, 2) {


    override fun input(): PuzzleInput {
//        return PuzzleInput.forDay(this, "test1")
//        return PuzzleInput.forDay(this, "test2")
        return super.input()
    }


    override fun partOne(): String {
        var double = 0
        var triple = 0
        for (line in input().lines) {
            val matching = line.groupingBy { it }.eachCount().filter { it.value > 1 }
            if (matching.values.contains(2)) {
                double += 1
            }
            if (matching.values.contains(3)) {
                triple += 1
            }
        }

        return (double * triple).toString()
    }

    override fun partTwo(): String {
        for (s1 in input().lines) {
            for (s2 in input().lines) {
                if (s1 == s2) {
                    continue
                }
                val equalChars = mutableListOf<Char>()
                for (i in 0 until s2.length) {
                    if (s1[i] == s2[i]) {
                        equalChars.add(s1[i])
                    }
                }
                if (equalChars.size == (s1.length - 1)) {
                    return equalChars.joinToString("")
                }
            }
        }
        return ""
    }
}

