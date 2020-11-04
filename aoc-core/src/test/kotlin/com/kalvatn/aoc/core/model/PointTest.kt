package com.kalvatn.aoc.core.model
import org.junit.jupiter.api.Test

import com.kalvatn.aoc.common.model.Point
import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.StringSpec

class PointTest : StringSpec({

  "test surrounding" {
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
    surrounding shouldBe expected

//        val points = surrounding + point
//        (0..4).forEach {y ->
//            (0..4).forEach { x ->
//                print(if (points.contains(Point(x, y))) "#" else ".")
//            }
//            println()
//        }
  }

  "grid from" {
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
    grid3x3 shouldBe expected3x3
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
    grid4x4 shouldBe expected4x4

    printGrid(grid3x3)
    printGrid(grid4x4)

    printGrid(Point(4, 4).gridFrom(5))
  }

  "point addition should work as expected" {
    Point(0, 0).plus(Point(1, 1)) shouldBe Point(1, 1)
  }
})

fun printGrid(grid: Set<Point>) {
  (0 until (grid.size * 0.75).toInt()).forEach { y ->
    (0 until (grid.size * 0.75).toInt()).forEach { x ->
      print(if (grid.contains(Point(x, y))) " # " else " . ")
    }
    println()
  }
}
