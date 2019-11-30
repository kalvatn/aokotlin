package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Year
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Test

class Y2018D02Test {
    @Test
    suspend fun testExamples() {
        val test1 = Y2018D02(PuzzleInput.forDay(Year.Y2018, Day.D02, "test1"))
        val test2 = Y2018D02(PuzzleInput.forDay(Year.Y2018, Day.D02, "test2"))
        Assert.assertThat(test1.partOne().toInt(), equalTo(12))
        Assert.assertThat(test1.partTwo(), equalTo("abcde"))
        Assert.assertThat(test2.partOne().toInt(), equalTo(0))
        Assert.assertThat(test2.partTwo(), equalTo("fgij"))


    }

    @Test
    suspend fun testSolution() {
        val day02 = Y2018D02()
        Assert.assertThat(day02.partOne().toInt(), equalTo(5727))
        Assert.assertThat(day02.partTwo(), equalTo("uwfmdjxyxlbgnrotcfpvswaqh"))
    }
}