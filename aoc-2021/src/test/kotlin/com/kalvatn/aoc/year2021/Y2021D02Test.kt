package com.kalvatn.aoc.year2021

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import io.kotest.matchers.shouldBe

internal class Y2021D02Test : BaseDayTest() {

  @Test
  override suspend fun examplePartOne() {
    val input = PuzzleInput.p1Test(Year.Y2021, Day.D02)
    Y2021D02(input).partOne() shouldBe "150"
  }

  @Test
  override suspend fun examplePartTwo() {
    val input = PuzzleInput.p1Test(Year.Y2021, Day.D02)
    Y2021D02(input).partTwo() shouldBe "900"
  }

  @Test
  override suspend fun solutionPartOne() {
    Y2021D02().partOne() shouldBe "2150351"
  }

  @Test
  override suspend fun solutionPartTwo() {
    Y2021D02().partTwo() shouldBe "1842742223"
  }
}
