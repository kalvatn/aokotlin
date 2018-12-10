package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.common.Day
import com.kalvatn.aoc.common.PuzzleInput
import com.kalvatn.aoc.common.Year
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert

import org.junit.Test

class Y2018D10Test : BaseDayTest() {

    @Test
    fun starFromString() {
        Assert.assertThat(Y2018D10.Star.fromString("position=< 9,  1> velocity=< 0,  2>"), equalTo(Y2018D10.Star(Y2018D10.Point(9, 1), Y2018D10.Point(0, 2))))
        Assert.assertThat(Y2018D10.Star.fromString("position=<-2,  7> velocity=< 2, -2>"), equalTo(Y2018D10.Star(Y2018D10.Point(-2, 7), Y2018D10.Point(2, -2))))
        val test1 = Y2018D10(PuzzleInput.forDay(Year.Y2018, Day.D10, "test"))
        Assert.assertThat(test1.stars().size, equalTo(31))

    }

    @Test
    fun starMove() {
        val star = Y2018D10.Star(Y2018D10.Point(0, 0), Y2018D10.Point(1, 0))
        star.move()
        Assert.assertThat(star.position.x, equalTo(1))
        Assert.assertThat(star.position.y, equalTo(0))
        star.move()
        Assert.assertThat(star.position.x, equalTo(2))
        Assert.assertThat(star.position.y, equalTo(0))
        repeat(10) {
            star.move()
        }
        Assert.assertThat(star.position.x, equalTo(12))
        Assert.assertThat(star.position.y, equalTo(0))
    }
    override fun examplePartOne() {
    }

    override fun examplePartTwo() {
    }

    override fun solutionPartOne() {
        val day = Y2018D10()
        Assert.assertThat(day.partOne(), equalTo("AHFGRKEE"))
    }

    override fun solutionPartTwo() {
        val day = Y2018D10()
        Assert.assertThat(day.partTwo().toInt(), equalTo(10243))
    }
}