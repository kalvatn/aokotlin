package com.kalvatn.aoc.year2015
import org.junit.jupiter.api.Test

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import io.kotest.matchers.shouldBe

class Y2015D02Test : BaseDayTest() {

  @Test
  override suspend fun examplePartOne() {
    Y2015D02(PuzzleInput.ofSingleLine("2x3x4")).partOne().toInt() shouldBe 58
    Y2015D02(PuzzleInput.ofSingleLine("1x1x10")).partOne().toInt() shouldBe 43
  }

  @Test
  override suspend fun examplePartTwo() {
    Y2015D02(PuzzleInput.ofSingleLine("2x3x4")).partTwo().toInt() shouldBe 34
    Y2015D02(PuzzleInput.ofSingleLine("1x1x10")).partTwo().toInt() shouldBe 14
  }

  @Test
  override suspend fun solutionPartOne() {
    Y2015D02().partOne().toInt() shouldBe 1606483
  }

  @Test
  override suspend fun solutionPartTwo() {
    Y2015D02().partTwo().toInt() shouldBe 3842356
  }
}
