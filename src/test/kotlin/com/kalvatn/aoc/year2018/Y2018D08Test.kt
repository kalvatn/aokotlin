package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.common.Day
import com.kalvatn.aoc.common.PuzzleInput
import com.kalvatn.aoc.common.Year
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Test

class Y2018D08Test : BaseDayTest() {
    @Test
    override fun examplePartOne() {
        val test1 = Y2018D08(PuzzleInput.forDay(Year.Y2018, Day.D08, "test"))
        Assert.assertThat(test1.partOne().toInt(), equalTo(138))
    }

    @Test
    override fun examplePartTwo() {
        val test1 = Y2018D08(PuzzleInput.forDay(Year.Y2018, Day.D08, "test"))
        Assert.assertThat(test1.partTwo().toInt(), equalTo(66))
    }

    @Test
    override fun solutionPartOne() {
        val day = Y2018D08()
        Assert.assertThat(day.partOne().toInt(), equalTo(48443))
    }

    @Test
    override fun solutionPartTwo() {
        val day = Y2018D08()
        Assert.assertThat(day.partTwo().toInt(), equalTo(30063))
    }
}