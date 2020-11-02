package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
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
        val input1 = PuzzleInput.ofSingleLine("80871224585914546619083218645595")
        Y2019D16(input1).partOne().toInt() shouldBe 24176176
        val input2 = PuzzleInput.ofSingleLine("19617804207202209144916044189917")
        Y2019D16(input2).partOne().toInt() shouldBe 73745418
        val input3 = PuzzleInput.ofSingleLine("69317163492948606335995924319873")
        Y2019D16(input3).partOne().toInt() shouldBe 52432133
    }

    @Test
    override suspend fun examplePartTwo() {
        val input1 = PuzzleInput.ofSingleLine("03036732577212944063491565474664")
        Y2019D16(input1).partTwo().toInt() shouldBe 84462026
        val input2 = PuzzleInput.ofSingleLine("02935109699940807407585447034323")
        Y2019D16(input2).partTwo().toInt() shouldBe 78725270
        val input3 = PuzzleInput.ofSingleLine("03081770884921959731165446850517")
        Y2019D16(input3).partTwo().toInt() shouldBe 53553731
    }

    @Test
    override suspend fun solutionPartOne() {
        Y2019D16().partOne().toInt() shouldBe 34841690
    }

    @Test
    override suspend fun solutionPartTwo() {
        Y2019D16().partTwo().toInt() shouldBe 48776785
    }
}
