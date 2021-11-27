package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.BaseDayTest
import io.kotest.matchers.shouldBe

internal class Y2015D08Test : BaseDayTest() {

  @Test
  override suspend fun examplePartOne() {
    val day = Y2015D08()
    day.p1(listOf("\"\"")) shouldBe 2
    day.p1(listOf("\"abc\"")) shouldBe 2
    day.p1(listOf("\"aaa\"aaa\"")) shouldBe 3
    day.p1(listOf("\"\\x27\"")) shouldBe 5
    day.p1(listOf("\"\"", "\"abc\"", "\"aaa\"aaa\"", "\"\\x27\"")) shouldBe 12
  }

  @Test
  override suspend fun examplePartTwo() {
    val day = Y2015D08()
    day.p2(listOf("\"\"")) shouldBe 4
    day.p2(listOf("\"abc\"")) shouldBe 4
    day.p2(listOf("\"aaa\\\"aaa\"")) shouldBe 6
    day.p2(listOf("\"\\x27\"")) shouldBe 5
    day.p2(listOf("\"\"", "\"abc\"", "\"aaa\\\"aaa\"", "\"\\x27\"")) shouldBe 19
  }

  @Test
  override suspend fun solutionPartOne() {
    Y2015D08().partOne().toInt() shouldBe 1350
  }

  @Test
  override suspend fun solutionPartTwo() {
    Y2015D08().partTwo().toInt() shouldBe 2085
  }
}
