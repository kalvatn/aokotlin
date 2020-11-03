package com.kalvatn.aoc.core

import com.kalvatn.aoc.core.model.Day
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class DayTest : StringSpec({

  "integer value of day should be as expected" {
    Day.D01.intValue() shouldBe 1
  }

  "string value of day should be as expected" {
    Day.D01.intString() shouldBe "01"
  }
})
