package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import io.kotlintest.shouldBe

class Y2015D03Test : BaseDayTest() {

    override suspend fun examplePartOne() {
        val test1 = Y2015D03(PuzzleInput.ofSingleLine(">"))
        val test2 = Y2015D03(PuzzleInput.ofSingleLine("^>v<"))
        val test3 = Y2015D03(PuzzleInput.ofSingleLine("^v^v^v^v^v"))
        test1.partOne().toInt() shouldBe 2
        test2.partOne().toInt() shouldBe 4
        test3.partOne().toInt() shouldBe 2
    }

    override suspend fun examplePartTwo() {
        val test2 = Y2015D03(PuzzleInput.ofSingleLine("^>v<"))
        val test3 = Y2015D03(PuzzleInput.ofSingleLine("^v^v^v^v^v"))
        test2.partTwo().toInt() shouldBe 3
        test3.partTwo().toInt() shouldBe 11
    }

    override suspend fun solutionPartOne() {
        val day = Y2015D03()
        day.partOne().toInt() shouldBe 2572
        day.partTwo().toInt() shouldBe 2631
    }

    override suspend fun solutionPartTwo() {
        val day = Y2015D03()
        day.partTwo().toInt() shouldBe 2631
    }
}