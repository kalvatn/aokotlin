package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.common.PuzzleInput
import com.kalvatn.aoc.common.Year
import com.kalvatn.aoc.common.Day
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert

class Y2018D12Test : BaseDayTest() {
    override fun examplePartOne() {
        //val test1 = Y2018D12(PuzzleInput.ofSingleLine(""))
        val test1 = Y2018D12(PuzzleInput.forDay(Year.Y2018, Day.D12, "test"))
        Assert.assertThat(test1.partOne().toInt(), equalTo(0))
    }

    override fun examplePartTwo() {
        //val test1 = Y2018D12(PuzzleInput.ofSingleLine(""))
        val test1 = Y2018D12(PuzzleInput.forDay(Year.Y2018, Day.D12, "test"))
        Assert.assertThat(test1.partOne().toInt(), equalTo(0))
    }

    override fun solutionPartOne() {
        TODO("not implemented")
        val day = Y2018D12()
        Assert.assertThat(day.partOne().toInt(), equalTo(0))
    }

    override fun solutionPartTwo() {
        TODO("not implemented")
        val day = Y2018D12()
        Assert.assertThat(day.partTwo().toInt(), equalTo(0))
    }
}