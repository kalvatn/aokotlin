package com.kalvatn.aoc.extensions


val VOWELS: List<Char> = listOf('a', 'e', 'i', 'o', 'u')
val CONSECUTIVE_LETTERS = ('a'..'z').map { "$it$it" }

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
