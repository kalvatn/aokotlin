package com.kalvatn.aoc.year2015
import org.junit.jupiter.api.Test

import com.kalvatn.aoc.BaseDayTest
import io.kotest.matchers.shouldBe

class Y2015D05Test : BaseDayTest() {

  @Test
  override suspend fun examplePartOne() {
    val day = Y2015D05()
    day.nicePartOne("ugknbfddgicrmopn") shouldBe true
    day.nicePartOne("aaa") shouldBe true
    day.nicePartOne("jchzalrnumimnmhp") shouldBe false
    day.nicePartOne("haegwjzuvuyypxyu") shouldBe false
    day.nicePartOne("dvszwmarrgswjxmb") shouldBe false
  }

  @Test
  override suspend fun examplePartTwo() {
    val day = Y2015D05()
    day.nicePartTwo("qjhvhtzxzqqjkmpb") shouldBe true
    day.nicePartTwo("xxyxx") shouldBe true
    day.nicePartTwo("uurcxstgmygtbstg") shouldBe false
    day.nicePartTwo("ieodomkazucvgmuy") shouldBe false
    day.nicePartTwo("xilodxfuxphuiiii") shouldBe true
  }

  @Test
  override suspend fun solutionPartOne() {
    Y2015D05().partOne().toInt() shouldBe 255
  }

  @Test
  override suspend fun solutionPartTwo() {
    Y2015D05().partTwo().toInt() shouldBe 55
  }
}
