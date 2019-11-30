package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.BaseDayTest
import io.kotlintest.shouldBe

class Y2015D05Test : BaseDayTest() {

    override suspend fun examplePartOne() {
        val day = Y2015D05()
        day.nicePartOne("ugknbfddgicrmopn") shouldBe true
        day.nicePartOne("aaa") shouldBe true
        day.nicePartOne("jchzalrnumimnmhp") shouldBe false
        day.nicePartOne("haegwjzuvuyypxyu") shouldBe false
        day.nicePartOne("dvszwmarrgswjxmb") shouldBe false
    }

    override suspend fun examplePartTwo() {
        val day = Y2015D05()
        day.nicePartTwo("qjhvhtzxzqqjkmpb") shouldBe true
        day.nicePartTwo("xxyxx") shouldBe true
        day.nicePartTwo("uurcxstgmygtbstg") shouldBe false
        day.nicePartTwo("ieodomkazucvgmuy") shouldBe false
        day.nicePartTwo("xilodxfuxphuiiii") shouldBe true
    }

    override suspend fun solutionPartOne() {
        val day = Y2015D05()
        day.partOne().toInt() shouldBe 255
    }

    override suspend fun solutionPartTwo() {
        val day = Y2015D05()
        day.partTwo().toInt() shouldBe 55
    }
}