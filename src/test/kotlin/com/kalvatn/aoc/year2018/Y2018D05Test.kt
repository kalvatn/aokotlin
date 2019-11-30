package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import io.kotlintest.shouldBe

class Y2018D05Test : BaseDayTest() {
    @Test
    override suspend fun examplePartOne() {
        val test1 = Y2018D05(PuzzleInput.ofSingleLine("dabAcCaCBAcCcaDA"))
        test1.partOne().toInt() shouldBe 10
    }

    @Test
    override suspend fun examplePartTwo() {
        val test1 = Y2018D05(PuzzleInput.ofSingleLine("dabAcCaCBAcCcaDA"))
        test1.partTwo().toInt() shouldBe 4
    }

    @Test
    override suspend fun solutionPartOne() {
        val day = Y2018D05()
        day.partOne().toInt() shouldBe 9526
    }

    @Test
    override suspend fun solutionPartTwo() {
        val day = Y2018D05()
        day.partTwo().toInt() shouldBe 6694
    }
}