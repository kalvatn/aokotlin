package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import io.kotlintest.shouldBe

internal class Y2019D23Test : BaseDayTest() {
    @Test
    override suspend fun examplePartOne() {
//        val input = PuzzleInput.ofSingleLine("")
        val input = PuzzleInput.forDay(Year.Y2019, Day.D23, "test")
        Y2019D23(input).partOne().toInt() shouldBe 0
    }

    @Test
    override suspend fun examplePartTwo() {
//        val input = PuzzleInput.ofSingleLine("")
        val input = PuzzleInput.forDay(Year.Y2019, Day.D23, "test")
        Y2019D23(input).partTwo().toInt() shouldBe 0
    }

    @Test
    override suspend fun solutionPartOne() {
        Y2019D23().partOne().toInt() shouldBe 0
    }

    @Test
    override suspend fun solutionPartTwo() {
        Y2019D23().partTwo().toInt() shouldBe 0
    }
}
