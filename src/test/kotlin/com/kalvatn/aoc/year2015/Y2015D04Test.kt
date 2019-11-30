package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import io.kotlintest.shouldBe

class Y2015D04Test : BaseDayTest() {

    override suspend fun examplePartOne() {
        val test1 = Y2015D04(PuzzleInput.ofSingleLine("abcdef"))
        test1.partOne().toInt() shouldBe 609043
    }

    override suspend fun examplePartTwo() {
        val test1 = Y2015D04(PuzzleInput.ofSingleLine("abcdef"))
        test1.partTwo().toInt() shouldBe 6742839
    }

    override suspend fun solutionPartOne() {
        val day = Y2015D04()
        day.partOne().toInt() shouldBe 254575
        day.partTwo().toInt() shouldBe 1038736
    }

    override suspend fun solutionPartTwo() {
    }
}