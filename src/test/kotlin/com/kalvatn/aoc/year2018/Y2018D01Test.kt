package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.core.input.PuzzleInput
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Ignore
import org.junit.Test

class Y2018D01Test {

    @Test
    suspend fun testExamples() {
        val test1 = Y2018D01(PuzzleInput.ofCommaDelimited("+1, -1"))
        val test2 = Y2018D01(PuzzleInput.ofCommaDelimited("+3, +3, +4, -2, -4"))
        val test3 = Y2018D01(PuzzleInput.ofCommaDelimited("-6, +3, +8, +5, -6"))
        val test4 = Y2018D01(PuzzleInput.ofCommaDelimited("+7, +7, -2, -7, -4"))
        Assert.assertThat(test1.partOne().toInt(), equalTo(0))
        Assert.assertThat(test1.partTwo().toInt(), equalTo(0))

        Assert.assertThat(test2.partOne().toInt(), equalTo(4))
        Assert.assertThat(test2.partTwo().toInt(), equalTo(10))

        Assert.assertThat(test3.partOne().toInt(), equalTo(4))
        Assert.assertThat(test3.partTwo().toInt(), equalTo(5))

        Assert.assertThat(test4.partOne().toInt(), equalTo(1))
        Assert.assertThat(test4.partTwo().toInt(), equalTo(14))


    }

    @Test
    suspend fun testSolution() {
        val day01 = Y2018D01()
        Assert.assertThat(day01.partOne().toInt(), equalTo(518))
        Assert.assertThat(day01.partTwo().toInt(), equalTo(72889))
    }

    @Test
    @Ignore(value = "ignored as optimization is not implemented")
    suspend fun testHard() {
        // https://www.reddit.com/r/adventofcode/comments/a20646/2018_day_1_solutions/eaukxu5/
        val testHard = Y2018D01(PuzzleInput.ofCommaDelimited("+10000000, -9999999"))
        Assert.assertThat(testHard.partOne().toInt(), equalTo(1))
        Assert.assertThat(testHard.partTwo().toInt(), equalTo(10000000))
    }
}