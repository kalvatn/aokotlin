package com.kalvatn.aoc.common.math

/**
 * greatest common denominator
 */
fun gcd(a: Long, b: Long): Long {
  if (b == 0L) return a
  return gcd(b, a % b)
}

tailrec fun gcd(vararg numbers: Long): Long {
  if (numbers.size == 1) {
    return 1L
  }
  if (numbers.size == 2) {
    return gcd(numbers[0], numbers[1])
  }
  val f = gcd(numbers[0], numbers[1])
  return gcd(*(numbers.drop(2) + f).toLongArray())
}

/**
 * least common multiple
 */
fun lcm(a: Long, b: Long): Long {
  return a / gcd(a, b) * b
}

tailrec fun lcm(vararg numbers: Long): Long {
  if (numbers.size == 1) {
    return 1L
  }
  if (numbers.size == 2) {
    return lcm(numbers[0], numbers[1])
  }
  val f = lcm(numbers[0], numbers[1])
  return lcm(*(numbers.drop(2) + f).toLongArray())
}

fun List<Long>.gcd(): Long = lcm(*this.toLongArray())
fun List<Int>.gcd(): Int = lcm(*this.map { it.toLong() }.toLongArray()).toInt()

fun List<Long>.lcm(): Long = lcm(*this.toLongArray())
fun List<Int>.lcm(): Int = lcm(*this.map { it.toLong() }.toLongArray()).toInt()
