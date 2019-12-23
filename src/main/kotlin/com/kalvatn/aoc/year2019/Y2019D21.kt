package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2019
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking


class Y2019D21(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2019(Day.D21, input) {

    private val program by lazy { this.input.singleLineLongs() }

    override suspend fun partOne(): String {
        val pc = IntcodeComputer(program)
        val cmds = listOf(
                /**
                 *  jump = if (hole1 || hole2 || hole3) and ground4
                 *  jump = if (!A OR !B OR !C) AND D
                 *  TODO FIXME XXX generate all permutations to not have to think about this.. feasible?
                 */
                "NOT A J", // JUMP = HOLE1
                "NOT B T", // TEMP = HOLE2
                "OR T J",  // JUMP = (HOLE1 OR HOLE2)
                "NOT C T", // TEMP = HOLE3
                "OR T J",  // JUMP = (HOLE1 OR HOLE2 OR HOLE3)
                "AND D J", // JUMP = GROUND3 AND (HOLE1 OR HOLE2 OR HOLE3)
                "WALK"
        )
        pc.run()
        cmds.forEach { cmd ->
            cmd.map { it.toLong() }.forEach {
                pc.input(it)
            }
            pc.input('\n'.toLong())
        }
        pc.run()
        val message = pc.output().map { it.toChar() }.joinToString("")
        if (message.contains("Didn't make it across:")) {
            println(message)
        } else {
            return pc.outputLast().toString()
        }
        error(message)
    }

    override suspend fun partTwo(): String {
        return ""
    }

}

fun main() = runBlocking {
    PuzzleRunner(listOf(Y2019D21())).run()
}

