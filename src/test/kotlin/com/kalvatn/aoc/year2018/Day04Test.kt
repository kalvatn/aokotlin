package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.common.PuzzleInput
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Test

class Day04Test {
    @Test
    fun testExamples() {
        val test1 = Day04(PuzzleInput.forDay(2018, 4, "test1"))
        Assert.assertThat(test1.partOne().toInt(), equalTo(240))
        Assert.assertThat(test1.partTwo().toInt(), equalTo(4455))
    }

    @Test
    fun testSolution() {
        val day = Day04()
        Assert.assertThat(day.partOne().toInt(), equalTo(30630))
        Assert.assertThat(day.partTwo().toInt(), equalTo(136571))
    }

    @Test
    fun eventFromString() {
        val p1 = Day04.GuardRecord.fromString("[1518-11-01 00:00] Guard #10 begins shift")
        Assert.assertThat(p1, equalTo(Day04.GuardRecord(1518, 11, 1, 0, 0, 10, Day04.Action.BEGINS)))
        val p2 = Day04.GuardRecord.fromString("[1518-11-01 00:05] falls asleep")
        Assert.assertThat(p2, equalTo(Day04.GuardRecord(1518, 11, 1, 0, 5, -1, Day04.Action.SLEEPS)))
        val p3 = Day04.GuardRecord.fromString("[1518-11-01 00:25] wakes up")
        Assert.assertThat(p3, equalTo(Day04.GuardRecord(1518, 11, 1, 0, 25, -1, Day04.Action.WAKES)))

    }
}