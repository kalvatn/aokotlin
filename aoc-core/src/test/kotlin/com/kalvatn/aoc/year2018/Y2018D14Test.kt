package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import io.kotest.matchers.shouldBe

class Y2018D14Test : BaseDayTest() {
  @Test
  override suspend fun examplePartOne() {
    val test1 = Y2018D14(PuzzleInput.ofSingleLine("5"))
    test1.partOne() shouldBe "0124515891"
  }

  @Test
  override suspend fun examplePartTwo() {
    val test1 = Y2018D14(PuzzleInput.ofSingleLine("59414"))
    test1.partTwo().toInt() shouldBe 2018
  }

  @Test
  override suspend fun solutionPartOne() {
    val day = Y2018D14()
    day.partOne() shouldBe "6910849249"
  }

  @Test
  override suspend fun solutionPartTwo() {
    val day = Y2018D14()
    day.partTwo().toInt() shouldBe 20330673
  }
}
