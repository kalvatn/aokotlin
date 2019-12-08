package com.kalvatn.aoc.year2016

import com.kalvatn.aoc.common.model.Direction
import com.kalvatn.aoc.common.model.Point
import com.kalvatn.aoc.common.model.Turn
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2016
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking


class Y2016D01(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2016(Day.D01, input) {

    private val instructions by lazy {
        this.input.singleLineSplit(",").map {
            Turn.fromChar(it[0]) to it.drop(1).toInt()
        }
    }

    private data class State(val facing: Direction, val current: Point, val pointsVisited: List<Point>)

    private val state by lazy {
        instructions.fold(State(Direction.NORTH, START, listOf(START))) { state, (turn, steps) ->
            val facing = state.facing.turn(turn)
            val points = (1..steps).map { n ->
                state.current + (facing.toPointDiff() * n)
            }
            State(facing, points.last(), state.pointsVisited + points)
        }
    }

    override suspend fun partOne(): String {
        return START.distance(state.current).toString()
    }

    override suspend fun partTwo(): String {
        return state.pointsVisited.groupingBy { it }
                .eachCount()
                .filter { it.value > 1 }
                .keys.first().distance(START).toString()

    }

    companion object {
        private val START = Point(0, 0)
    }

}

fun main() = runBlocking {
    PuzzleRunner(listOf(Y2016D01())).run()
}

