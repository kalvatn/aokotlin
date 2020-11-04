package com.kalvatn.aoc.year2019
import org.junit.jupiter.api.Test

import com.kalvatn.aoc.BaseDayTest
import io.kotest.matchers.shouldBe

internal class Y2019D05Test : BaseDayTest() {
  @Test
  override suspend fun examplePartOne() {
    IntcodeComputer(listOf(1002, 4, 3, 4, 33)).run().memory() shouldBe listOf<Long>(1002, 4, 3, 4, 99)
  }

  @Test
  override suspend fun examplePartTwo() {
    val zeroOrNotPositionMode = IntcodeComputer(listOf(3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9))
    val zeroOrNotImmediateMode = IntcodeComputer(listOf(3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1))
    (-1000..1000L).forEach {
      zeroOrNotImmediateMode.runDiagnostic(it) shouldBe (if (it == 0L) 0 else 1)
      zeroOrNotPositionMode.runDiagnostic(it) shouldBe (if (it == 0L) 0 else 1)
    }
  }

  @Test
  override suspend fun solutionPartOne() {
    Y2019D05().partOne().toInt() shouldBe 7839346
  }

  @Test
  override suspend fun solutionPartTwo() {
    Y2019D05().partTwo().toInt() shouldBe 447803
  }
}
