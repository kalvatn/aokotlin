package com.kalvatn.aoc.exceptions

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ExceptionsTest : StringSpec({
  "impossiburu exception message" {
    val e = Impossiburu()
    e.message shouldBe "impossiburu"
  }

  "cookie missing exception message" {
    val e = CookieMissing()
    e.message shouldBe "src/main/resources/session.cookie is missing or empty"
  }
})
