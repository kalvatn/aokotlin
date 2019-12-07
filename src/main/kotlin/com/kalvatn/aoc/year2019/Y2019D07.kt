package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2019
import com.kalvatn.aoc.core.runner.PuzzleRunner
import com.kalvatn.aoc.extensions.extractIntegers
import com.marcinmoskala.math.permutations
import kotlinx.coroutines.runBlocking


class Y2019D07(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2019(Day.D07, input) {

    val lines = this.input.singleLine().extractIntegers()

    override suspend fun partOne(): String {
        val pc = IntcodeComputer(lines)
        val permutations = listOf(0, 1, 2, 3, 4).permutations()

        var max = Integer.MIN_VALUE

        permutations.map {ps ->
            val outputA = pc.runPhase(ps[0], 0)
            val outputB = pc.runPhase(ps[1], outputA)
            val outputC = pc.runPhase(ps[2], outputB)
            val outputD = pc.runPhase(ps[3], outputC)
            val outputE = pc.runPhase(ps[4], outputD)
            if (outputE > max) {
                max = outputE
            }
        }

        return max.toString()
    }

    override suspend fun partTwo(): String {
        val permutations = listOf(5,6,7,8,9).permutations()
        val pc = IntcodeComputer(listOf(3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5))
        val A = pc.runPhase(9, 0)
        println(A)
        return ""
    }

}

fun main() = runBlocking {
//    val input = PuzzleInput.ofSingleLine("3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0")
    val input = PuzzleInput.ofSingleLine("3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0")
//    PuzzleRunner(listOf(Y2019D07(input))).run()
    PuzzleRunner(listOf(Y2019D07())).run()
}

