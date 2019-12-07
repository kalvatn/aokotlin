package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2019
import com.kalvatn.aoc.core.runner.PuzzleRunner
import com.kalvatn.aoc.extensions.extractIntegers
import com.marcinmoskala.math.permutations
import kotlinx.coroutines.*


class Y2019D07(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2019(Day.D07, input) {

    val lines = this.input.singleLine().extractIntegers()

    override suspend fun partOne(): String {
        val pc = IntcodeComputer(lines)
        val permutations = listOf(0, 1, 2, 3, 4).permutations()

        var max = Integer.MIN_VALUE

        permutations.map { ps ->
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
        val permutations = listOf(5, 6, 7, 8, 9).permutations()
//        val program = listOf(3, 26, 1001, 26, -4, 26, 3, 27, 1002, 27, 2, 27, 1, 27, 26, 27, 4, 27, 1001, 28, -1, 28, 1005, 28, 6, 99, 0, 0, 5)
//        val program = listOf(3,52,1001,52,-5,52,3,53,1,52,56,54,1007,54,5,55,1005,55,26,1001,54,
//                -5,54,1105,1,12,1,53,54,53,1008,54,0,55,1001,55,1,55,2,53,55,53,4,
//                53,1001,56,-1,56,1005,56,6,99,0,0,0,0,10)
        val program = this.input.singleLine().extractIntegers()

        permutations.map { ps ->
            val A = IntcodeComputerSignals("A", ps[0], program)
            val B = IntcodeComputerSignals("B", ps[1], program)
            val C = IntcodeComputerSignals("C", ps[2], program)
            val D = IntcodeComputerSignals("D", ps[3], program)
            val E = IntcodeComputerSignals("E", ps[4], program)
            A.sendTo = B
            A.receiveFrom = E

            B.sendTo = C
            B.receiveFrom = A

            C.sendTo = D
            C.receiveFrom = B

            D.sendTo = E
            D.receiveFrom = C

            E.sendTo = A
            E.receiveFrom = D

            val tasks = listOf(A, B, C, D, E).map {
                GlobalScope.async { it.process() }
            }
            A.receive(0)

            tasks.awaitAll().first { it.name == "E" }.diagnosticCode
        }.max().let {
            println(it)
        }

        return ""

    }

}

fun main() = runBlocking {
    //    val input = PuzzleInput.ofSingleLine("3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0")
    val input = PuzzleInput.ofSingleLine("3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0")
//    PuzzleRunner(listOf(Y2019D07(input))).run()
    PuzzleRunner(listOf(Y2019D07())).run()
}

