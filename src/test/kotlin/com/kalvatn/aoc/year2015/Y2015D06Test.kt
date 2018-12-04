package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.common.Day
import com.kalvatn.aoc.common.PuzzleInput
import com.kalvatn.aoc.common.Year
import com.kalvatn.aoc.year2015.Y2015D06.Action.*
import com.kalvatn.aoc.year2015.Y2015D06.Command
import com.kalvatn.aoc.year2015.Y2015D06.Command.Companion.fromString
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test

class Y2015D06Test {
    @Test
    fun commandParse() {
        assertThat(fromString("turn on 0,0 through 999,999"), equalTo(Command(ON, Pair(0, 0), Pair(999, 999))))
        assertThat(fromString("toggle 0,0 through 999,0"), equalTo(Command(TOGGLE, Pair(0, 0), Pair(999, 0))))
        assertThat(fromString("turn off 499,499 through 500,500"), equalTo(Command(OFF, Pair(499, 499), Pair(500, 500))))
    }

    @Test
    fun testExamples() {
        val day = Y2015D06(PuzzleInput.forDay(Year.Y2015, Day.D06, "test"))

        assertThat(day.partOne().toInt(), equalTo((1000 * 1000) - 1))
        assertThat(day.partTwo().toInt(), equalTo(2000001))

    }


    @Test
    fun testSolution() {
        val day = Y2015D06()
        assertThat(day.partOne().toInt(), equalTo(543903))
        assertThat(day.partTwo().toInt(), equalTo(14687245))
    }
}