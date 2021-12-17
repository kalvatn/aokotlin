package com.kalvatn.aoc.extensions

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CollectionExtensionsKtTest : StringSpec({

  "cartesian product test" {
    val actual = cartesianProduct((0..1), (0..1), 'a'..'c').toList()
    val expected = setOf(
      listOf(0, 0, 'a'),
      listOf(0, 0, 'b'),
      listOf(0, 0, 'c'),
      listOf(0, 1, 'a'),
      listOf(0, 1, 'b'),
      listOf(0, 1, 'c'),
      listOf(1, 0, 'a'),
      listOf(1, 0, 'b'),
      listOf(1, 0, 'c'),
      listOf(1, 1, 'a'),
      listOf(1, 1, 'b'),
      listOf(1, 1, 'c'),
    ).toList()
//    expected.indices.forEach { index ->
//      println("expected '${expected[index]} actual '${actual[index]}'")
//    }
    actual shouldBe expected
    cartesianProduct(setOf(1, 2), setOf("a")) shouldBe setOf(
      listOf(1, "a"),
      listOf(2, "a"),
    )
    cartesianProduct(1..3, 'x'..'z') shouldBe setOf(
      listOf(1, 'x'),
      listOf(1, 'y'),
      listOf(1, 'z'),
      listOf(2, 'x'),
      listOf(2, 'y'),
      listOf(2, 'z'),
      listOf(3, 'x'),
      listOf(3, 'y'),
      listOf(3, 'z'),
    )
  }
})
