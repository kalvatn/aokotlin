package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.JunitTags
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags

internal class Y2015D10Test : BaseDayTest() {

  @Test
  fun testLookAndSay() {
    "1".lookAndSay() shouldBe "11"
    "11".lookAndSay() shouldBe "21"
    "21".lookAndSay() shouldBe "1211"
    "1211".lookAndSay() shouldBe "111221"
    "111221".lookAndSay() shouldBe "312211"
  }

  @Test
  override suspend fun examplePartOne() {
  }

  @Test
  override suspend fun examplePartTwo() {
  }

  @Tags(Tag(JunitTags.SLOW))
  @Test
  override suspend fun solutionPartOne() {
    Y2015D10().partOne().toInt() shouldBe 492982
  }

  @Tags(Tag(JunitTags.SLOW))
  @Test
  override suspend fun solutionPartTwo() {
    Y2015D10().partTwo().toInt() shouldBe 6989950
  }
}
