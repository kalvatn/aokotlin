package com.kalvatn.aoc.core

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.year2015.Y2015D04
import com.kalvatn.aoc.year2015.Y2015D06
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Test

class PuzzleInputTest {
    @Test
    fun ofSingleLine() {
        val i = PuzzleInput.ofSingleLine("2x3x4")
        Assert.assertThat(i.singleLine(), equalTo("2x3x4"))
        val j = PuzzleInput.ofSingleLineSplit("2x3x4", "x")
        Assert.assertThat(j.lines, equalTo(listOf("2", "3", "4")))
    }

    @Test
    fun ofSingleLineSplit() {
        val i = PuzzleInput.ofSingleLineSplit("2x3x4", "x")
        Assert.assertThat(i.lines, equalTo(listOf("2", "3", "4")))
        val j = PuzzleInput.ofSingleLineSplit("2x3x4")
        Assert.assertThat(j.lines, equalTo(listOf("2", "x", "3", "x", "4")))
    }

    @Test
    fun ofCommaDelimited() {
        val i = PuzzleInput.ofCommaDelimited("2,3,4")
        Assert.assertThat(i.lines, equalTo(listOf("2", "3", "4")))
    }

    @Test
    fun asIntegers() {
        val i = PuzzleInput.ofCommaDelimited("2,3,4")
        Assert.assertThat(i.asIntegers(), equalTo(listOf(2, 3, 4)))
    }

    @Test
    fun asInteger() {
        val i = PuzzleInput.ofSingleLine("234")
        Assert.assertThat(i.asInteger(), equalTo(234))
    }

    @Test
    fun forDay() {
        val puzzle = Y2015D04()
        val i = PuzzleInput.forDay(puzzle)
        Assert.assertThat(i.singleLine(), equalTo("bgvyzdsv"))
    }

    @Test
    fun forDayWithSuffix() {
        val puzzle = Y2015D06()
        val i = PuzzleInput.forDay(puzzle, suffix = "test")
        Assert.assertThat(i.lines, equalTo(listOf("turn on 0,0 through 0,0",
                "toggle 0,0 through 999,999")))
    }
}