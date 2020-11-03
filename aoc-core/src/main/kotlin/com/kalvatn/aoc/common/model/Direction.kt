package com.kalvatn.aoc.common.model

import com.kalvatn.aoc.exceptions.Impossiburu

enum class DirectionDiag {
  NW, NE, SW, SE;

  fun toPointDiff(): Point {
    return when (this) {
      NW -> Direction.NORTH.toPointDiff() + Direction.WEST.toPointDiff()
      NE -> Direction.NORTH.toPointDiff() + Direction.EAST.toPointDiff()
      SW -> Direction.SOUTH.toPointDiff() + Direction.WEST.toPointDiff()
      SE -> Direction.SOUTH.toPointDiff() + Direction.EAST.toPointDiff()
    }
  }
}

enum class Direction {
  NORTH, EAST, SOUTH, WEST;

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

  fun toChar(): Char {
    return when (this) {
      NORTH -> '^'
      EAST -> '>'
      SOUTH -> 'v'
      WEST -> '<'
    }
  }

  fun toLong(): Long {
    return when (this) {
      NORTH -> 1
      SOUTH -> 2
      WEST -> 3
      EAST -> 4
    }
  }

  companion object {
    fun fromChar(char: Char): Direction {
      return when (char) {
        '^', 'U' -> NORTH
        '>', 'R' -> EAST
        'v', 'D' -> SOUTH
        '<', 'L' -> WEST
        else -> {
          println(char)
          throw Impossiburu()
        }
      }
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
