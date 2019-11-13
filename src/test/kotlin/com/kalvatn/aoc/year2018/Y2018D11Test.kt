package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.common.model.Point
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Test

class Y2018D11Test : BaseDayTest() {


    @Test
    fun testFuelCell() {
        Assert.assertThat(Y2018D11.FuelCell(Point(3, 5), 8).powerLevel(), equalTo(4))
        Assert.assertThat(Y2018D11.FuelCell(Point(122, 79), 57).powerLevel(), equalTo(-5))
        Assert.assertThat(Y2018D11.FuelCell(Point(217, 196), 39).powerLevel(), equalTo(0))
        Assert.assertThat(Y2018D11.FuelCell(Point(101, 153), 71).powerLevel(), equalTo(4))
    }

    override suspend fun examplePartOne() {
        val test1 = Y2018D11(PuzzleInput.ofSingleLine("18"))
        Assert.assertThat(test1.partOne(), equalTo("33,45"))
        val test2 = Y2018D11(PuzzleInput.ofSingleLine("42"))
        Assert.assertThat(test2.partOne(), equalTo("21,61"))
    }

    override suspend fun examplePartTwo() {
        val test1 = Y2018D11(PuzzleInput.ofSingleLine(""))
        Assert.assertThat(test1.partTwo().toInt(), equalTo(0))
    }

    override suspend fun solutionPartOne() {
        val day = Y2018D11()
        Assert.assertThat(day.partOne(), equalTo("33,54"))
    }

    override suspend fun solutionPartTwo() {
        val day = Y2018D11()
        Assert.assertThat(day.partTwo(), equalTo("232,289,8"))
    }
}