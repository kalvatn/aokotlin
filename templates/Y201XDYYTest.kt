package com.kalvatn.aoc.year$YEAR

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import io.kotest.matchers.shouldBe

internal class Y$YEARD$DAYTest : BaseDayTest() {

  @Test
  override suspend fun examplePartOne() {
    val input = PuzzleInput.p1Test(Year.Y$YEAR, Day.D$DAY)
    Y$YEARD$DAY(input).partOne() shouldBe ""
  }

  @Test
  override suspend fun examplePartTwo() {
    val input = PuzzleInput.p2Test(Year.Y$YEAR, Day.D$DAY)
    Y$YEARD$DAY(input).partTwo() shouldBe ""
  }

  @Test
  override suspend fun solutionPartOne() {
    Y$YEARD$DAY().partOne() shouldBe ""
  }

  @Test
  override suspend fun solutionPartTwo() {
    Y$YEARD$DAY().partTwo() shouldBe ""
  }
}
