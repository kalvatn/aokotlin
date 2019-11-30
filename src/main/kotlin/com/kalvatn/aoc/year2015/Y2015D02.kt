package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2015
import com.kalvatn.aoc.extensions.extractIntegers

class Y2015D02(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2015(Day.D02, input) {

    data class Present(val l: Int, val w: Int, val h: Int) {
        private fun sides(): List<Int> {
            return listOf(l * w, w * h, h * l)
        }

        private fun surface(): Int {
            return sides().map { it * 2 }.sum()
        }

        private fun smallestSide(): Int {
            return sides().min()!!
        }

        fun paper(): Int {
            return surface() + smallestSide()
        }

        fun ribbon(): Int {
            val wrap = listOf(l, w, h).sorted().take(2).map { it * 2 }.sum()
            val bow = l * w * h
            return wrap + bow
        }

        companion object {
            fun fromString(string: String): Present {
                val (l, w, h) = string.extractIntegers()
                return Present(l, w, h)
            }
        }
    }


    private val dimensions = this.input.map { Present.fromString(it) }


    override suspend fun partOne(): String {
        return dimensions.map { it.paper() }.sum().toString()
    }


    override suspend fun partTwo(): String {
        return dimensions.map { it.ribbon() }.sum().toString()
    }


}


