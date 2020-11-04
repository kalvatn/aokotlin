package com.kalvatn.aoc.year2019
import org.junit.jupiter.api.Test

import com.kalvatn.aoc.BaseDayTest
import io.kotest.matchers.shouldBe

internal class Y2019D09Test : BaseDayTest() {
  @Test
  override suspend fun examplePartOne() {
    IntcodeComputer(listOf(109, 1, 204, -1, 1001, 100, 1, 100, 1008, 100, 16, 101, 1006, 101, 0, 99)).run()
      .output() shouldBe listOf<Long>(109, 1, 204, -1, 1001, 100, 1, 100, 1008, 100, 16, 101, 1006, 101, 0, 99)
    IntcodeComputer(listOf(1102, 34915192, 34915192, 7, 4, 7, 99, 0)).run().outputLast().toString().length shouldBe 16
    IntcodeComputer(listOf(104, 1125899906842624, 99)).run().outputLast() shouldBe 1125899906842624
  }

  @Test
  override suspend fun examplePartTwo() {
  }

  @Test
  override suspend fun solutionPartOne() {
    Y2019D09().partOne().toLong() shouldBe 2494485073
  }

  @Test
  override suspend fun solutionPartTwo() {
    Y2019D09().partTwo().toLong() shouldBe 44997
  }
}
