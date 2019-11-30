package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2018

class Y2018D14(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2018(Day.D14, input) {

    private fun createRecipes(partTwo: Boolean): String {
        val recipes = mutableListOf(3, 7)
        var elf1 = 0
        var elf2 = 1
        val target = this.input.lines.first()
        val targetLength = target.length
        while (true) {
            val score1 = recipes[elf1]
            val score2 = recipes[elf2]
            recipes.addAll("${score1 + score2}".map { "$it".toInt() })
            elf1 = (elf1 + score1 + 1) % recipes.size
            elf2 = (elf2 + score2 + 1) % recipes.size

            if (partTwo) {
                when (target) {
                    recipes.takeLast(targetLength).joinToString("") -> return (recipes.size - targetLength).toString()
                    recipes.takeLast(targetLength + 1).dropLast(1).joinToString("") -> return (recipes.size - (targetLength + 1)).toString()
                }
            } else {
                if ((recipes.size - 10) >= target.toInt()) {
                    return recipes.drop(target.toInt()).take(10).joinToString("")
                }
            }
        }
    }

    override suspend fun partOne(): String {
        return createRecipes(false)
    }

    override suspend fun partTwo(): String {
        return createRecipes(true)
    }
}

fun main(args: Array<String>) {
//    val day = Y2018D14(PuzzleInput.ofSingleLine("580741"))
    val day = Y2018D14()
}