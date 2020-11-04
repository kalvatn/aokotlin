package com.kalvatn.aoc
import org.junit.jupiter.api.Test

import io.kotest.core.spec.style.AnnotationSpec
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle

@TestInstance(Lifecycle.PER_CLASS)
abstract class BaseDayTest : AnnotationSpec() {

  @Test
  abstract suspend fun examplePartOne()

  @Test
  abstract suspend fun examplePartTwo()

  @Test
  abstract suspend fun solutionPartOne()

  @Test
  abstract suspend fun solutionPartTwo()
}
