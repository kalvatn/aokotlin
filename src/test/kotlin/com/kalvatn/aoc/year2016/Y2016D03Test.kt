package com.kalvatn.aoc.year2016

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import io.kotlintest.shouldBe

internal class Y2016D03Test : BaseDayTest() {
    @Test
    override suspend fun examplePartOne() {
    }

    @Test
    override suspend fun examplePartTwo() {
    }

    @Test
    override suspend fun solutionPartOne() {
        Y2016D03().partOne().toInt() shouldBe 982
    }

    @Test
    override suspend fun solutionPartTwo() {
        Y2016D03().partTwo().toInt() shouldBe 1826
    }
}
