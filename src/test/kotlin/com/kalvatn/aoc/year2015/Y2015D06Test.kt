package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import com.kalvatn.aoc.year2015.Y2015D06.Action.*
import com.kalvatn.aoc.year2015.Y2015D06.Command
import com.kalvatn.aoc.year2015.Y2015D06.Command.Companion.fromString
import io.kotlintest.shouldBe

class Y2015D06Test : BaseDayTest() {
    @Test
    fun commandParse() {
        fromString("turn on 0,0 through 999,999") shouldBe Command(ON, Pair(0, 0), Pair(999, 999))
        fromString("toggle 0,0 through 999,0") shouldBe Command(TOGGLE, Pair(0, 0), Pair(999, 0))
        fromString("turn off 499,499 through 500,500") shouldBe Command(OFF, Pair(499, 499), Pair(500, 500))
    }

    @Test
    override suspend fun examplePartOne() {
        val input = PuzzleInput.forDay(Year.Y2015, Day.D06, "test")
        Y2015D06(input).partOne().toInt() shouldBe (1000 * 1000) - 1
    }

    @Test
    override suspend fun examplePartTwo() {
        val input = PuzzleInput.forDay(Year.Y2015, Day.D06, "test")
        Y2015D06(input).partTwo().toInt() shouldBe 2000001
    }

    @Test
    override suspend fun solutionPartOne() {
        Y2015D06().partOne().toInt() shouldBe 543903
    }

    @Test
    override suspend fun solutionPartTwo() {
        Y2015D06().partTwo().toInt() shouldBe 14687245
    }
}