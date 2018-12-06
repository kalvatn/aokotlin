package com.kalvatn.aoc.extensions

import java.security.MessageDigest


val VOWELS: List<Char> = listOf('a', 'e', 'i', 'o', 'u')
val CONSECUTIVE_LETTERS = ('a'..'z').map { "$it$it" }
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
            .filter { !it.isBlank() }
            .map { it.toInt() }
}

fun String.hasNVowels(number: Int = 1): Boolean {
    return this.filter { it -> VOWELS.contains(it) }.count() >= number
}

fun String.hasConsecutiveLetters(number: Int = 1): Boolean {
    return CONSECUTIVE_LETTERS.filter { this.contains(it) }.count() >= number
}

fun String.doesNotContain(vararg bad: String): Boolean {
    return !bad.toList().filter { this.contains(it) }.any()
}

fun String.hasXYX(): Boolean {
    val windowed = this.windowed(3, 1, false)
    val filter = windowed.filter { it[0] == it[2] }
    return filter.any()
}

fun String.hasNonOverlappingPair(): Boolean {
    return windowed(2, 1, false)
            .mapIndexed { index, it -> Pair(it, index) }
            .groupBy({ it.first }, { it.second })
            .filter {
                it.value.size > 1 && it.value[it.value.size - 1] - it.value[0] > 1
            }.any()


}
