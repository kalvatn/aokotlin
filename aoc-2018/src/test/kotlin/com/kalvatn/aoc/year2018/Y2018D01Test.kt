package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Disabled

class Y2018D01Test : BaseDayTest() {

  @Disabled
  suspend fun testHard() {
    // https://www.reddit.com/r/adventofcode/comments/a20646/2018_day_1_solutions/eaukxu5/
    val testHard = Y2018D01(PuzzleInput.ofCommaDelimited("+10000000, -9999999"))
    testHard.partOne().toInt() shouldBe 1
    testHard.partTwo().toInt() shouldBe 10000000
  }

  @Test
  override suspend fun examplePartOne() {
    val test1 = Y2018D01(PuzzleInput.ofCommaDelimited("+1, -1"))
    val test2 = Y2018D01(PuzzleInput.ofCommaDelimited("+3, +3, +4, -2, -4"))
    val test3 = Y2018D01(PuzzleInput.ofCommaDelimited("-6, +3, +8, +5, -6"))
    val test4 = Y2018D01(PuzzleInput.ofCommaDelimited("+7, +7, -2, -7, -4"))
    test1.partOne().toInt() shouldBe 0
    test2.partOne().toInt() shouldBe 4
    test3.partOne().toInt() shouldBe 4
    test4.partOne().toInt() shouldBe 1
  }

  @Test
  override suspend fun examplePartTwo() {
    val test1 = Y2018D01(PuzzleInput.ofCommaDelimited("+1, -1"))
    val test2 = Y2018D01(PuzzleInput.ofCommaDelimited("+3, +3, +4, -2, -4"))
    val test3 = Y2018D01(PuzzleInput.ofCommaDelimited("-6, +3, +8, +5, -6"))
    val test4 = Y2018D01(PuzzleInput.ofCommaDelimited("+7, +7, -2, -7, -4"))
    test1.partTwo().toInt() shouldBe 0
    test2.partTwo().toInt() shouldBe 10
    test3.partTwo().toInt() shouldBe 5
    test4.partTwo().toInt() shouldBe 14
  }

  @Test
  override suspend fun solutionPartOne() {
    val day01 = Y2018D01()
    day01.partOne().toInt() shouldBe 518
  }

  @Test
  override suspend fun solutionPartTwo() {
    val day01 = Y2018D01()
    day01.partTwo().toInt() shouldBe 72889
  }
}
