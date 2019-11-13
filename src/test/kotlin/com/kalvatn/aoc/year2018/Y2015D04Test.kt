package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Year
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Test

class Y2015D04Test {
    @Test
    suspend fun testExamples() {
        val test1 = Y2018D04(PuzzleInput.forDay(Year.Y2018, Day.D04, "test1"))
        Assert.assertThat(test1.partOne().toInt(), equalTo(240))
        Assert.assertThat(test1.partTwo().toInt(), equalTo(4455))
    }

    @Test
    suspend fun testSolution() {
        val day = Y2018D04()
        Assert.assertThat(day.partOne().toInt(), equalTo(30630))
        Assert.assertThat(day.partTwo().toInt(), equalTo(136571))
    }

    @Test
    fun eventFromString() {
        val p1 = Y2018D04.GuardRecord.fromString("[1518-11-01 00:00] Guard #10 begins shift")
        Assert.assertThat(p1, equalTo(Y2018D04.GuardRecord(1518, 11, 1, 0, 0, 10, Y2018D04.Action.BEGINS)))
        val p2 = Y2018D04.GuardRecord.fromString("[1518-11-01 00:05] falls asleep")
        Assert.assertThat(p2, equalTo(Y2018D04.GuardRecord(1518, 11, 1, 0, 5, -1, Y2018D04.Action.SLEEPS)))
        val p3 = Y2018D04.GuardRecord.fromString("[1518-11-01 00:25] wakes up")
        Assert.assertThat(p3, equalTo(Y2018D04.GuardRecord(1518, 11, 1, 0, 25, -1, Y2018D04.Action.WAKES)))

    }
}