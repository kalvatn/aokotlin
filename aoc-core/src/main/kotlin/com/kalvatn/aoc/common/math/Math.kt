package com.kalvatn.aoc.common.math

fun greatestCommonDenominator(a: Long, b: Long): Long {
  if (b == 0L) return a
  return greatestCommonDenominator(b, a % b)
}

fun leastCommonMultiple(a: Long, b: Long): Long {
  return a / greatestCommonDenominator(a, b) * b
}

private tailrec fun leastCommonMultiple(numbers: List<Long>): Long {
  if (numbers.size == 1) {
    return 1L
  }
  if (numbers.size == 2) {
    return leastCommonMultiple(numbers[0], numbers[1])
  }
  val f = leastCommonMultiple(numbers[0], numbers[1])
  return leastCommonMultiple((numbers.drop(2) + f))
}

fun List<Long>.lcm(): Long = leastCommonMultiple(this)
