package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import io.kotlintest.shouldBe

class Y2015D07Test : BaseDayTest() {

    @Test
    override suspend fun examplePartOne() {
        val day = Y2015D07(PuzzleInput.forDay(Year.Y2015, Day.D07, "test"))
        day.partOne().toInt() shouldBe 65412

    }

    @Test
    override suspend fun examplePartTwo() {
        val day = Y2015D07(PuzzleInput.forDay(Year.Y2015, Day.D07, "test"))
    }

    @Test
    override suspend fun solutionPartOne() {
        val day = Y2015D07()
        day.partOne().toInt() shouldBe 16076
    }

    @Test
    override suspend fun solutionPartTwo() {
        val day = Y2015D07()
        day.partTwo().toInt() shouldBe 2797
    }
}