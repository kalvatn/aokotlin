package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Year
import com.kalvatn.aoc.common.model.Direction
import com.kalvatn.aoc.common.model.Point
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Test

class Y2018D13Test : BaseDayTest() {

    @Test
    fun gridSize() {
        val test1 = Y2018D13(PuzzleInput.forDay(Year.Y2018, Day.D13, "test"))
        Assert.assertThat(test1.grid.size, equalTo(13))
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
        Assert.assertThat(test1.grid.valueAt(Point(0, 0)), equalTo('/'))
        Assert.assertThat(test1.grid.valueAt(Point(9, 4)), equalTo('+'))

    }

    @Test
    fun gridTrains() {
        val test1 = Y2018D13(PuzzleInput.forDay(Year.Y2018, Day.D13, "test"))
        val trains = test1.grid.trains()
        Assert.assertThat(
            trains, equalTo(
                setOf(
                    Y2018D13.Train(1, Point(2, 0), Direction.EAST),
                    Y2018D13.Train(2, Point(9, 3), Direction.SOUTH)
                )
            )
        )
    }

    @Test
    fun trainMove() {
        val test1 = Y2018D13(PuzzleInput.forDay(Year.Y2018, Day.D13, "test"))
        val trains = test1.grid.trains()
        val train1 = trains.first()
        val train2 = trains.last()

        train1.move(test1.grid)
        train2.move(test1.grid)

        Assert.assertThat(train1.position, equalTo(Point(3, 0)))
        Assert.assertThat(train1.direction, equalTo(Direction.EAST))
        Assert.assertThat(train2.position, equalTo(Point(9, 4)))
        Assert.assertThat(train2.direction, equalTo(Direction.EAST))
        repeat(2) {
            train1.move(test1.grid)
            train2.move(test1.grid)
        }
        Assert.assertThat(train1.position, equalTo(Point(4, 1)))
        Assert.assertThat(train1.direction, equalTo(Direction.SOUTH))
        Assert.assertThat(train2.position, equalTo(Point(11, 4)))
        Assert.assertThat(train2.direction, equalTo(Direction.EAST))
    }

    override suspend fun examplePartOne() {
        val test1 = Y2018D13(PuzzleInput.forDay(Year.Y2018, Day.D13, "test"))
        Assert.assertThat(test1.partOne(), equalTo("7,3"))
    }

    override suspend fun examplePartTwo() {
        val test1 = Y2018D13(PuzzleInput.forDay(Year.Y2018, Day.D13, "test"))
        Assert.assertThat(test1.partTwo().toInt(), equalTo(0))
    }

    override suspend fun solutionPartOne() {
        val day = Y2018D13()
        Assert.assertThat(day.partOne(), equalTo("40,90"))
    }

    override suspend fun solutionPartTwo() {
        val day = Y2018D13()
        Assert.assertThat(day.partTwo(), equalTo("65,81"))
    }
}