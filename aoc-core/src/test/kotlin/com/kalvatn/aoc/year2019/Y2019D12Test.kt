package com.kalvatn.aoc.year2019
import org.junit.jupiter.api.Test

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import io.kotest.matchers.shouldBe

internal class Y2019D12Test : BaseDayTest() {
  @Test
  override suspend fun examplePartOne() {
    val input = PuzzleInput.forDay(Year.Y2019, Day.D12, "test2")
    Y2019D12(input).partOne().toInt() shouldBe 14645
  }

  @Test
  override suspend fun examplePartTwo() {
    val input = PuzzleInput.forDay(Year.Y2019, Day.D12, "test")
    Y2019D12(input).partTwo().toInt() shouldBe 2772
  }

  @Test
  override suspend fun solutionPartOne() {
    Y2019D12().partOne().toInt() shouldBe 6735
  }

  @Test
  override suspend fun solutionPartTwo() {
    Y2019D12().partTwo().toLong() shouldBe 326489627728984
  }
}
