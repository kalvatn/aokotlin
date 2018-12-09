package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.common.APuzzleTest
import com.kalvatn.aoc.common.PuzzleInput
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert

import org.junit.Assert.*

class Y2018D09Test : BaseDayTest() {
    override fun examplePartOne() {
        val test1 = Y2018D09(PuzzleInput.ofSingleLine("9 players; last marble is worth 23 points"))
        Assert.assertThat(test1.partOne().toInt(), equalTo(32))
        val test2 = Y2018D09(PuzzleInput.ofSingleLine("10 players; last marble is worth 1618 points"))
        Assert.assertThat(test2.partOne().toInt(), equalTo(8317))
        val test3 = Y2018D09(PuzzleInput.ofSingleLine("13 players; last marble is worth 7999 points"))
        Assert.assertThat(test3.partOne().toInt(), equalTo(146373))
        val test4 = Y2018D09(PuzzleInput.ofSingleLine("17 players; last marble is worth 1104 points"))
        Assert.assertThat(test4.partOne().toInt(), equalTo(2764))
        val test5 = Y2018D09(PuzzleInput.ofSingleLine("21 players; last marble is worth 6111 points"))
        Assert.assertThat(test5.partOne().toInt(), equalTo(54718))
        val test6 = Y2018D09(PuzzleInput.ofSingleLine("30 players; last marble is worth 5807 points"))
        Assert.assertThat(test6.partOne().toInt(), equalTo(37305))
    }

    override fun examplePartTwo() {
    }

    override fun solutionPartOne() {
        val day = Y2018D09()
        Assert.assertThat(day.partOne().toInt(), equalTo(370210))
    }

    override fun solutionPartTwo() {
        val day = Y2018D09()
        Assert.assertThat(day.partTwo().toLong(), equalTo(3101176548))
    }
}