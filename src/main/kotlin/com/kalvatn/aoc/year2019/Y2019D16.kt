package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2019
import com.kalvatn.aoc.core.runner.PuzzleRunner
import com.kalvatn.aoc.extensions.cycle
import kotlinx.coroutines.runBlocking
import kotlin.math.abs


class Y2019D16(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2019(Day.D16, input) {

    private val numbers by lazy { this.input.singleLineSplit("").map { it.toInt() } }

    private val basePattern = listOf(0, 1, 0, -1)

    fun extendPattern(index:Int):Sequence<Int> {
        if (index == 0) {
            return basePattern.cycle().drop(1)
        }
        val new = mutableListOf<Int>()
        basePattern.forEach { v ->
            repeat(index+1) {
                new.add(v)
            }
        }
        return new.cycle().drop(1)
    }

    fun phase(signal:List<Int>):List<Int> {
        return (signal.indices).map { i ->
            abs(extendPattern(i).take(signal.size).mapIndexed { pi, pv ->
                signal[pi] * pv
            }.sum()) % 10
        }
    }

    override suspend fun partOne(): String {
        // 7:51 -> 8:53
        var output = numbers
        repeat(100) {
            output = phase(output)
        }
        return output.joinToString("").take(8)
    }

    override suspend fun partTwo(): String {
        return ""
    }
    
    companion object {
        
    }

}

fun main() = runBlocking {
//    PuzzleRunner(listOf(Y2019D16(PuzzleInput.ofSingleLine("12345678")))).run()
    PuzzleRunner(listOf(Y2019D16())).run()
}

