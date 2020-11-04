package com.kalvatn.aoc.year2018
import org.junit.jupiter.api.Test

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.common.model.Direction
import com.kalvatn.aoc.common.model.Point
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import io.kotest.matchers.shouldBe

class Y2018D13Test : BaseDayTest() {

  @Test
  fun gridSize() {
    val test1 = Y2018D13(PuzzleInput.forDay(Year.Y2018, Day.D13, "test"))
    test1.grid.size shouldBe 13
  }

  @Test
  fun gridPrint() {
    val test1 = Y2018D13(PuzzleInput.forDay(Year.Y2018, Day.D13, "test"))
    test1.grid.print()
    repeat(30) {
      test1.grid.tick()
      test1.grid.print()
      System.out.flush()
    }
  }

  @Test
  fun gridValueAt() {
    val test1 = Y2018D13(PuzzleInput.forDay(Year.Y2018, Day.D13, "test"))
    test1.grid.valueAt(Point(0, 0)) shouldBe '/'
    test1.grid.valueAt(Point(9, 4)) shouldBe '+'
  }

  @Test
  fun gridTrains() {
    val grid = Y2018D13.Grid(PuzzleInput.forDay(Year.Y2018, Day.D13, "test").lines)
    val trains = grid.trains()

    trains shouldBe
      setOf(
        Y2018D13.Train(1, Point(2, 0), Direction.EAST),
        Y2018D13.Train(2, Point(9, 3), Direction.SOUTH)
      )
  }

  @Test
  fun trainMove() {
    val grid = Y2018D13.Grid(PuzzleInput.forDay(Year.Y2018, Day.D13, "test").lines)
    val trains = grid.trains()
    val train1 = trains.first()
    val train2 = trains.last()

    train1.move(grid)
    train2.move(grid)

    train1.position shouldBe Point(3, 0)
    train1.direction shouldBe Direction.EAST
    train2.position shouldBe Point(9, 4)
    train2.direction shouldBe Direction.EAST
    repeat(2) {
      train1.move(grid)
      train2.move(grid)
    }
    train1.position shouldBe Point(4, 1)
    train1.direction shouldBe Direction.SOUTH
    train2.position shouldBe Point(11, 4)
    train2.direction shouldBe Direction.EAST
  }

  override suspend fun examplePartOne() {
    val test1 = Y2018D13(PuzzleInput.forDay(Year.Y2018, Day.D13, "test"))
    test1.partOne() shouldBe "7,3"
  }

  override suspend fun examplePartTwo() {
    val test1 = Y2018D13(PuzzleInput.forDay(Year.Y2018, Day.D13, "test"))
    test1.partTwo().toInt() shouldBe 0
  }

  override suspend fun solutionPartOne() {
    val day = Y2018D13()
    day.partOne() shouldBe "40,90"
  }

  override suspend fun solutionPartTwo() {
    val day = Y2018D13()
    day.partTwo() shouldBe "65,81"
  }
}
