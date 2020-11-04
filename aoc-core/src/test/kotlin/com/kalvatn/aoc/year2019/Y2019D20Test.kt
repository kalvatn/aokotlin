package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import io.kotest.matchers.shouldBe

internal class Y2019D20Test : BaseDayTest() {
  @Test
  override suspend fun examplePartOne() {
    val input1 = PuzzleInput.forDay(Year.Y2019, Day.D20, "test")
    Y2019D20(input1).partOne().toInt() shouldBe 23
    val input2 = PuzzleInput.forDay(Year.Y2019, Day.D20, "test2")
    Y2019D20(input2).partOne().toInt() shouldBe 58
  }

  @Test
  override suspend fun examplePartTwo() {
    val input3 = PuzzleInput.forDay(Year.Y2019, Day.D20, "test3")
    Y2019D20(input3).partTwo().toInt() shouldBe 396
  }

  @Test
  override suspend fun solutionPartOne() {
    Y2019D20().partOne().toInt() shouldBe 690
  }

  @Test
  override suspend fun solutionPartTwo() {
    Y2019D20().partTwo().toInt() shouldBe 7976
  }
}
