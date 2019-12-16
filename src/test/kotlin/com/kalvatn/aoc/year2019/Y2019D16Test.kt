package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Puzzle
import com.kalvatn.aoc.core.model.Year
import io.kotlintest.shouldBe

internal class Y2019D16Test : BaseDayTest() {

    @Test
    fun pattern() {
        Y2019D16().extendPattern(0).take(5).toList() shouldBe listOf(1, 0, -1, 0, 1)
        Y2019D16().extendPattern(1).take(15).toList() shouldBe listOf(0, 1, 1, 0, 0, -1, -1, 0, 0, 1, 1, 0, 0, -1, -1)
        Y2019D16().extendPattern(2).take(15).toList() shouldBe listOf(0, 0, 1, 1, 1, 0, 0, 0, -1, -1, -1, 0, 0, 0, 1)
        Y2019D16().extendPattern(5).take(41).toList() shouldBe
                listOf(0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0)
    }

    @Test
    fun phase() {
        Y2019D16().phase(listOf(1, 2, 3, 4, 5, 6, 7, 8)) shouldBe listOf(4, 8, 2, 2, 6, 1, 5, 8)
        Y2019D16().phase(listOf(4, 8, 2, 2, 6, 1, 5, 8)) shouldBe listOf(3, 4, 0, 4, 0, 4, 3, 8)
    }


    @Test
    override suspend fun examplePartOne() {
        val input = PuzzleInput.ofSingleLine("80871224585914546619083218645595")
        Y2019D16(input).partOne() shouldBe "24176176"
    }

    @Test
    override suspend fun examplePartTwo() {
//        val input = PuzzleInput.ofSingleLine("")
//        val input = PuzzleInput.forDay(Year.Y2019, Day.D16, "test")
//        Y2019D16(input).partTwo().toInt() shouldBe 0
    }

    @Test
    override suspend fun solutionPartOne() {
        Y2019D16().partOne().toInt() shouldBe 34841690
    }

    @Test
    override suspend fun solutionPartTwo() {
//        Y2019D16().partTwo().toInt() shouldBe 0
    }
}
