package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.common.PuzzleInput
import com.kalvatn.aoc.common.Year
import com.kalvatn.aoc.common.Day
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert

class Y2018D14Test : BaseDayTest() {
    override fun examplePartOne() {
        val test1 = Y2018D14(PuzzleInput.ofSingleLine("5"))
        Assert.assertThat(test1.partOne(), equalTo("0124515891"))
    }

    override fun examplePartTwo() {
        val test1 = Y2018D14(PuzzleInput.ofSingleLine("59414"))
        Assert.assertThat(test1.partTwo().toInt(), equalTo(2018))
    }

    override fun solutionPartOne() {
        val day = Y2018D14()
        Assert.assertThat(day.partOne(), equalTo("6910849249"))
    }

    override fun solutionPartTwo() {
        val day = Y2018D14()
        Assert.assertThat(day.partTwo().toInt(), equalTo(20330673))
    }
}