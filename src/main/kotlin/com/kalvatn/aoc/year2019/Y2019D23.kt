package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2019
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking


class Y2019D23(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2019(Day.D23, input) {

    val program by lazy { this.input.singleLineLongs() }

    override suspend fun partOne(): String {
        val computers = (0L..49).map {
            val pc = IntcodeComputer(program)
            pc.input(it)
            it to pc
        }.toMap()
        while (true) {
            computers.forEach { (k, v) ->
                v.input(-1)
                v.run()
                val message = v.output()
                println(message)
                message.chunked(3).map {
                    val (dest, x, y) = it
                    if (dest == 255L) {
                        return y.toString()
                    }
                    computers[dest]!!.apply {
                        input(x)
                        input(y)
                    }
                }
            }
        }



        return ""
    }

    override suspend fun partTwo(): String {
        return ""
    }

    companion object {

    }

}

fun main() = runBlocking {
    PuzzleRunner(listOf(Y2019D23())).run()
}

