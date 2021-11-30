package com.kalvatn.aoc.common.model

enum class Turn(val intValue: Int, private val charValues: List<Char>) {
  LEFT(0, listOf('L', '<')),
  RIGHT(1, listOf('R', '>')),
  FORWARD(2, listOf('F', '^'));

  fun toChar() = charValues.first()
  fun toInt() = intValue

  companion object {
    fun fromInt(value: Int) = values().first { it.intValue == value }

    fun fromChar(char: Char) = values().first { it.charValues.contains(char) }
  }
}
