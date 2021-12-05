package com.kalvatn.aoc.year2021

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import io.kotest.matchers.shouldBe

internal class Y2021D05Test : BaseDayTest() {

  @Test
  override suspend fun examplePartOne() {
    val input = PuzzleInput.p1Test(Year.Y2021, Day.D05)
    Y2021D05(input).partOne() shouldBe "5"
  }

  @Test
  override suspend fun examplePartTwo() {
    val input = PuzzleInput.p1Test(Year.Y2021, Day.D05)
    Y2021D05(input).partTwo() shouldBe "12"
  }

  @Test
  override suspend fun solutionPartOne() {
    Y2021D05().partOne() shouldBe "4745"
  }

  @Test
  override suspend fun solutionPartTwo() {
    Y2021D05().partTwo() shouldBe "18442"
  }
}
