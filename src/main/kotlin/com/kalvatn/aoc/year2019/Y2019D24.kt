package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.common.model.Point
import com.kalvatn.aoc.common.model.asString
import com.kalvatn.aoc.common.model.print
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2019
import com.kalvatn.aoc.core.model.Year
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking


class Y2019D24(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2019(Day.D24, input) {

    val initialState by lazy {
        this.input.asPoints()
    }

    class GameOfLife(initialState: Map<Point, Char>) {
        var state = initialState
        var minute = 0
        val previous = mutableMapOf<Int, Map<Point, Char>>()
        fun step() {
            previous[minute] = state

            val newState = mutableMapOf<Point, Char>()
            state.keys.forEach {
                val current = state[it] ?: error("impossiburu")
                if (current == '#') {
                    newState[it] = if (it.adj4().mapNotNull { p -> state[p] }.count { v -> v == '#' } == 1) '#' else '.'
                } else {
                    newState[it] = if (it.adj4().mapNotNull { p -> state[p] }.count { v -> v == '#' } in listOf(1, 2)) '#' else '.'
                }
            }
            minute += 1
            state = newState
        }

        fun simulate() {
            do {
                step()
                state.print { print(state[it]) }
            } while(!previous.values.contains(state))
        }

        override fun toString(): String {
            return state.asString()
        }

        fun biodiversity(): Long {
            val (xMin, xMax, yMin, yMax) = with(state.keys) {
                listOf(
                        minBy { it.x }!!.x,
                        maxBy { it.x }!!.x,
                        minBy { it.y }!!.y,
                        maxBy { it.y }!!.y
                )
            }
            var total = 0L
            var inc = 1
            (yMin..yMax).forEach { y ->
                (xMin..xMax).forEach { x ->
                    total += if (state[Point(x,y)] == '#') inc else 0
                    inc *= 2
                }
            }
            return total
        }
    }

    override suspend fun partOne(): String {
        val game = GameOfLife(initialState)
        game.simulate()
        return game.biodiversity().toString()
    }

    override suspend fun partTwo(): String {
        return ""
    }

}

fun main() = runBlocking {
    val input = PuzzleInput.forDay(Year.Y2019, Day.D24, "test")
    PuzzleRunner(listOf(Y2019D24(input))).run()
//    PuzzleRunner(listOf(Y2019D24())).run()
}

