package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.common.PuzzleInput
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Test

class Day02Test {
    @Test
    fun testExamples() {
        val test1 = Day02(PuzzleInput.forDay(2018, 2, "test1"))
        val test2 = Day02(PuzzleInput.forDay(2018, 2, "test2"))
        Assert.assertThat(test1.partOne().toInt(), equalTo(12))
        Assert.assertThat(test1.partTwo(), equalTo("abcde"))
        Assert.assertThat(test2.partOne().toInt(), equalTo(0))
        Assert.assertThat(test2.partTwo(), equalTo("fgij"))


    }

    @Test
    fun testSolution() {
        val day02 = Day02()
        Assert.assertThat(day02.partOne().toInt(), equalTo(5727))
        Assert.assertThat(day02.partTwo(), equalTo("uwfmdjxyxlbgnrotcfpvswaqh"))
    }
}