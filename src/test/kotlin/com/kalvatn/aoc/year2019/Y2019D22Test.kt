package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.year2019.Y2019D22.Deck
import io.kotlintest.shouldBe

internal class Y2019D22Test : BaseDayTest() {

    @Test
    fun testDeckCut() {
        var deck = Deck(10)
        deck.cut(3) shouldBe listOf(3, 4, 5, 6, 7, 8, 9, 0, 1, 2)
        deck = Deck(10)
        deck.cut(-4) shouldBe listOf(6, 7, 8, 9, 0, 1, 2, 3, 4, 5)
    }

    @Test
    fun testDeckDealWithIncrement() {
        var deck = Deck(10)
        deck.dealWithIncrement(3) shouldBe listOf(0, 7, 4, 1, 8, 5, 2, 9, 6, 3)
    }

    @Test
    fun testDealIntoNewStack() {
        var deck = Deck(10)
        deck.dealIntoNewStack() shouldBe listOf(9, 8, 7, 6, 5, 4, 3, 2, 1, 0)
    }

    @Test
    fun testApplyTechnique() {
        var deck = Deck(10)
        println(deck.applyTechnique("deal with increment 7"))
        println(deck.applyTechnique("deal into new stack"))
        deck.applyTechnique("deal into new stack") shouldBe listOf(0, 3, 6, 9, 2, 5, 8, 1, 4, 7)

        deck = Deck(10)
        listOf(
                "cut 6",
                "deal with increment 7",
                "deal into new stack"
        ).map { deck.applyTechnique(it) }.last() shouldBe listOf(3, 0, 7, 4, 1, 8, 5, 2, 9, 6)
        deck = Deck(10)
        listOf(
                "deal with increment 7",
                "deal with increment 9",
                "cut -2"
        ).map { deck.applyTechnique(it) }.last() shouldBe listOf(6, 3, 0, 7, 4, 1, 8, 5, 2, 9)

        deck = Deck(10)
        listOf(
                "deal into new stack",
                "cut -2",
                "deal with increment 7",
                "cut 8",
                "cut -4",
                "deal with increment 7",
                "cut 3",
                "deal with increment 9",
                "deal with increment 3",
                "cut -1"
        ).map { deck.applyTechnique(it) }.last() shouldBe listOf(9, 2, 5, 8, 1, 4, 7, 0, 3, 6)

    }


    @Test
    override suspend fun examplePartOne() {
    }

    @Test
    override suspend fun examplePartTwo() {
    }

    @Test
    override suspend fun solutionPartOne() {
        Y2019D22().partOne().toInt() shouldBe 8775
    }

    @Test
    override suspend fun solutionPartTwo() {
        Y2019D22().partTwo().toBigInteger() shouldBe 47141544607176.toBigInteger()
    }
}
