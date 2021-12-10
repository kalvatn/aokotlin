package com.kalvatn.aoc.year2021

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import io.kotest.matchers.shouldBe

internal class Y2021D09Test : BaseDayTest() {

  @Test
  override suspend fun examplePartOne() {
    val input = PuzzleInput.p1Test(Year.Y2021, Day.D09)
    Y2021D09(input).partOne() shouldBe "15"
  }

  @Test
  override suspend fun examplePartTwo() {
    val input = PuzzleInput.p1Test(Year.Y2021, Day.D09)
    Y2021D09(input).partTwo() shouldBe "1134"
  }

  @Test
  override suspend fun solutionPartOne() {
    Y2021D09().partOne() shouldBe "545"
  }

  @Test
  override suspend fun solutionPartTwo() {
    Y2021D09().partTwo() shouldBe "950600"
  }
}
