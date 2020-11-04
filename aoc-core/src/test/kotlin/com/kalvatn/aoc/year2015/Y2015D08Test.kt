package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.BaseDayTest
import io.kotlintest.shouldBe

internal class Y2015D08Test : BaseDayTest() {

  @Test
  override suspend fun examplePartOne() {
    val day = Y2015D08()
    day.p1("\"\"") shouldBe 2
    day.p1("\"abc\"") shouldBe 2
    day.p1("\"aaa\"aaa\"") shouldBe 3
    day.p1("\"\\x27\"") shouldBe 5
    day.p1("\"\"", "\"abc\"", "\"aaa\"aaa\"", "\"\\x27\"") shouldBe 12
  }

  @Test
  override suspend fun examplePartTwo() {
    val day = Y2015D08()
    day.p2("\"\"") shouldBe 4
    day.p2("\"abc\"") shouldBe 4
    day.p2("\"aaa\\\"aaa\"") shouldBe 6
    day.p2("\"\\x27\"") shouldBe 5
    day.p2("\"\"", "\"abc\"", "\"aaa\\\"aaa\"", "\"\\x27\"") shouldBe 19
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
