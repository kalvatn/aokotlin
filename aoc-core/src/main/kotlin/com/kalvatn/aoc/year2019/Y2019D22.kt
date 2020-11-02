package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2019
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking
import kotlin.math.abs


class Y2019D22(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2019(Day.D22, input) {

    val lines by lazy { this.input.lines }


    data class Deck(val size: Int) {
        private var deck = (0 until size).toList()

        fun toList() = deck.toList()

        fun cut(n: Int): List<Int> {
            if (n == 0) {
                return deck
            }
            deck = if (n > 0) {
                deck.drop(n) + deck.take(n)
            } else {
                deck.takeLast(abs(n)) + deck.dropLast(abs(n))
            }
            return deck
        }

        fun dealIntoNewStack(): List<Int> {
            deck = deck.reversed()
            return deck
        }

        fun dealWithIncrement(n: Int): List<Int> {
            val new = arrayOfNulls<Int>(deck.size)
            var index = 0
            deck.forEach {
                if (new[index] != null) {
                    error("impossiburu")
                }
                new[index] = it
                index = (index + n) % deck.size
            }
            deck = new.filterNotNull().toList()
            return deck
        }

        fun applyTechnique(s: String): List<Int> {
            return when {
                s == "deal into new stack" -> dealIntoNewStack()
                s.startsWith("cut") -> cut(s.split(" ").last().toInt())
                s.startsWith("deal with increment") -> dealWithIncrement(s.split(" ").last().toInt())
                else -> error("impossiburu")
            }
        }
    }

    override suspend fun partOne(): String {
        val deck = Deck(10007)
        lines.forEach {
            deck.applyTechnique(it)
        }
        return deck.toList().indexOf(2019).toString()
    }

    override suspend fun partTwo(): String {
        val deckSize = 119315717514047.toBigInteger()
        val shuffleCount = 101741582076661.toBigInteger()
        val zero = 0.toBigInteger()
        val one = 1.toBigInteger()
        val two = 2.toBigInteger()
        val positionToFind = 2020.toBigInteger()
        val table = arrayOf(one, zero)
        input.lines.reversed().forEach { s ->
            when {
                s == "deal into new stack" -> {
                    table[0] = table[0].negate()
                    table[1] = (table[1].inc()).negate()
                }
                s.startsWith("cut") -> table[1] += s.split(" ").last().toBigInteger()
                s.startsWith("deal with increment") -> {
                    s.split(" ").last().toBigInteger().modPow(deckSize - two, deckSize).also {
                        table[0] *= it
                        table[1] *= it
                    }
                }
                else -> error("impossiburu")
            }
            table[0] %= deckSize
            table[1] %= deckSize
        }
        val power = table[0].modPow(shuffleCount, deckSize)
        return (power * positionToFind + table[1] * (power + deckSize.dec()) * table[0].dec().modPow(deckSize - two, deckSize))
                .mod(deckSize).toString()
    }

}

fun main() = runBlocking {
    PuzzleRunner(listOf(Y2019D22())).run()
}

