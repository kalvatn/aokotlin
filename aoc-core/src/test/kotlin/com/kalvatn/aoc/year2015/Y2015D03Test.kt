package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import io.kotlintest.shouldBe

class Y2015D03Test : BaseDayTest() {

  @Test
  override suspend fun examplePartOne() {
    Y2015D03(PuzzleInput.ofSingleLine(">")).partOne().toInt() shouldBe 2
    Y2015D03(PuzzleInput.ofSingleLine("^>v<")).partOne().toInt() shouldBe 4
    Y2015D03(PuzzleInput.ofSingleLine("^v^v^v^v^v")).partOne().toInt() shouldBe 2
  }

  @Test
  override suspend fun examplePartTwo() {
    Y2015D03(PuzzleInput.ofSingleLine("^>v<")).partTwo().toInt() shouldBe 3
    Y2015D03(PuzzleInput.ofSingleLine("^v^v^v^v^v")).partTwo().toInt() shouldBe 11
  }

  @Test
  override suspend fun solutionPartOne() {
    Y2015D03().partOne().toInt() shouldBe 2572
  }

  @Test
  override suspend fun solutionPartTwo() {
    Y2015D03().partTwo().toInt() shouldBe 2631
  }
}
