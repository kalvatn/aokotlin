package com.kalvatn.aoc.common

import com.kalvatn.aoc.exceptions.CookieMissing
import khttp.responses.Response
import java.io.File

@Suppress("unused")
class PuzzleInput(val lines: List<String>) {

    fun <E> map(fn: (String) -> E): List<E> {
        return lines.map { fn(it) }
    }

    fun singleLine(): String {
        return lines.first()
    }

    fun asIntegers(): List<Int> {
        return map { it.toInt() }
    }

    fun asInteger(): Int {
        return asIntegers().first()
    }


    companion object {
        private fun downloadInput(year: Year, day: Day, inputFile: File) {
            val sessionCookieFile = File("src/main/resources/session.cookie")
            if (!sessionCookieFile.exists()) {
                throw CookieMissing()
            }
            val sessionCookie = sessionCookieFile.readText()
            if (sessionCookie.isBlank()) {
                throw CookieMissing()
            }

            val url = "https://adventofcode.com/${year.intValue()}/day/${day.intValue()}/input"
            println("downloading $url to ${inputFile.absolutePath} ...")
            val get: Response = khttp.get(
                    url = url,
                    cookies = mapOf("session" to sessionCookie)
            )

            val content = get.content
            inputFile.createNewFile()
            inputFile.writeBytes(content)

        }

        @JvmStatic
        fun forDay(puzzle: IPuzzle, suffix: String = ""): PuzzleInput {
            return forDay(puzzle.year, puzzle.day, suffix)
        }

        @JvmStatic
        fun forDay(year: Year, day: Day, suffix: String = ""): PuzzleInput {

            val extra = if (!suffix.isBlank()) "_$suffix" else ""
            val filename = "src/main/resources/inputs/${year.intString()}/${day.intString()}$extra"

            val inputFile = File(filename)
            if (suffix.isBlank() && !inputFile.exists()) {
                println("${inputFile.absolutePath} does not exist")
                downloadInput(year, day, inputFile)
            }
            return PuzzleInput(inputFile.readLines().filter { !it.isBlank() })
        }

        @JvmStatic
        fun ofCommaDelimited(string: String): PuzzleInput {
            return ofSingleLine(string, splitOn = ",")
        }

        @JvmStatic
        fun ofSingleLine(string: String, splitOn: String = ""): PuzzleInput {
            return PuzzleInput(string.split(splitOn).filter { !it.isBlank() }.map { it.trim() })
        }

        @JvmStatic
        fun ofSingleLine(string: String): PuzzleInput {
            return PuzzleInput(listOf(string))
        }
    }
}