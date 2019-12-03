package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.common.model.Direction
import com.kalvatn.aoc.common.model.Point
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2019
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking

data class Step(val direction: Direction, val steps: Int) {
    companion object {
        fun fromString(s: String) =
                Step(Direction.fromChar(s.first()), s.drop(1).toInt())
    }
}

class Wire(private val steps: List<Step>) {
    val seen = mutableMapOf<Point, Int>()

    fun move() {
        var totalSteps = 0
        var current = Point(0, 0)
        steps.forEach { step ->
            repeat(step.steps) {
                totalSteps++
                current = current.plus(step.direction.toPointDiff())
                seen.putIfAbsent(current, totalSteps)
            }
        }
    }

    companion object {
        fun fromString(s: String) =
                Wire(s.split(",").map { Step.fromString(it) })
    }
}

class Y2019D03(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2019(Day.D03, input) {

    private val wires = this.input.lines.map { Wire.fromString(it) }
    private val seenBoth = wires.map {
        it.move()
        it
    }.map { it.seen }
    private val seen1 = seenBoth.first()
    private val seen2 = seenBoth.last()
    private val intersections = seen1.keys.intersect(seen2.keys)

    override suspend fun partOne(): String {
        return intersections.map { it.distance(Point(0, 0)) }.min().toString()
    }

    override suspend fun partTwo(): String {
        return intersections.map { seen1[it]!! + seen2[it]!! }.min().toString()
    }

}

fun main() = runBlocking {
    PuzzleRunner(listOf(Y2019D03())).run()
}

