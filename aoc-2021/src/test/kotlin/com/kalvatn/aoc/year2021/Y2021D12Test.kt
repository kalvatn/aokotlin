package com.kalvatn.aoc.year2021

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import io.kotest.matchers.shouldBe

internal class Y2021D12Test : BaseDayTest() {

  @Test
  override suspend fun examplePartOne() {
    val input = PuzzleInput.p1Test(Year.Y2021, Day.D12)
    Y2021D12(input).partOne() shouldBe "10"
  }

  @Test
  override suspend fun examplePartTwo() {
    val input = PuzzleInput.p1Test(Year.Y2021, Day.D12)
    Y2021D12(input).partTwo() shouldBe "36"
  }

  @Test
  override suspend fun solutionPartOne() {
    Y2021D12().partOne() shouldBe "5157"
  }

  @Test
  override suspend fun solutionPartTwo() {
    Y2021D12().partTwo() shouldBe "144309"
  }
}
