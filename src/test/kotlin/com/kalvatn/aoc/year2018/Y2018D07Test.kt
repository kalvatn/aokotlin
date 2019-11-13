package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Year
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Test

class Y2018D07Test : BaseDayTest() {
    @Test
    override suspend fun examplePartOne() {
        val test1 = Y2018D07(PuzzleInput.forDay(Year.Y2018, Day.D07, "test"))
        Assert.assertThat(test1.partOne(), equalTo("CABDFE"))
    }

    @Test
    override suspend fun examplePartTwo() {
        val test1 = Y2018D07(PuzzleInput.forDay(Year.Y2018, Day.D07, "test"))
        Assert.assertThat(test1.partTwo().toInt(), equalTo(15))
    }

    @Test
    override suspend fun solutionPartOne() {
        val day = Y2018D07()
        Assert.assertThat(day.partOne(), equalTo("JKNSTHCBGRVDXWAYFOQLMPZIUE"))
    }

    @Test
    override suspend fun solutionPartTwo() {
        val day = Y2018D07()
        Assert.assertThat(day.partTwo().toInt(), equalTo(755))
    }
}