package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.common.Day
import com.kalvatn.aoc.common.PuzzleInput
import com.kalvatn.aoc.common.Year
import com.kalvatn.aoc.year2015.Y2015D06.Action.*
import com.kalvatn.aoc.year2015.Y2015D06.Command
import com.kalvatn.aoc.year2015.Y2015D06.Command.Companion.fromString
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test

class Y2015D07Test : BaseDayTest() {
    @Test
    fun commandParse() {
        assertThat(fromString("turn on 0,0 through 999,999"), equalTo(Command(ON, Pair(0, 0), Pair(999, 999))))
        assertThat(fromString("toggle 0,0 through 999,0"), equalTo(Command(TOGGLE, Pair(0, 0), Pair(999, 0))))
        assertThat(fromString("turn off 499,499 through 500,500"), equalTo(Command(OFF, Pair(499, 499), Pair(500, 500))))
    }

    @Test
    override fun examplePartOne() {
        val day = Y2015D07(PuzzleInput.forDay(Year.Y2015, Day.D07, "test"))
        assertThat(day.partOne().toInt(), equalTo(65412))

    }

    @Test
    override fun examplePartTwo() {
        return
    }

    @Test
    override fun solutionPartOne() {
        val day = Y2015D07()
        assertThat(day.partOne().toInt(), equalTo(16076))
    }

    @Test
    override fun solutionPartTwo() {
        val day = Y2015D07()
        assertThat(day.partTwo().toInt(), equalTo(2797))
    }
}