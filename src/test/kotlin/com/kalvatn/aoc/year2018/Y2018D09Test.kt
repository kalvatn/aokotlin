package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import io.kotlintest.shouldBe

class Y2018D09Test : BaseDayTest() {
    override suspend fun examplePartOne() {
        val test1 = Y2018D09(PuzzleInput.ofSingleLine("9 players; last marble is worth 23 points"))
        test1.partOne().toInt() shouldBe 32
        val test2 = Y2018D09(PuzzleInput.ofSingleLine("10 players; last marble is worth 1618 points"))
        test2.partOne().toInt() shouldBe 8317
        val test3 = Y2018D09(PuzzleInput.ofSingleLine("13 players; last marble is worth 7999 points"))
        test3.partOne().toInt() shouldBe 146373
        val test4 = Y2018D09(PuzzleInput.ofSingleLine("17 players; last marble is worth 1104 points"))
        test4.partOne().toInt() shouldBe 2764
        val test5 = Y2018D09(PuzzleInput.ofSingleLine("21 players; last marble is worth 6111 points"))
        test5.partOne().toInt() shouldBe 54718
        val test6 = Y2018D09(PuzzleInput.ofSingleLine("30 players; last marble is worth 5807 points"))
        test6.partOne().toInt() shouldBe 37305
    }

    override suspend fun examplePartTwo() {
    }

    override suspend fun solutionPartOne() {
        val day = Y2018D09()
        day.partOne().toInt() shouldBe 370210
    }

    override suspend fun solutionPartTwo() {
        val day = Y2018D09()
        day.partTwo().toLong() shouldBe 3101176548
    }
}