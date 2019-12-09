package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import io.kotlintest.shouldBe

internal class Y2019D09Test : BaseDayTest() {
    @Test
    override suspend fun examplePartOne() {
//        val input = PuzzleInput.ofSingleLine("")
//        val input = PuzzleInput.forDay(Year.Y2019, Day.D09, "test")
        IntcodeComputer(listOf(109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99)).process().output() shouldBe listOf<Long>(109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99)
        IntcodeComputer(listOf(1102,34915192,34915192,7,4,7,99,0)).process().lastOutput().toString().length shouldBe 16
        IntcodeComputer(listOf(104,1125899906842624,99)).process().lastOutput() shouldBe 1125899906842624
//        Y2019D09(input).partOne().toInt() shouldBe 0
    }

    @Test
    override suspend fun examplePartTwo() {
    }

    @Test
    override suspend fun solutionPartOne() {
        Y2019D09().partOne().toLong() shouldBe 2494485073
    }

    @Test
    override suspend fun solutionPartTwo() {
        Y2019D09().partTwo().toLong() shouldBe 44997
    }
}
