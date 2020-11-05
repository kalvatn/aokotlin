package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import io.kotest.matchers.shouldBe

internal class Y2019D10Test : BaseDayTest() {
  @Test
  override suspend fun examplePartOne() {
    val input = PuzzleInput.forDay(Year.Y2019, Day.D10, "test")
    Y2019D10(input).partOne().toInt() shouldBe 210
  }

  @Test
  override suspend fun examplePartTwo() {
    val input = PuzzleInput.forDay(Year.Y2019, Day.D10, "test")
    Y2019D10(input).partTwo().toInt() shouldBe 802
  }

  @Test
  override suspend fun solutionPartOne() {
    Y2019D10().partOne().toInt() shouldBe 280
  }

  @Test
  override suspend fun solutionPartTwo() {
    Y2019D10().partTwo().toInt() shouldBe 706
  }
}
