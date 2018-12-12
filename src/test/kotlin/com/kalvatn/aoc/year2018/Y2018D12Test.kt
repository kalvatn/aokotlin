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
        Assert.assertThat(test1.partOne().toInt(), equalTo(325))
    }

    override fun examplePartTwo() {
        //val test1 = Y2018D12(PuzzleInput.ofSingleLine(""))
        val test1 = Y2018D12(PuzzleInput.forDay(Year.Y2018, Day.D12, "test"))
        Assert.assertThat(test1.partTwo().toLong(), equalTo(350000000080))
    }

    override fun solutionPartOne() {
        val day = Y2018D12()
        Assert.assertThat(day.partOne().toInt(), equalTo(3798))
    }

    override fun solutionPartTwo() {
        val day = Y2018D12()
        Assert.assertThat(day.partTwo().toLong(), equalTo(3900000002212))
    }
}