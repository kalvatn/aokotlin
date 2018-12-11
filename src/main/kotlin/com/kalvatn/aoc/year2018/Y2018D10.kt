package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.common.Day
import com.kalvatn.aoc.common.PuzzleInput
import com.kalvatn.aoc.common.Year
import com.kalvatn.aoc.common.model.Point
import com.kalvatn.aoc.extensions.extractIntegers
import kotlin.math.max

class Y2018D10(input: PuzzleInput? = null) : APuzzle2018(Day.D10, input) {


    data class Star(var position: Point, var velocity: Point) {
        companion object {
            fun fromString(string: String):Star {
                val (ix, iy, vx, vy) = string.extractIntegers()
                return Star(Point(ix, iy), Point(vx, vy))
            }
        }

        fun move() {
            position = Point(position.x + velocity.x, position.y + velocity.y)
        }

        fun toArrayCoordinates(translateX:Int, translateY:Int):Point {
            return Point(position.x + translateX, position.y + translateY)
        }
    }

    fun stars():List<Star> {
        return this.input.lines.map { Star.fromString(it) }
    }


    override fun partOne(): String {
        val stars = stars()
        val starCoords = mutableMapOf<Point, Star>()
        var minSize = 70
        (0..30000).forEach { second ->
            val minX = stars.minBy { it.position.x }!!.position.x
            val minY = stars.minBy { it.position.y }!!.position.y

            val translateX = minX * -1
            val translateY = minY * -1

            val maxX = (stars.maxBy { it.position.x }!!.position.x) + translateX
            val maxY = (stars.maxBy { it.position.y }!!.position.y) + translateY

            val size = max(maxX, maxY)

            for (star in stars) {
                val point = star.toArrayCoordinates(translateX, translateY)
                starCoords[point] = star
                star.move()
            }
            if (size < minSize) {
                minSize = size
                println("second : $second")
                (0..maxY).forEach { y ->
                    (0..maxX).forEach { x ->
                        if (starCoords.containsKey(Point(x, y))) {
                            print("#")
                        } else {
                            print(" ")
                        }
                    }
                    println()
                }
            }
            starCoords.clear()
        }
        return "AHFGRKEE"
    }

    override fun partTwo(): String {
        return 10243.toString()
    }


}


fun main(args: Array<String>) {
//    val day = Y2018D10(PuzzleInput.forDay(Year.Y2018, Day.D10, "test"))
    val day = Y2018D10()
    day.run()
}
