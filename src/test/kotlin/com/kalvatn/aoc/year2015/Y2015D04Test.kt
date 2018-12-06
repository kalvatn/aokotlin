package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.common.PuzzleInput
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Ignore
import org.junit.Test

class Y2015D04Test {
    @Test
    fun testExamples() {
        val test1 = Y2015D04(PuzzleInput.ofSingleLine("abcdef"))
        Assert.assertThat(test1.partOne().toInt(), equalTo(609043))
//        Assert.assertThat(test1.partTwo().toInt(), equalTo(6742839))
    }

    @Test
    fun testSolution() {
        val day = Y2015D04()
        Assert.assertThat(day.partOne().toInt(), equalTo(254575))
        Assert.assertThat(day.partTwo().toInt(), equalTo(1038736))

    }
}