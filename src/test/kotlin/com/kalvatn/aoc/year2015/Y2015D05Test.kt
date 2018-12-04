package com.kalvatn.aoc.year2015

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Test

class Y2015D05Test {
    @Test
    fun nicePartOne() {
        val day = Y2015D05()
        Assert.assertThat(day.nicePartOne("ugknbfddgicrmopn"), equalTo(true))
        Assert.assertThat(day.nicePartOne("aaa"), equalTo(true))
        Assert.assertThat(day.nicePartOne("jchzalrnumimnmhp"), equalTo(false))
        Assert.assertThat(day.nicePartOne("haegwjzuvuyypxyu"), equalTo(false))
        Assert.assertThat(day.nicePartOne("dvszwmarrgswjxmb"), equalTo(false))

    }

    @Test
    fun nicePartTwo() {
        val day = Y2015D05()
        Assert.assertThat(day.nicePartTwo("qjhvhtzxzqqjkmpb"), equalTo(true))
        Assert.assertThat(day.nicePartTwo("xxyxx"), equalTo(true))
        Assert.assertThat(day.nicePartTwo("uurcxstgmygtbstg"), equalTo(false))
        Assert.assertThat(day.nicePartTwo("ieodomkazucvgmuy"), equalTo(false))
        Assert.assertThat(day.nicePartTwo("xilodxfuxphuiiii"), equalTo(true))
    }


    @Test
    fun testSolution() {
        val day = Y2015D05()
        Assert.assertThat(day.partOne().toInt(), equalTo(255))
        Assert.assertThat(day.partTwo().toInt(), equalTo(55))
    }
}