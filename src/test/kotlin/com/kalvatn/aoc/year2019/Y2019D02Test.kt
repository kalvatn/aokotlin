package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import com.kalvatn.aoc.extensions.extractIntegers
import io.kotlintest.shouldBe

internal class Y2019D02Test : BaseDayTest() {
    @Test
    override suspend fun examplePartOne() {
        IntcodeComputer(listOf(1, 0, 0, 0, 99)).process().memory shouldBe listOf(2, 0, 0, 0, 99)
        IntcodeComputer(listOf(2, 3, 0, 3, 99)).process().memory shouldBe listOf(2, 3, 0, 6, 99)
        IntcodeComputer(listOf(2, 4, 4, 5, 99, 0)).process().memory shouldBe listOf(2, 4, 4, 5, 99, 9801)
        IntcodeComputer(listOf(1, 1, 1, 4, 99, 5, 6, 0, 99)).process().memory shouldBe listOf(30, 1, 1, 4, 2, 5, 6, 0, 99)
    }

    @Test
    override suspend fun examplePartTwo() {
        val computer = IntcodeComputer(PuzzleInput.forDay(Year.Y2019, Day.D02).singleLine().extractIntegers())
        computer.findVerbNounPairThatProducesSolution(3654868) shouldBe Pair(12, 2)
    }

    @Test
    override suspend fun solutionPartOne() {
        val day = Y2019D02()
        day.partOne().toInt() shouldBe 3654868
    }

    @Test
    override suspend fun solutionPartTwo() {
        val day = Y2019D02()
        day.partTwo().toInt() shouldBe 7014
    }
}