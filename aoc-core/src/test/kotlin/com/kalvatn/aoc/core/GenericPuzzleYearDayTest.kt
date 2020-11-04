package com.kalvatn.aoc.core
import org.junit.jupiter.api.Test

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import com.kalvatn.aoc.year2015.Y2015D04
import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.StringSpec

class GenericPuzzleYearDayTest : StringSpec({

  "test input as single line" {
    val withCustomInput = Y2015D04(PuzzleInput.ofSingleLine("asdf"))
    Y2015D04().input.singleLine() shouldBe "bgvyzdsv"
    withCustomInput.input.singleLine() shouldBe "asdf"
  }

  "test day toString" {
    Y2015D04().toString() shouldBe "2015-04"
  }

  "test day getYear" {
    Y2015D04().year shouldBe Year.Y2015
  }

  "test day getDay" {
    Y2015D04().day shouldBe Day.D04
  }
})
