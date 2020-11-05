package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import io.kotest.matchers.shouldBe

internal class Y2019D07Test : BaseDayTest() {
  @Test
  override suspend fun examplePartOne() {
    val input1 = PuzzleInput.ofSingleLine("3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0")
    Y2019D07(input1).partOne().toInt() shouldBe 43210

    val input2 = PuzzleInput
      .ofSingleLine("3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0")
    Y2019D07(input2).partOne().toInt() shouldBe 54321

    val input3 = PuzzleInput.ofSingleLine(
      "3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0"
    )
    Y2019D07(input3).partOne().toInt() shouldBe 65210
  }

  @Test
  override suspend fun examplePartTwo() {
    val input1 = PuzzleInput.ofSingleLine(
      "3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5"
    )
    Y2019D07(input1).partTwo().toInt() shouldBe 139629729

    val input2 = PuzzleInput.ofSingleLine(
      "3,52,1001,52,-5,52,3,53,1,52,56,54,1007,54,5,55,1005,55,26,1001,54,-5," +
        "54,1105,1,12,1,53,54,53,1008,54,0,55,1001,55,1,55,2,53,55,53,4,53,1001,56,-1,56,1005,56,6,99,0,0,0,0,10"
    )
    Y2019D07(input2).partTwo().toInt() shouldBe 18216
  }

  @Test
  override suspend fun solutionPartOne() {
    Y2019D07().partOne().toInt() shouldBe 47064
  }

  @Test
  override suspend fun solutionPartTwo() {
    Y2019D07().partTwo().toInt() shouldBe 4248984
  }
}
