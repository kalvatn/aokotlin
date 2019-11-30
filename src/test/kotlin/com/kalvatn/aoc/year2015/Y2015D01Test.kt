package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import io.kotlintest.shouldBe

class Y2015D01Test : BaseDayTest() {

    override suspend fun examplePartOne() {
        val test1 = Y2015D01(PuzzleInput.ofSingleLine(")"))
        test1.partOne().toInt() shouldBe -1
    }

    override suspend fun examplePartTwo() {
        val test1 = Y2015D01(PuzzleInput.ofSingleLine(")"))
        test1.partTwo().toInt() shouldBe 1
    }

    override suspend fun solutionPartOne() {
        val day = Y2015D01()
        day.partOne().toInt() shouldBe 138
    }

    override suspend fun solutionPartTwo() {
        val day = Y2015D01()
        day.partTwo().toInt() shouldBe 1771
    }
}