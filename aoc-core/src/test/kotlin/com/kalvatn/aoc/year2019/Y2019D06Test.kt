package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import io.kotlintest.shouldBe

internal class Y2019D06Test : BaseDayTest() {
    @Test
    override suspend fun examplePartOne() {
//        val input = PuzzleInput.ofSingleLine("")
        val input = PuzzleInput.forDay(Year.Y2019, Day.D06, "test")
        val day = Y2019D06(input)
        day.partOne().toInt() shouldBe 42
    }

    @Test
    override suspend fun examplePartTwo() {
    }

    @Test
    override suspend fun solutionPartOne() {
        Y2019D06().partOne().toInt() shouldBe 140608
    }

    @Test
    override suspend fun solutionPartTwo() {
        Y2019D06().partTwo().toInt() shouldBe 337
    }
}
