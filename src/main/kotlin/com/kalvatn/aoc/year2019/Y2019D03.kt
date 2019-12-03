package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.common.model.Direction
import com.kalvatn.aoc.common.model.Point
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2019
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking


class Y2019D03(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2019(Day.D03, input) {

    private val directions: List<List<Pair<Direction, Int>>> = this.input.lines.map { it.split(",") }.map { list ->
        list.map {
            Pair(Direction.fromChar(it.first()), it.drop(1).toInt())
        }
    }

    val seen = mutableMapOf<Int, MutableMap<Point, Int>>()
    val intersection = mutableMapOf<Point, Pair<Int, Int>>()

    fun move(id: Int, start: Point, directions: List<Pair<Direction, Int>>): Pair<Point, Int> {
        var current = start
        var steps = 0
        directions.forEach { (dir, times) ->
            println("$dir $times")
            repeat(times) {
                steps += 1
                current = current.plus(dir.toPointDiff())
                seen.putIfAbsent(id, mutableMapOf())
                seen[id]!!.putIfAbsent(current, steps)
                if (id != 1 && seen[1]!!.contains(current)) {
                    intersection[current] = Pair(seen[1]!![current]!!, seen[id]!![current]!!)
                }
            }
        }
        return Pair(current, start.distance(current))
    }

    override suspend fun partOne(): String {
        var i = 1
        directions.map { move(i++, Point(0, 0), it) }.forEach {
            println(it)
        }
        println(intersection)
        return intersection.map { it.key.distance(Point(0, 0)) }.min().toString()
    }

    override suspend fun partTwo(): String {
        var i = 1
        directions.map { move(i++, Point(0, 0), it) }.forEach {
            println(it)
        }
        println(intersection)
        return intersection.map { it.value.first + it.value.second }.min().toString()
    }

}

fun main() = runBlocking {
    PuzzleRunner(listOf(Y2019D03())).run()
}

