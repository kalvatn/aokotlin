package com.kalvatn.aoc.extensions

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class CollectionKtTest : StringSpec({
    "cycle n" {
        listOf(1, 2, 3).cycle(3).toList() shouldBe listOf(1, 2, 3, 1, 2, 3, 1, 2, 3)
        listOf("ab", "lol", "foo").cycle(1).toList() shouldBe listOf("ab", "lol", "foo")
        listOf("ab", "lol", "foo").cycle(2).toList() shouldBe listOf("ab", "lol", "foo", "ab", "lol", "foo")
    }

    "cycle" {
        listOf(1, 2, 3).cycle().take(10).toList() shouldBe listOf(1, 2, 3, 1, 2, 3, 1, 2, 3, 1)
    }

    "reductions" {
        val r = listOf(1, 2, 3).reductions(0) { acc, i ->
            acc + i
        }.toList()
        r shouldBe listOf(0, 1, 3, 6)
    }
})