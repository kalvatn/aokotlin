package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.common.PuzzleInput
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert

class Y2018D11Test : BaseDayTest() {
    override fun examplePartOne() {
        val test1 = Y2018D11(PuzzleInput.ofSingleLine(""))
        Assert.assertThat(test1.partOne().toInt(), equalTo(0))
    }

    override fun examplePartTwo() {
        val test1 = Y2018D11(PuzzleInput.ofSingleLine(""))
        Assert.assertThat(test1.partTwo().toInt(), equalTo(0))
    }

    override fun solutionPartOne() {
        TODO("not implemented")
        val day = Y2018D11()
        Assert.assertThat(day.partOne().toInt(), equalTo(0))
    }

    override fun solutionPartTwo() {
        TODO("not implemented")
        val day = Y2018D11()
        Assert.assertThat(day.partTwo().toInt(), equalTo(0))
    }
}