package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.BaseDayTest
import io.kotest.matchers.shouldBe

class Y2018D16Test : BaseDayTest() {
  @Test
  override suspend fun examplePartOne() {
    //val test1 = Y2018D16(PuzzleInput.ofSingleLine(""))
//    val test1 = Y2018D16(PuzzleInput.forDay(Year.Y2018, Day.D16, "test"))
//    test1.partOne().toInt() shouldBe 0
  }

  @Test
  override suspend fun examplePartTwo() {
    //val test1 = Y2018D16(PuzzleInput.ofSingleLine(""))
//    val test1 = Y2018D16(PuzzleInput.forDay(Year.Y2018, Day.D16, "test"))
//    test1.partOne().toInt() shouldBe 0
  }

  @Test
  override suspend fun solutionPartOne() {
    val day = Y2018D16()
    day.partOne().toInt() shouldBe 640
  }

  @Test
  override suspend fun solutionPartTwo() {
//    TODO("not implemented")
//    val day = Y2018D16()
//    day.partTwo().toInt() shouldBe 0
  }
}
