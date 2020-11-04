package com.kalvatn.aoc.core
import org.junit.jupiter.api.Test

import com.kalvatn.aoc.core.model.Day
import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.StringSpec

class DayTest : StringSpec({

  "integer value of day should be as expected" {
    Day.D01.intValue() shouldBe 1
  }

  "string value of day should be as expected" {
    Day.D01.intString() shouldBe "01"
  }
})
