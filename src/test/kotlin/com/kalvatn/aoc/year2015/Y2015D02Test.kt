package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.common.PuzzleInput
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Test

class Y2015D02Test {
    @Test
    fun testExamples() {
        val test1 = Y2015D02(PuzzleInput.ofSingleLine("2x3x4"))
        Assert.assertThat(test1.partOne().toInt(), equalTo(58))
        Assert.assertThat(test1.partTwo().toInt(), equalTo(34))
        val test2 = Y2015D02(PuzzleInput.ofSingleLine("1x1x10"))
        Assert.assertThat(test2.partOne().toInt(), equalTo(43))
        Assert.assertThat(test2.partTwo().toInt(), equalTo(14))
    }

    @Test
    fun testSolution() {
        val day = Y2015D02()
        Assert.assertThat(day.partOne().toInt(), equalTo(1606483))
        Assert.assertThat(day.partTwo().toInt(), equalTo(3842356))

    }
}