package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.common.model.Point
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import io.kotlintest.shouldBe


class Y2018D10Test : BaseDayTest() {

    @Test
    fun starFromString() {
        Y2018D10.Star.fromString("position=< 9,  1> velocity=< 0,  2>") shouldBe Y2018D10.Star(Point(9, 1), Point(0, 2))
        Y2018D10.Star.fromString("position=<-2,  7> velocity=< 2, -2>") shouldBe Y2018D10.Star(Point(-2, 7), Point(2, -2))
        val test1 = Y2018D10(PuzzleInput.forDay(Year.Y2018, Day.D10, "test"))
        test1.stars().size shouldBe 31

    }

    @Test
    fun starMove() {
        val star = Y2018D10.Star(Point(0, 0), Point(1, 0))
        star.move()
        star.position.x shouldBe 1
        star.position.y shouldBe 0
        star.move()
        star.position.x shouldBe 2
        star.position.y shouldBe 0
        repeat(10) {
            star.move()
        }
        star.position.x shouldBe 12
        star.position.y shouldBe 0
    }

    override suspend fun examplePartOne() {
    }

    override suspend fun examplePartTwo() {
    }

    override suspend fun solutionPartOne() {
        val day = Y2018D10()
        day.partOne() shouldBe "AHFGRKEE"
    }

    override suspend fun solutionPartTwo() {
        val day = Y2018D10()
        day.partTwo().toInt() shouldBe 10243
    }
}