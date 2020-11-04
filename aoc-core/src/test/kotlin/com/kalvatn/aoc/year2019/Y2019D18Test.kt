package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import io.kotest.matchers.shouldBe

internal class Y2019D18Test : BaseDayTest() {
  @Test
  override suspend fun examplePartOne() {
    val input1 = PuzzleInput.forDay(Year.Y2019, Day.D18, "test")
    Y2019D18(input1).partOne().toInt() shouldBe 8

    val input2 = PuzzleInput.forDay(Year.Y2019, Day.D18, "test2")
    Y2019D18(input2).partOne().toInt() shouldBe 86

    val input3 = PuzzleInput.forDay(Year.Y2019, Day.D18, "test3")
    Y2019D18(input3).partOne().toInt() shouldBe 132

    val input4 = PuzzleInput.forDay(Year.Y2019, Day.D18, "test4")
    Y2019D18(input4).partOne().toInt() shouldBe 136

    val input5 = PuzzleInput.forDay(Year.Y2019, Day.D18, "test5")
    Y2019D18(input5).partOne().toInt() shouldBe 81
  }

  @Test
  override suspend fun examplePartTwo() {
//    val input = PuzzleInput.forDay(Year.Y2019, Day.D18, "test")
//        Y2019D18(input).partTwo().toInt() shouldBe 0
  }

  @Test
  override suspend fun solutionPartOne() {
    Y2019D18().partOne().toInt() shouldBe 5808
  }

  @Test
  override suspend fun solutionPartTwo() {
    Y2019D18().partTwo().toInt() shouldBe 1992
  }
}
