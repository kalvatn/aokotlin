package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Year
import com.kalvatn.aoc.core.model.Day
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert

class Y2018D16Test : BaseDayTest() {
    override suspend fun examplePartOne() {
        //val test1 = Y2018D16(PuzzleInput.ofSingleLine(""))
        val test1 = Y2018D16(PuzzleInput.forDay(Year.Y2018, Day.D16, "test"))
        Assert.assertThat(test1.partOne().toInt(), equalTo(0))
    }

    override suspend fun examplePartTwo() {
        //val test1 = Y2018D16(PuzzleInput.ofSingleLine(""))
        val test1 = Y2018D16(PuzzleInput.forDay(Year.Y2018, Day.D16, "test"))
        Assert.assertThat(test1.partOne().toInt(), equalTo(0))
    }

    override suspend fun solutionPartOne() {
        val day = Y2018D16()
        Assert.assertThat(day.partOne().toInt(), equalTo(640))
    }

    override suspend fun solutionPartTwo() {
        TODO("not implemented")
        val day = Y2018D16()
        Assert.assertThat(day.partTwo().toInt(), equalTo(0))
    }
}