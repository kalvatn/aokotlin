package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.common.PuzzleInput
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Test

class Y2015D03Test {
    @Test
    fun testExamples() {
        val test1 = Y2015D03(PuzzleInput.ofSingleLine(">"))
        Assert.assertThat(test1.partOne().toInt(), equalTo(2))
        val test2 = Y2015D03(PuzzleInput.ofSingleLine("^>v<"))
        Assert.assertThat(test2.partOne().toInt(), equalTo(4))
        Assert.assertThat(test2.partTwo().toInt(), equalTo(3))
        val test3 = Y2015D03(PuzzleInput.ofSingleLine("^v^v^v^v^v"))
        Assert.assertThat(test3.partOne().toInt(), equalTo(2))
        Assert.assertThat(test3.partTwo().toInt(), equalTo(11))
    }

    @Test
    fun testSolution() {
        val day = Y2015D03()
        Assert.assertThat(day.partOne().toInt(), equalTo(2572))
        Assert.assertThat(day.partTwo().toInt(), equalTo(2631))

    }
}