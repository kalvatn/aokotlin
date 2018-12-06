package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.common.Day
import com.kalvatn.aoc.common.PuzzleInput
import com.kalvatn.aoc.exceptions.Impossiburu
import com.kalvatn.aoc.extensions.md5

class Y2015D04(input: PuzzleInput? = null) : APuzzle2015(Day.D04, input) {


    private val key = this.input.singleLine()

    private fun findSecret(startingWith: String): Int {
        (0..Integer.MAX_VALUE).forEach {
            if ("$key$it".md5().startsWith(startingWith)) return it
        }
        throw Impossiburu()
    }

    override fun partOne(): String {
        return findSecret("0".repeat(5)).toString()
    }


    override fun partTwo(): String {
        return findSecret("0".repeat(6)).toString()
    }


}


