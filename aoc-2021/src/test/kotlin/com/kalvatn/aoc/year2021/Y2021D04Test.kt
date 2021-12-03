package com.kalvatn.aoc.year2021

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import io.kotest.matchers.shouldBe

internal class Y2021D04Test : BaseDayTest() {

  @Test
  override suspend fun examplePartOne() {
    val input = PuzzleInput.p1Test(Year.Y2021, Day.D04)
    Y2021D04(input).partOne() shouldBe ""
  }

  @Test
  override suspend fun examplePartTwo() {
    val input = PuzzleInput.p2Test(Year.Y2021, Day.D04)
    Y2021D04(input).partTwo() shouldBe ""
  }

  @Test
  override suspend fun solutionPartOne() {
    Y2021D04().partOne() shouldBe ""
  }

  @Test
  override suspend fun solutionPartTwo() {
    Y2021D04().partTwo() shouldBe ""
  }
}
