package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.common.PuzzleInput
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Test

class Day03Test {
    @Test
    fun testExamples() {
        val test1 = Day03(PuzzleInput.ofSingleLine(">"))
        Assert.assertThat(test1.partOne().toInt(), equalTo(2))
//        Assert.assertThat(test1.partTwo().toInt(), equalTo(34))
        val test2 = Day03(PuzzleInput.ofSingleLine("^>v<"))
        Assert.assertThat(test2.partOne().toInt(), equalTo(4))
        Assert.assertThat(test2.partTwo().toInt(), equalTo(3))
        val test3 = Day03(PuzzleInput.ofSingleLine("^v^v^v^v^v"))
        Assert.assertThat(test3.partOne().toInt(), equalTo(2))
        Assert.assertThat(test3.partTwo().toInt(), equalTo(11))
    }

    @Test
    fun testSolution() {
        val day = Day03()
        Assert.assertThat(day.partOne().toInt(), equalTo(2572))
        Assert.assertThat(day.partTwo().toInt(), equalTo(2631))

    }
}