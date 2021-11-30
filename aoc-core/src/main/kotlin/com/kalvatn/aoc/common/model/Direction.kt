package com.kalvatn.aoc.common.model

enum class Direction(val longValue: Long, val charValues: List<Char>) {
  NORTH(1, listOf('^', 'U', 'N')),
  SOUTH(2, listOf('v', 'D', 'S')),
  WEST(3, listOf('<', 'L', 'W')),
  EAST(4, listOf('>', 'R', 'E'))
  ;

  fun toPointDiff(): Point {
    return when (this) {
      NORTH -> Point(0, -1)
      EAST -> Point(1, 0)
      SOUTH -> Point(0, 1)
      WEST -> Point(-1, 0)
    }
  }

  fun turn(turn: Turn): Direction {
    return when (turn) {
      Turn.LEFT -> when (this) {
        NORTH -> WEST
        EAST -> NORTH
        SOUTH -> EAST
        WEST -> SOUTH
      }
      Turn.RIGHT -> when (this) {
        NORTH -> EAST
        EAST -> SOUTH
        SOUTH -> WEST
        WEST -> NORTH
      }
      Turn.FORWARD -> this
    }
  }

  fun toChar(): Char = this.charValues.first()

  fun toLong(): Long = this.longValue

  companion object {
    fun fromChar(char: Char): Direction =
      values().first {
        it.charValues.contains(char)
      }

    @Suppress("unused")
    fun fromPointDiff(p: Point): Direction {
      return when (p) {
        Point(0, -1) -> NORTH
        Point(1, 0) -> EAST
        Point(0, 1) -> SOUTH
        Point(-1, 0) -> WEST
        else -> error("impossiburu")
      }
    }
  }
}
