package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert

class Y2018D14Test : BaseDayTest() {
    override suspend fun examplePartOne() {
        val test1 = Y2018D14(PuzzleInput.ofSingleLine("5"))
        Assert.assertThat(test1.partOne(), equalTo("0124515891"))
    }

    override suspend fun examplePartTwo() {
        val test1 = Y2018D14(PuzzleInput.ofSingleLine("59414"))
        Assert.assertThat(test1.partTwo().toInt(), equalTo(2018))
    }

    override suspend fun solutionPartOne() {
        val day = Y2018D14()
        Assert.assertThat(day.partOne(), equalTo("6910849249"))
    }

    override suspend fun solutionPartTwo() {
        val day = Y2018D14()
        Assert.assertThat(day.partTwo().toInt(), equalTo(20330673))
    }
}