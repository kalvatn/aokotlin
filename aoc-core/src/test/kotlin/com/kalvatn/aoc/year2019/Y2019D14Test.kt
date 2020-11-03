package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import io.kotlintest.shouldBe

internal class Y2019D14Test : BaseDayTest() {
  @Test
  override suspend fun examplePartOne() {
    val input1 = PuzzleInput.forDay(Year.Y2019, Day.D14, "test")
    Y2019D14(input1).partOne().toInt() shouldBe 165
    val input2 = PuzzleInput.forDay(Year.Y2019, Day.D14, "test2")
    Y2019D14(input2).partOne().toInt() shouldBe 13312
    val input3 = PuzzleInput.forDay(Year.Y2019, Day.D14, "test3")
    Y2019D14(input3).partOne().toInt() shouldBe 180697
    val input4 = PuzzleInput.forDay(Year.Y2019, Day.D14, "test4")
    Y2019D14(input4).partOne().toInt() shouldBe 2210736
  }

  @Test
  override suspend fun examplePartTwo() {
    val input2 = PuzzleInput.forDay(Year.Y2019, Day.D14, "test2")
    Y2019D14(input2).partTwo().toLong() shouldBe 82892753
    val input3 = PuzzleInput.forDay(Year.Y2019, Day.D14, "test3")
    Y2019D14(input3).partTwo().toLong() shouldBe 5586022
    val input4 = PuzzleInput.forDay(Year.Y2019, Day.D14, "test4")
    Y2019D14(input4).partTwo().toLong() shouldBe 460664
  }

  @Test
  override suspend fun solutionPartOne() {
    Y2019D14().partOne().toInt() shouldBe 443537
  }

  @Test
  override suspend fun solutionPartTwo() {
    Y2019D14().partTwo().toInt() shouldBe 2910558
  }
}
