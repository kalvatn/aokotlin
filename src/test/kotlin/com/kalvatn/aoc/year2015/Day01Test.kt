package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.common.PuzzleInput
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Test

class Day01Test {
    @Test
    fun testExamples() {
        val test1 = Day01(PuzzleInput.ofSingleLine(")"))
        Assert.assertThat(test1.partOne().toInt(), equalTo(-1))
        Assert.assertThat(test1.partTwo().toInt(), equalTo(1))
    }

    @Test
    fun testSolution() {
        val day01 = Day01()
        Assert.assertThat(day01.partOne().toInt(), equalTo(138))
        Assert.assertThat(day01.partTwo().toInt(), equalTo(1771))

    }
}