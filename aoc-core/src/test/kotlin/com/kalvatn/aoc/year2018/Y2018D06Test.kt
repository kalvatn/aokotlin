package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import io.kotlintest.shouldBe

class Y2018D06Test : BaseDayTest() {
  @Test
  override suspend fun examplePartOne() {
    val test1 = Y2018D06(PuzzleInput.forDay(Year.Y2018, Day.D06, "test"))
    test1.partOne().toInt() shouldBe 17
  }

  @Test
  override suspend fun examplePartTwo() {
    val test1 = Y2018D06(PuzzleInput.forDay(Year.Y2018, Day.D06, "test"))
    test1.partTwo().toInt() shouldBe 90
  }

  @Test
  override suspend fun solutionPartOne() {
    val day = Y2018D06()
    day.partOne().toInt() shouldBe 3223
  }

  @Test
  override suspend fun solutionPartTwo() {
    val day = Y2018D06()
    day.partTwo().toInt() shouldBe 40495
  }
}
