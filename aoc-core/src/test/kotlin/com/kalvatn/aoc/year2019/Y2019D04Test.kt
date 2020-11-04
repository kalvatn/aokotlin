package com.kalvatn.aoc.year2019
import org.junit.jupiter.api.Test

import com.kalvatn.aoc.BaseDayTest
import io.kotest.matchers.shouldBe

internal class Y2019D04Test : BaseDayTest() {

  val day = Y2019D04()

  @Test
  override suspend fun examplePartOne() {
    day.valid("111111") shouldBe true
    day.valid("123789") shouldBe false
    day.valid("112233") shouldBe true
    day.valid("123444") shouldBe true
    day.valid("111122") shouldBe true
    day.valid("155577") shouldBe true
    day.valid("223450") shouldBe false
  }

  @Test
  override suspend fun examplePartTwo() {
    day.valid2("111111") shouldBe false
    day.valid2("123789") shouldBe false
    day.valid2("112233") shouldBe true
    day.valid2("123444") shouldBe false
    day.valid2("111122") shouldBe true
    day.valid2("155577") shouldBe true
  }

  @Test
  override suspend fun solutionPartOne() {
    day.partOne().toInt() shouldBe 1767
  }

  @Test
  override suspend fun solutionPartTwo() {
    day.partTwo().toInt() shouldBe 1192
  }
}
