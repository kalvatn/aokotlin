package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.common.PuzzleInput
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Test

class Day04Test {
    @Test
    fun testExamples() {
        val test1 = Day04(PuzzleInput.ofSingleLine("abcdef"))
        Assert.assertThat(test1.partOne().toInt(), equalTo(609043))
////        Assert.assertThat(test1.partTwo().toInt(), equalTo(34))
//        val test2 = Day04(PuzzleInput.ofSingleLine("^>v<"))
//        Assert.assertThat(test2.partOne().toInt(), equalTo(4))
//        Assert.assertThat(test2.partTwo().toInt(), equalTo(3))
//        val test3 = Day04(PuzzleInput.ofSingleLine("^v^v^v^v^v"))
//        Assert.assertThat(test3.partOne().toInt(), equalTo(2))
//        Assert.assertThat(test3.partTwo().toInt(), equalTo(11))
    }

    @Test
    fun testSolution() {
        val day = Day04()
        Assert.assertThat(day.partOne().toInt(), equalTo(254575))
        Assert.assertThat(day.partTwo().toInt(), equalTo(1038736))

    }
}