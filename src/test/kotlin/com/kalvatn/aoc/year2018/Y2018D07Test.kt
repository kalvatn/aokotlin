package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.common.Day
import com.kalvatn.aoc.common.PuzzleInput
import com.kalvatn.aoc.common.Year
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Test

class Y2018D07Test : BaseDayTest() {
    @Test
    override fun examplePartOne() {
        val test1 = Y2018D07(PuzzleInput.forDay(Year.Y2018, Day.D07, "test"))
        Assert.assertThat(test1.partOne(), equalTo("CABDFE"))
    }

    @Test
    override fun examplePartTwo() {
        val test1 = Y2018D07(PuzzleInput.forDay(Year.Y2018, Day.D07, "test"))
        Assert.assertThat(test1.partTwo().toInt(), equalTo(15))
    }

    @Test
    override fun solutionPartOne() {
        val day = Y2018D07()
        Assert.assertThat(day.partOne(), equalTo("JKNSTHCBGRVDXWAYFOQLMPZIUE"))
    }

    @Test
    override fun solutionPartTwo() {
        val day = Y2018D07()
        Assert.assertThat(day.partTwo().toInt(), equalTo(755))
    }
}