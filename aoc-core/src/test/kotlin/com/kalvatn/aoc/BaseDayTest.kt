package com.kalvatn.aoc

import io.kotlintest.specs.AnnotationSpec

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
