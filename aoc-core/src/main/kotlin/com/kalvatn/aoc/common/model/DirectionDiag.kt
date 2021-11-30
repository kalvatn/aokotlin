package com.kalvatn.aoc.common.model

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
