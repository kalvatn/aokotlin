package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.common.model.Point
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2019
import com.kalvatn.aoc.core.model.Year
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.atan2


class Y2019D10(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2019(Day.D10, input) {

    val lines by lazy { this.input.lines }

    fun gcd(a: Int, b: Int): Int {
        if (b == 0) return a
        return gcd(b, a % b)
    }

    override suspend fun partOne(): String {
        val points = mutableListOf<Point>()
        val pointValue = mutableMapOf<Point, Boolean>()
        lines.forEachIndexed { y, line ->
            line.forEachIndexed { x, value ->
                pointValue[Point(x, y)] = value == '#'
                print(value)
            }
            println()
        }
        val pointsWithAsteroids = pointValue.filter { it.value }.keys.toSet()
        val seenFrom = mutableMapOf<Point, MutableSet<Point>>()
        for (p1 in pointsWithAsteroids) {
            for (p2 in pointsWithAsteroids) {
                if (p1 == p2) {
                    continue
                }
                val dx = p2.x - p1.x
                val dy = p2.y - p1.y
                val gcd = abs(gcd(dx, dy))

                seenFrom.computeIfAbsent(p1) { mutableSetOf() }.add(Point(-dx / gcd, dy / gcd))

            }
//            println("seen from $p1 ${seenFrom[p1]} ${seenFrom[p1]!!.size}")
        }
        val (fromPoint, visible) = seenFrom.maxBy { it.value.size }?.toPair()!!
        println("$fromPoint ${visible.size} $visible")

        // https://stackoverflow.com/questions/41855695/sorting-list-of-two-dimensional-coordinates-by-clockwise-angle-using-python
        val sortedByAngle = visible.map { it ->
                var angle = atan2(it.x.toDouble(), it.y.toDouble())
                if (angle < 0) {
//        # Negative angles represent counter-clockwise angles so we need to subtract them
//        # from 2*pi (360 degrees)
                    angle += (2 * PI)
                }
                angle to it
        }.sortedBy { it.first }.map { it.second }.reversed()

        println(fromPoint)
        val at200 = sortedByAngle[199]

        val answer = Point(fromPoint.x - at200.x, fromPoint.y - at200.y)
        println(answer)


        return ""
    }

    override suspend fun partTwo(): String {
        return ""
    }

    companion object {

    }

}

fun main() = runBlocking {
//        val input = PuzzleInput.forDay(Year.Y2019, Day.D10, "test")
//    PuzzleRunner(listOf(Y2019D10(input))).run()
        val input = PuzzleInput.forDay(Year.Y2019, Day.D10, "test")
    PuzzleRunner(listOf(Y2019D10(input))).run()
//    PuzzleRunner(listOf(Y2019D10())).run()
}

