package com.kalvatn.aoc.core.model

import com.kalvatn.aoc.common.model.Direction
import com.kalvatn.aoc.common.model.Point
import com.kalvatn.aoc.common.model.Turn
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class DirectionTest : StringSpec({
  "turning from a direction should end up in the correct direction" {
    Direction.NORTH.turn(Turn.LEFT) shouldBe Direction.WEST
    Direction.NORTH.turn(Turn.RIGHT) shouldBe Direction.EAST
    Direction.EAST.turn(Turn.LEFT) shouldBe Direction.NORTH
    Direction.EAST.turn(Turn.RIGHT) shouldBe Direction.SOUTH
    Direction.WEST.turn(Turn.LEFT) shouldBe Direction.SOUTH
    Direction.WEST.turn(Turn.RIGHT) shouldBe Direction.NORTH
    Direction.SOUTH.turn(Turn.LEFT) shouldBe Direction.EAST
    Direction.SOUTH.turn(Turn.RIGHT) shouldBe Direction.WEST
    Direction.SOUTH.turn(Turn.FORWARD) shouldBe Direction.SOUTH
    Direction.NORTH.turn(Turn.FORWARD) shouldBe Direction.NORTH
    Direction.EAST.turn(Turn.FORWARD) shouldBe Direction.EAST
    Direction.WEST.turn(Turn.FORWARD) shouldBe Direction.WEST
  }

  "point difference should be as expected" {
    Direction.NORTH.toPointDiff() shouldBe Point(0, -1)
    Direction.SOUTH.toPointDiff() shouldBe Point(0, 1)
    Direction.EAST.toPointDiff() shouldBe Point(1, 0)
    Direction.WEST.toPointDiff() shouldBe Point(-1, 0)
  }
})
