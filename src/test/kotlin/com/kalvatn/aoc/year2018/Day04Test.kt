package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.common.PuzzleInput
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Test

class Day04Test {
    @Test
    fun testExamples() {
        val test1 = Day04(PuzzleInput.forDay(2018, 4, "test1"))
        Assert.assertThat(test1.partOne().toInt(), equalTo(240))
        Assert.assertThat(test1.partTwo().toInt(), equalTo(4455))
    }

    @Test
    fun testSolution() {
        val day = Day04()
        Assert.assertThat(day.partOne().toInt(), equalTo(30630))
        Assert.assertThat(day.partTwo().toInt(), equalTo(136571))

    }
}