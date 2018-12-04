package com.kalvatn.aoc.extensions


fun String.extractIntegers(): List<Int> {
    return Regex("[^?\\-\\d+]")
        .replace(this, " ")
        .split(" ")
        .filter { !it.isBlank() }
        .map { it.toInt() }
}
