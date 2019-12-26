package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2019
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking


fun List<Long>.toAscii() = this.map { it.toChar() }.joinToString("")

fun IntcodeComputer.inputCmd(s: String) {
    println("'$s'")
    s.map { it.toLong() }.also {
        println(it)
    }.forEach {
        input(it)
    }
    input('\n'.toLong())
    run()
}

class Y2019D25(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2019(Day.D25, input) {

    private val program by lazy { this.input.singleLineLongs() }

    override suspend fun partOne(): String {
        val pc = IntcodeComputer(program)
        pc.run()
        loop@ while (true) {
            val message = pc.output().toAscii()
            println(message)
            when (val cmd = readLine()!!) {
                "reset" -> pc.reset()
                "exit" -> break@loop
                "help" -> println(message)
                else -> pc.inputCmd(cmd)
            }
        }
        /**
         * semiconductor
         * antenna
         * hypercube
         * mouse
         */
        return 20483.toString()
    }

    override suspend fun partTwo(): String {
        return ""
    }

    companion object {

    }

}

fun main() = runBlocking {
    PuzzleRunner(listOf(Y2019D25())).run()
}

