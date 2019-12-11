package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.common.model.Direction
import com.kalvatn.aoc.common.model.Point
import com.kalvatn.aoc.common.model.Turn
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2019
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking



class Y2019D11(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2019(Day.D11, input) {

    val program by lazy { this.input.singleLineLongs() }

    override suspend fun partOne(): String {
        val pc = IntcodeComputer(program)
        val pointCounts = mutableMapOf<Point, Int>()
        val pointColors = mutableMapOf<Point, Int>()


        val start = Point(0,0)
        pointColors[start] = COLOR_WHITE
        var current = start
        var dir = Direction.NORTH

        while(true) {
//            println(current)
            pointCounts.computeIfAbsent(current) { 0 }
            pointColors.computeIfAbsent(current) { COLOR_BLACK }
//            println(pointColors)
//            println(pointCounts)

            pc.input(pointColors[current]!!.toLong())
            pc.run()

            if (pc.output().size == 2 && current.x < 100) {
                val color = pc.removeOutput().toInt()
                val turnValue = pc.removeOutput().toInt()
                pointColors[current] = color
                pointCounts[current] = pointCounts[current]!!.inc()


                val turn = Turn.fromChar(if (turnValue == 0) '0' else '1')
                dir = dir.turn(turn)
                current += dir.toPointDiff()
            } else {
                break
            }

        }
        val countPaintedAtLeastOnce = pointCounts.size

        val maxX = pointCounts.keys.maxBy { it.x }!!.x
        val maxY = pointCounts.keys.maxBy { it.y }!!.y
        val minX = pointCounts.keys.minBy { it.x }!!.x
        val minY = pointCounts.keys.minBy { it.y }!!.y

        (minY..maxY).forEach { y ->
            (minX..maxX).forEach {x ->
                print(if (pointColors[Point(x, y)] == 0) " " else "#")
            }
            println()
        }
        println(pointColors.keys.size)
//        println(pointCounts.keys.distinct())

        return countPaintedAtLeastOnce.toString()
    }

    override suspend fun partTwo(): String {
        return ""
    }
    
    companion object {
        const val COLOR_BLACK = 0
        const val COLOR_WHITE = 1

    }

}

fun main() = runBlocking {
    PuzzleRunner(listOf(Y2019D11())).run()
}

