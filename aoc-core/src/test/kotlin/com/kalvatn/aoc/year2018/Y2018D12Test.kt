package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import io.kotest.matchers.shouldBe

class Y2018D12Test : BaseDayTest() {
  @Test
  override suspend fun examplePartOne() {
    val test1 = Y2018D12(PuzzleInput.forDay(Year.Y2018, Day.D12, "test"))
    test1.partOne().toInt() shouldBe 325
  }

  @Test
  override suspend fun examplePartTwo() {
    val test1 = Y2018D12(PuzzleInput.forDay(Year.Y2018, Day.D12, "test"))
    test1.partTwo().toLong() shouldBe 350000000080
  }

  @Test
  override suspend fun solutionPartOne() {
    val day = Y2018D12()
    day.partOne().toInt() shouldBe 3798
  }

  @Test
  override suspend fun solutionPartTwo() {
    val day = Y2018D12()
    day.partTwo().toLong() shouldBe 3900000002212
  }
}
