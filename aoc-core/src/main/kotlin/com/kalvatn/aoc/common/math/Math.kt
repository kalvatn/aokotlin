package com.kalvatn.aoc.common.math

/**
 * greatest common denominator
 */
fun gcd(a: Long, b: Long): Long {
  if (b == 0L) return a
  return gcd(b, a % b)
}

/**
 * least common multiple
 */
fun leastCommonMultiple(a: Long, b: Long): Long {
  return a / gcd(a, b) * b
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

@Suppress("unused")
fun List<Long>.gcd(): Long = leastCommonMultiple(this)

@Suppress("unused")
fun List<Int>.gcd(): Int = leastCommonMultiple(this.map { it.toLong() }).toInt()

fun List<Long>.lcm(): Long = leastCommonMultiple(this)

@Suppress("unused")
fun List<Int>.leastCommonMultiple(): Int = leastCommonMultiple(this.map { it.toLong() }).toInt()
