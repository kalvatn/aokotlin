package com.kalvatn.aoc.common.model

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Test

class PointTest {

    @Test
    fun surrounding() {
        val point = Point(2, 2)
        val surrounding = point.surrounding()
        val expected = setOf(

                Point(1, 1),
                Point(1, 2),
                Point(1, 3),

                Point(2, 1),
                Point(2, 3),

                Point(3, 1),
                Point(3, 2),
                Point(3, 3)
        )
        Assert.assertThat(surrounding, equalTo(expected))

//        val points = surrounding + point
//        (0..4).forEach {y ->
//            (0..4).forEach { x ->
//                print(if (points.contains(Point(x, y))) "#" else ".")
//            }
//            println()
//        }

    }

    @Test
    fun gridFrom() {
        val topleft3x3 = Point(0, 0)
        val grid3x3 = topleft3x3.gridFrom(size = 3)
        val expected3x3 = setOf(

                Point(0, 0),
                Point(0, 1),
                Point(0, 2),

                Point(1, 0),
                Point(1, 1),
                Point(1, 2),

                Point(2, 0),
                Point(2, 1),
                Point(2, 2)
        )
        Assert.assertThat(grid3x3, equalTo(expected3x3))
        val topleft4x4 = Point(0, 0)
        val grid4x4 = topleft4x4.gridFrom(size = 4)
        val expected4x4 = setOf(
                Point(0, 0),
                Point(0, 1),
                Point(0, 2),
                Point(0, 3),

                Point(1, 0),
                Point(1, 1),
                Point(1, 2),
                Point(1, 3),

                Point(2, 0),
                Point(2, 1),
                Point(2, 2),
                Point(2, 3),

                Point(3, 0),
                Point(3, 1),
                Point(3, 2),
                Point(3, 3)
        )
        Assert.assertThat(grid4x4, equalTo(expected4x4))

        printGrid(grid3x3)
        printGrid(grid4x4)


        printGrid(Point(4, 4).gridFrom(5))
    }

    private fun printGrid(grid:Set<Point>) {
        (0 until (grid.size * 0.75).toInt()).forEach { y ->
            (0 until (grid.size * 0.75).toInt()).forEach { x ->
                print(if (grid.contains(Point(x, y))) " # " else " . ")
            }
            println()
        }
    }
}