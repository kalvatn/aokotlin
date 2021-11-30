package com.kalvatn.aoc.extensions

import java.security.MessageDigest

val VOWELS: List<Char> = listOf('a', 'e', 'i', 'o', 'u')
val CONSECUTIVE_LETTERS = ('a'..'z').map { "$it$it" }
val CONSECUTIVE_DIGITS = (0..9).map { "$it$it" }
private val MD5 = MessageDigest.getInstance("MD5")
private const val HEX_CHARS = "0123456789ABCDEF"

fun String.md5(): String {
  val bytes = MD5.digest(toByteArray())
  val result = StringBuilder(bytes.size * 2)

  bytes.forEach {
    val i = it.toInt()
    result.append(HEX_CHARS[i shr 4 and 0x0f])
    result.append(HEX_CHARS[i and 0x0f])
  }
  return result.toString()
}

fun String.extractIntegers(): List<Int> {
  return Regex("[^?\\-\\d+]")
    .replace(this, " ")
    .split(" ")
    .filter { it.isNotBlank() }
    .map { it.toInt() }
}

fun String.hasNVowels(number: Int = 1) =
  this.count { VOWELS.contains(it) } >= number

fun String.hasConsecutiveLetters(number: Int = 1) =
  CONSECUTIVE_LETTERS.asSequence().filter { this.contains(it) }.count() >= number

fun String.hasConsecutiveDigits(number: Int = 1) =
  CONSECUTIVE_DIGITS.asSequence().filter { this.contains(it) }.count() >= number

fun String.doesNotContain(bad: List<String>) = bad.none { this.contains(it) }

fun String.hasXYX(): Boolean {
  val windowed = this.windowed(3, 1, false)
  val filter = windowed.filter { it[0] == it[2] }
  return filter.any()
}

fun String.hasIncreasingLetterStraight(size: Int) =
  this.windowed(size, 1, false).asSequence().filter {
    val map = it.map { c -> c.code }
    map.filterIndexed { index, i ->
      if (index == size - 1) {
        true
      } else {
        i == map[index + 1] - 1
      }
    }.count() == size
  }.any()

fun String.hasNonOverlappingPair(): Boolean {
  return windowed(2, 1, false)
    .mapIndexed { index, it -> Pair(it, index) }
    .groupBy({ it.first }, { it.second })
    .filter {
      it.value.size > 1 && it.value[it.value.size - 1] - it.value[0] > 1
    }.any()
}
