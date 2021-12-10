package com.kalvatn.aoc.year2021

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import io.kotest.matchers.shouldBe

internal class Y2021D08Test : BaseDayTest() {

  @Test
  override suspend fun examplePartOne() {
    val input = PuzzleInput.p1Test(Year.Y2021, Day.D08)
    Y2021D08(input).partOne() shouldBe "26"
  }

  @Test
  override suspend fun examplePartTwo() {
    val example = PuzzleInput.ofSingleLine("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf")
    Y2021D08(example).partTwo() shouldBe "5353"

    val input = PuzzleInput.p1Test(Year.Y2021, Day.D08)
    Y2021D08(input).partTwo() shouldBe "61229"
  }

  @Test
  override suspend fun solutionPartOne() {
    Y2021D08().partOne() shouldBe "392"
  }

  @Test
  override suspend fun solutionPartTwo() {
    Y2021D08().partTwo() shouldBe "1004688"
  }
}
