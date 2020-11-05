package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.JunitTags
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags

internal class Y2015D11Test : BaseDayTest() {
  @Test
  override suspend fun examplePartOne() {
  }

  @Test
  override suspend fun examplePartTwo() {
  }

  @Test
  override suspend fun solutionPartOne() {
    Y2015D11().partOne() shouldBe "hepxxyzz"
  }

  @Tags(Tag(JunitTags.SLOW))
  @Test
  override suspend fun solutionPartTwo() {
    Y2015D11().partTwo() shouldBe "heqaabcc"
  }
}
