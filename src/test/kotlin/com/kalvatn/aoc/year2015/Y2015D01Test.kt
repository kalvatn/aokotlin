package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.core.input.PuzzleInput
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Test

class Y2015D01Test {
    @Test
    suspend fun testExamples() {
        val test1 = Y2015D01(PuzzleInput.ofSingleLine(")"))
        Assert.assertThat(test1.partOne().toInt(), equalTo(-1))
        Assert.assertThat(test1.partTwo().toInt(), equalTo(1))
    }

    @Test
    suspend fun testSolution() {
        val day01 = Y2015D01()
        Assert.assertThat(day01.partOne().toInt(), equalTo(138))
        Assert.assertThat(day01.partTwo().toInt(), equalTo(1771))

    }
}