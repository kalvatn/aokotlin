package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.common.model.Direction
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

    private val initialState by lazy {
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
            } while (!previous.values.contains(state))
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
                    total += if (state[Point(x, y)] == '#') inc else 0
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

    class GameOfLifeRecursive(initialState: Map<Point, Char>) {
        val center = Point(2, 2)
        var minute = 0
        val emptyState by lazy {
            initialState.keys.map {
                it to if (it == center) '?' else '.'
            }.toMap()
        }
        val levels = mutableMapOf<Int, Map<Point, Char>>().apply {
            put(-1, emptyState)
            put(0, initialState.toMutableMap().apply { this[center] = '?' })
            put(1, emptyState)
        }


        fun step() {
            val newstates = mutableMapOf<Int, Map<Point, Char>>()
            levels.keys.toList().forEach { level ->
                levels.putIfAbsent(level - 1, emptyState)
                levels.putIfAbsent(level + 1, emptyState)

                val newState = mutableMapOf<Point, Char>()
                val newStateSub = mutableMapOf<Point,Char>()
                val state = levels[level]!!
                state.keys.forEach { point ->
                    val current = state[point] ?: error("impossiburu")
                    if (point == center) {
                        newState[point] = '?'
                    } else if (point in center.adj4()) {
                        val subState = levels[level + 1]!!
                        val adjThisLevel = point.adj4().filter { it != center }.mapNotNull { p -> state[p] }
                        val adjSub = when (center) {
                            point + Direction.WEST.toPointDiff() -> rightRowPoints.mapNotNull { subState[it] }
                            point + Direction.EAST.toPointDiff() -> leftRowPoints.mapNotNull { subState[it] }
                            point + Direction.NORTH.toPointDiff() -> bottomRowPoints.mapNotNull { subState[it] }
                            point + Direction.SOUTH.toPointDiff() -> topRowPoints.mapNotNull { subState[it] }
                            else -> error("impossiburu")
                        }
                        val adj = adjThisLevel + adjSub
                        if (adj.count() != 8) {
                            error("impossiburu")
                        }

                        if (current == '#') {
                            newState[point] = if (adj.count { v -> v == '#' } == 1) '#' else '.'
                        } else {
                            newState[point] = if (adj.count { v -> v == '#' } in listOf(1, 2)) '#' else '.'
                        }

                    } else if (point in topRowPoints || point in leftRowPoints || point in bottomRowPoints || point in rightRowPoints) {
                        val subState = levels[level - 1]!!
                        val adjThisLevel = point.adj4().mapNotNull { p -> state[p] }
                        val adjSub = when (point) {
                            Point(0,0) -> listOf(subState[center + Direction.NORTH.toPointDiff()], subState[center + Direction.WEST.toPointDiff()])
                            Point(0,4) -> listOf(subState[center + Direction.SOUTH.toPointDiff()], subState[center + Direction.WEST.toPointDiff()])
                            Point(4,0) -> listOf(subState[center + Direction.NORTH.toPointDiff()], subState[center + Direction.EAST.toPointDiff()])
                            Point(4,4) -> listOf(subState[center + Direction.SOUTH.toPointDiff()], subState[center + Direction.EAST.toPointDiff()])
                            in topRowPoints -> listOf(subState[center + Direction.NORTH.toPointDiff()])
                            in leftRowPoints -> listOf(subState[center + Direction.WEST.toPointDiff()])
                            in bottomRowPoints -> listOf(subState[center + Direction.SOUTH.toPointDiff()])
                            in rightRowPoints -> listOf(subState[center + Direction.EAST.toPointDiff()])
                            else -> error("impossiburu")
                        }.filterNotNull()
                        val adj = adjThisLevel + adjSub
                        if (adj.count() != 4) {
                            error("impossiburu")
                        }

                        if (current == '#') {
                            newState[point] = if (adj.count { v -> v == '#' } == 1) '#' else '.'
                        } else {
                            newState[point] = if (adj.count { v -> v == '#' } in listOf(1, 2)) '#' else '.'
                        }

                    } else {
                        if (current == '#') {
                            newState[point] = if (point.adj4().mapNotNull { p -> state[p] }.count { v -> v == '#' } == 1) '#' else '.'
                        } else {
                            newState[point] = if (point.adj4().mapNotNull { p -> state[p] }.count { v -> v == '#' } in listOf(1, 2)) '#' else '.'
                        }
                    }
                }
                newstates[level] = newState
            }
            minute += 1
            newstates.forEach { (k, v) ->
                levels[k] = v
            }
        }

        fun simulate(minutes: Int) {
            repeat(minutes) {
                step()

            }
        }

        fun countBugs(): Int {
            return levels.values.map { it.values }.flatten().count { it == '#' }
        }

        fun print() {
            levels.keys.sorted().forEach {
                levels[it]!!.print { p ->
                    print(levels[it]!![p])
                }
            }
        }


        companion object {
            val rightRowPoints = (0..5).map { y -> Point(4, y) }
            val leftRowPoints = (0..5).map { y -> Point(0, y) }
            val bottomRowPoints = (0..5).map { x -> Point(x, 4) }
            val topRowPoints = (0..5).map { x -> Point(x, 0) }
        }
    }

    override suspend fun partTwo(): String {
        val game = GameOfLifeRecursive(initialState)
        game.simulate(200)
        return game.countBugs().toString()
//        return ""
    }

}

fun main() = runBlocking {
    val input = PuzzleInput.forDay(Year.Y2019, Day.D24, "test")
//    PuzzleRunner(listOf(Y2019D24(input))).run()
    PuzzleRunner(listOf(Y2019D24())).run()
}

