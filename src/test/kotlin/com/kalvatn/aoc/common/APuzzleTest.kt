package com.kalvatn.aoc.common

import com.kalvatn.aoc.year2015.Y2015D04
import com.kalvatn.aoc.year2015.Y2015D05
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Test

class APuzzleTest {

    val DAY = Y2015D04()

    @Test
    fun getInput() {
        Assert.assertThat(DAY.input.singleLine(), equalTo("bgvyzdsv"))

        val withCustomInput = Y2015D04(PuzzleInput.ofSingleLine("asdf"))
        Assert.assertThat(withCustomInput.input.singleLine(), equalTo("asdf"))

    }

    @Test
    fun run() {
        val day = Y2015D05()

        day.run(true)
        day.run()
    }

    @Test
    fun toStringMethod() {
        Assert.assertThat(DAY.toString(), equalTo("2015-04"))

    }

    @Test
    fun getYear() {
        Assert.assertThat(DAY.year, equalTo(Year.Y2015))
    }

    @Test
    fun getDay() {
        Assert.assertThat(DAY.day, equalTo(Day.D04))
    }
}