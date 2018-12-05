package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.IPuzzleTest
import com.kalvatn.aoc.common.PuzzleInput
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Test

class Y2018D05Test : IPuzzleTest {
    @Test
    override fun examplePartOne() {
        val test1 = Y2018D05(PuzzleInput.ofSingleLine("dabAcCaCBAcCcaDA"))
        Assert.assertThat(test1.partOne().toInt(), equalTo(10))
    }

    @Test
    override fun examplePartTwo() {
        val test1 = Y2018D05(PuzzleInput.ofSingleLine("dabAcCaCBAcCcaDA"))
        Assert.assertThat(test1.partTwo().toInt(), equalTo(4))
    }

    @Test
    override fun solutionPartOne() {
        val day = Y2018D05()
        Assert.assertThat(day.partOne().toInt(), equalTo(9526))
    }

    @Test
    override fun solutionPartTwo() {
        val day = Y2018D05()
        Assert.assertThat(day.partTwo().toInt(), equalTo(6694))
    }
}