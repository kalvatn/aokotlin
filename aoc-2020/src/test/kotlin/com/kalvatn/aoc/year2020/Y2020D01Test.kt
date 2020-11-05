package com.kalvatn.aoc.year2020
import org.junit.jupiter.api.Test

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Puzzle
import com.kalvatn.aoc.core.model.Year
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest

@ExperimentalCoroutinesApi
internal class Y2020D01Test {

  private fun puzzle(input:PuzzleInput = PuzzleInput.NULL) = Y2020D01(input)

    @Test
    fun examplePartOne() = runBlockingTest{
//        val input = PuzzleInput.ofSingleLine("")
        val input = PuzzleInput.forDay(Year.Y2020, Day.D01, "test")
        puzzle(input).partOne().toInt() shouldBe 0
    }

    @Test
    fun examplePartTwo() = runBlockingTest {
//        val input = PuzzleInput.ofSingleLine("")
        val input = PuzzleInput.forDay(Year.Y2020, Day.D01, "test")
        Y2020D01(input).partTwo().toInt() shouldBe 0
    }

    @Test
    fun solutionPartOne() = runBlockingTest {
        Y2020D01().partOne().toInt() shouldBe 0
    }

    @Test
    fun solutionPartTwo() = runBlockingTest {
        Y2020D01().partTwo().toInt() shouldBe 0
    }
}
