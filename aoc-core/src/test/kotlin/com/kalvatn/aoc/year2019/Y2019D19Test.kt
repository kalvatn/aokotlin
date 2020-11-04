package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.JunitTags
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags

internal class Y2019D19Test : BaseDayTest() {
  @Test
  override suspend fun examplePartOne() {
  }

  @Test
  override suspend fun examplePartTwo() {
  }

  @Test
  override suspend fun solutionPartOne() {
    Y2019D19().partOne().toInt() shouldBe 152
  }

  @Tags(Tag(JunitTags.SLOW))
  @Test
  override suspend fun solutionPartTwo() {
    Y2019D19().partTwo().toInt() shouldBe 10730411
  }
}
