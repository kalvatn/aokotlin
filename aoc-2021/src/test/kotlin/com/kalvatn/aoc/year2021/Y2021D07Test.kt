package com.kalvatn.aoc.year2021

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import io.kotest.matchers.shouldBe

internal class Y2021D07Test : BaseDayTest() {

  @Test
  override suspend fun examplePartOne() {
    val input = PuzzleInput.p1Test(Year.Y2021, Day.D07)
    Y2021D07(input).partOne() shouldBe "37"
  }

  @Test
  override suspend fun examplePartTwo() {
    val input = PuzzleInput.p1Test(Year.Y2021, Day.D07)
    Y2021D07(input).partTwo() shouldBe "168"
  }

  @Test
  override suspend fun solutionPartOne() {
    Y2021D07().partOne() shouldBe "344535"
  }

  @Test
  override suspend fun solutionPartTwo() {
    Y2021D07().partTwo() shouldBe "95581659"
  }
}
