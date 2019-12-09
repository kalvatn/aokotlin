package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2019
import com.kalvatn.aoc.core.model.Year
import com.kalvatn.aoc.core.runner.PuzzleRunner
import com.kalvatn.aoc.extensions.extractIntegers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking


class Y2019D09(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2019(Day.D09, input) {

    val program by lazy { this.input.singleLineSplit(",").map { it.toLong() } }

    override suspend fun partOne(): String {
//        LongcodeComputer(listOf(109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99)).process()
//        LongcodeComputer(listOf(104,1125899906842624,99)).process()
//        LongcodeComputer(listOf(1102,34915192,34915192,7,4,7,99,0)).process()
//        LongcodeComputer(listOf(109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99)).process()

        val pc = LongcodeComputer(program)

        pc.input(1)
        pc.process()
        println(pc.output().reversed())

//        LongcodeComputer(PuzzleInput.forDay(Year.Y2019,Day.D05).singleLineSplit(",").map { it.toLong() }).run {
//            this.input(5)
//            this.process()
//            println(this.output())
//        }

        return pc.output().last().toString()
    }

    override suspend fun partTwo(): String {
        return ""
    }

    companion object {

    }

}

fun main() = runBlocking {
    PuzzleRunner(listOf(Y2019D09())).run()
}

