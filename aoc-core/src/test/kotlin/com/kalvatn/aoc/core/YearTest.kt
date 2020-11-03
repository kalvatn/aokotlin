package com.kalvatn.aoc.core

import com.kalvatn.aoc.core.model.Year
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class YearTest : StringSpec({

  fun intValue() {
    Year.Y2015.intValue() shouldBe 2015
  }

  fun intString() {
    Year.Y2015.intString() shouldBe "2015"
  }
})
