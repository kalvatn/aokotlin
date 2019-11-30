package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import io.kotlintest.shouldBe

class Y2015D02Test : BaseDayTest() {

    override suspend fun examplePartOne() {
        val test1 = Y2015D02(PuzzleInput.ofSingleLine("2x3x4"))
        test1.partOne().toInt() shouldBe 58
        val test2 = Y2015D02(PuzzleInput.ofSingleLine("1x1x10"))
        test2.partOne().toInt() shouldBe 43
    }

    override suspend fun examplePartTwo() {
        val test1 = Y2015D02(PuzzleInput.ofSingleLine("2x3x4"))
        test1.partTwo().toInt() shouldBe 34
        val test2 = Y2015D02(PuzzleInput.ofSingleLine("1x1x10"))
        test2.partTwo().toInt() shouldBe 14
    }

    override suspend fun solutionPartOne() {
        val day = Y2015D02()
        day.partOne().toInt() shouldBe 1606483
    }

    override suspend fun solutionPartTwo() {
        val day = Y2015D02()
        day.partTwo().toInt() shouldBe 3842356
    }
}