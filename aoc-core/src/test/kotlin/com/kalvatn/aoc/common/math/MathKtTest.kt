package com.kalvatn.aoc.common.math

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MathKtTest : StringSpec({

  "triangular numbers" {
    0.triangularSum() shouldBe 0
    1.triangularSum() shouldBe 1
    2.triangularSum() shouldBe 3
    3.triangularSum() shouldBe 6
    36.triangularSum() shouldBe 666
  }
})
