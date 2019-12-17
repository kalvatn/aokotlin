package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.common.model.Point
import com.kalvatn.aoc.common.model.print
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2019
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking


class Y2019D17(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2019(Day.D17, input) {

    private val program by lazy { this.input.singleLineLongs() }

    override suspend fun partOne(): String {
        val points = mutableMapOf<Point, Char>()
        var x = 0
        var y = 0
        val pc = IntcodeComputer(program)
        pc.run()
        pc.output().forEach {
            when (it.toChar()) {
                '\n' -> {
                    y += 1
                    x = 0
                }
                else -> {
                    points[Point(x, y)] = it.toChar()
                    x++
                }
            }
        }

        val intersections = points.keys.filter {
            points[it] == '#' && it.adj4().all { a -> points.getOrDefault(a, '.') == '#' }
        }.toSet()
        points.print {
            if (it in intersections) {
                print('O')
            } else {
                print(points[it])
            }
        }
        return intersections.map { it.x * it.y }.sum().toString()
    }

    override suspend fun partTwo(): String {
        return ""
    }

    companion object {

    }

}

fun main() = runBlocking {
    PuzzleRunner(listOf(Y2019D17())).run()
}

