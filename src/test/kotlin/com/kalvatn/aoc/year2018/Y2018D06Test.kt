package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Test

class Y2018D06Test : BaseDayTest() {
    @Test
    override suspend fun examplePartOne() {
        val test1 = Y2018D06(PuzzleInput.ofSingleLine(""))
        Assert.assertThat(test1.partOne(), equalTo(""))
    }

    @Test
    override suspend fun examplePartTwo() {
        val test1 = Y2018D06(PuzzleInput.ofSingleLine(""))
        Assert.assertThat(test1.partTwo(), equalTo(""))
    }

    @Test
    override suspend fun solutionPartOne() {
//        val day = Y2018D06()
//        Assert.assertThat(day.partOne().toInt(), equalTo(0))
    }

    @Test
    override suspend fun solutionPartTwo() {
//        val day = Y2018D06()
//        Assert.assertThat(day.partTwo().toInt(), equalTo(0))
    }
}