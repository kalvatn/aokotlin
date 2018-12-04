package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.common.Day
import com.kalvatn.aoc.common.PuzzleInput
import com.kalvatn.aoc.common.Year
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Test

class Y2015D03Test {
    @Test
    fun testExamples() {
        val test1 = Y2018D03(PuzzleInput.forDay(Year.Y2018, Day.D03, "test1"))
        Assert.assertThat(test1.partOne().toInt(), equalTo(4))
        Assert.assertThat(test1.partTwo().toInt(), equalTo(3))
    }

    @Test
    fun testSolution() {
        val day03 = Y2018D03()
        Assert.assertThat(day03.partOne().toInt(), equalTo(116140))
        Assert.assertThat(day03.partTwo().toInt(), equalTo(574))

    }

    @Test
    fun testClaim() {
        val claim1 = Y2018D03.Claim.fromString("#1 @ 1,3: 4x4")
        Assert.assertThat(claim1.id, equalTo(1))
        Assert.assertThat(claim1.x, equalTo(1))
        Assert.assertThat(claim1.y, equalTo(3))
        Assert.assertThat(claim1.width, equalTo(4))
        Assert.assertThat(claim1.height, equalTo(4))
    }
}