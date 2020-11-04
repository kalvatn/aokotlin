package com.kalvatn.aoc.utils

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

private const val SIZE = 10

class ArrayKtTest : StringSpec({

  "2d integer array" {
    val a = intArray2D(SIZE)
    a.size shouldBe SIZE
    a[0][0] shouldBe 0
  }

  "2d integer array with default value" {
    val a = intArray2D(SIZE, 1)
    a[0][0] shouldBe 1
  }

  "2d boolean array" {
    val a = boolArray2D(SIZE)
    a.size shouldBe SIZE
    a[0][0] shouldBe false
  }

  "2d boolean array with default value" {
    val a = boolArray2D(SIZE, true)
    a[0][0] shouldBe true
  }

  "2d string array" {
    val a = stringArray2D(SIZE)
    a.size shouldBe SIZE
    a[0][0] shouldBe ""
  }

  "2d string array with default value" {
    val a = stringArray2D(SIZE, "lol")
    a[0][0] shouldBe "lol"
  }

  "2d object array with default value" {
    val a = buildArray2D(SIZE, Pair(0, 1))
    a.size shouldBe SIZE
    a[0][0] shouldBe Pair(0, 1)
  }
})
