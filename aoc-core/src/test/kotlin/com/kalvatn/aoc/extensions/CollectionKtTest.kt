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

  "list intersection" {
    val list = listOf(
      mapOf(1 to 10, 2 to 3, 3 to 99, 5 to 999),
      mapOf(1 to 23, 4 to 100, 3 to 1, 5 to 999)
    )
    val expected = mapOf(1 to listOf(10, 23), 3 to listOf(99, 1), 5 to listOf(999, 999))
    list.groupMapsBySharedKeys() shouldBe expected
//        list.groupMapsBySharedKeys2() shouldBe expected
  }
})
