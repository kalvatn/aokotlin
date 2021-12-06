package com.kalvatn.aoc.year2021

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import io.kotest.matchers.shouldBe

internal class Y2021D06Test : BaseDayTest() {

  @Test
  override suspend fun examplePartOne() {
    val input = PuzzleInput.p1Test(Year.Y2021, Day.D06)
    Y2021D06(input).partOne() shouldBe "5934"
  }

  @Test
  override suspend fun examplePartTwo() {
    val input = PuzzleInput.p1Test(Year.Y2021, Day.D06)
    Y2021D06(input).partTwo() shouldBe "26984457539"
  }

  @Test
  override suspend fun solutionPartOne() {
    Y2021D06().partOne() shouldBe "355386"
  }

  @Test
  override suspend fun solutionPartTwo() {
    Y2021D06().partTwo() shouldBe "1613415325809"
  }
}
