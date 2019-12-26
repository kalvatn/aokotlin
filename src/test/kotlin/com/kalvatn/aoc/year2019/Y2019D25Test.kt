package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import io.kotlintest.shouldBe

internal class Y2019D25Test : BaseDayTest() {
    @Test
    override suspend fun examplePartOne() {
    }

    @Test
    override suspend fun examplePartTwo() {
    }

    @Test
    override suspend fun solutionPartOne() {
        Y2019D25().partOne().toInt() shouldBe 20483
    }

    @Test
    override suspend fun solutionPartTwo() {
        Y2019D25().partTwo().toInt() shouldBe 0
    }
}
