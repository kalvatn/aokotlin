package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Year
import com.kalvatn.aoc.core.model.Day
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert

class Y2018D12Test : BaseDayTest() {
    override suspend fun examplePartOne() {
        //val test1 = Y2018D12(PuzzleInput.ofSingleLine(""))
        val test1 = Y2018D12(PuzzleInput.forDay(Year.Y2018, Day.D12, "test"))
        Assert.assertThat(test1.partOne().toInt(), equalTo(325))
    }

    override suspend fun examplePartTwo() {
        //val test1 = Y2018D12(PuzzleInput.ofSingleLine(""))
        val test1 = Y2018D12(PuzzleInput.forDay(Year.Y2018, Day.D12, "test"))
        Assert.assertThat(test1.partTwo().toLong(), equalTo(350000000080))
    }

    override suspend fun solutionPartOne() {
        val day = Y2018D12()
        Assert.assertThat(day.partOne().toInt(), equalTo(3798))
    }

    override suspend fun solutionPartTwo() {
        val day = Y2018D12()
        Assert.assertThat(day.partTwo().toLong(), equalTo(3900000002212))
    }
}