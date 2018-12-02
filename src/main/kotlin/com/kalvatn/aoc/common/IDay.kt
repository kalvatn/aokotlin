package com.kalvatn.aoc.common

interface IDay {
    val year: Int
    val day: Int

    fun input(): PuzzleInput

    fun partOne(): String
    fun partTwo(): String

    fun run(benchmark: Boolean = false)
}