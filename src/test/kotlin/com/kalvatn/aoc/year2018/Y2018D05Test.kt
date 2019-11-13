package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Test

class Y2018D05Test : BaseDayTest() {
    @Test
    override suspend fun examplePartOne() {
        val test1 = Y2018D05(PuzzleInput.ofSingleLine("dabAcCaCBAcCcaDA"))
        Assert.assertThat(test1.partOne().toInt(), equalTo(10))
    }

    @Test
    override suspend fun examplePartTwo() {
        val test1 = Y2018D05(PuzzleInput.ofSingleLine("dabAcCaCBAcCcaDA"))
        Assert.assertThat(test1.partTwo().toInt(), equalTo(4))
    }

    @Test
    override suspend fun solutionPartOne() {
        val day = Y2018D05()
        Assert.assertThat(day.partOne().toInt(), equalTo(9526))
    }

    @Test
    override suspend fun solutionPartTwo() {
        val day = Y2018D05()
        Assert.assertThat(day.partTwo().toInt(), equalTo(6694))
    }
}