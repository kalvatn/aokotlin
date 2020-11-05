package com.kalvatn.aoc.year2016

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import io.kotest.matchers.shouldBe

internal class Y2016D01Test : BaseDayTest() {
  @Test
  override suspend fun examplePartOne() {
    Y2016D01(PuzzleInput.ofSingleLine("R2, L3")).partOne().toInt() shouldBe 5
    Y2016D01(PuzzleInput.ofSingleLine("R2, R2, R2")).partOne().toInt() shouldBe 2
    Y2016D01(PuzzleInput.ofSingleLine("R5, L5, R5, R3")).partOne().toInt() shouldBe 12
  }

  @Test
  override suspend fun examplePartTwo() {
    Y2016D01(PuzzleInput.ofSingleLine("R8, R4, R4, R8")).partTwo().toInt() shouldBe 4
  }

  @Test
  override suspend fun solutionPartOne() {
    Y2016D01().partOne().toInt() shouldBe 271
  }

  @Test
  override suspend fun solutionPartTwo() {
    Y2016D01().partTwo().toInt() shouldBe 153
  }
}
