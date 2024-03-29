package com.kalvatn.aoc.year2021

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import io.kotest.matchers.shouldBe

internal class Y2021D11Test : BaseDayTest() {

  @Test
  override suspend fun examplePartOne() {
    val input = PuzzleInput.p1Test(Year.Y2021, Day.D11)
    Y2021D11(input).partOne() shouldBe "1656"
  }

  @Test
  override suspend fun examplePartTwo() {
    val input = PuzzleInput.p1Test(Year.Y2021, Day.D11)
    Y2021D11(input).partTwo() shouldBe "195"
  }

  @Test
  override suspend fun solutionPartOne() {
    Y2021D11().partOne() shouldBe "1594"
  }

  @Test
  override suspend fun solutionPartTwo() {
    Y2021D11().partTwo() shouldBe "437"
  }
}
