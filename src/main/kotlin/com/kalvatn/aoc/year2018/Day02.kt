package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.common.Day

class Day02 : Day(2018, 2) {

    private val boxIds = input().lines

    override fun partOne(): String {
        val charCounts = boxIds.map { line -> line.groupingBy { it }.eachCount().values }
        return (charCounts.count { 2 in it } * charCounts.count { 3 in it }).toString()
    }

    override fun partTwo(): String {
        for (s1 in boxIds) {
            for (s2 in boxIds) {
                val equalChars = (0 until s2.length)
                        .asSequence()
                        .filter { s1[it] == s2[it] }
                        .map { s1[it] }
                        .toList()
                if (equalChars.size == (s1.length - 1)) {
                    return equalChars.joinToString("")
                }
            }
        }
        return ""
    }
}

