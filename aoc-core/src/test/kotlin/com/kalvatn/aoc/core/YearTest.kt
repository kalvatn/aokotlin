package com.kalvatn.aoc.core

import com.kalvatn.aoc.core.model.Year
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class YearTest : StringSpec({

  fun intValue() {
    Year.Y2015.intValue() shouldBe 2015
  }

  fun intString() {
    Year.Y2015.intString() shouldBe "2015"
  }
})
