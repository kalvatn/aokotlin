package com.kalvatn.aoc.core.model

import com.kalvatn.aoc.common.model.Direction
import com.kalvatn.aoc.common.model.Point
import com.kalvatn.aoc.common.model.Turn
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Test

class DirectionTest {
    @Test
    fun turn() {
        Assert.assertThat(Direction.NORTH.turn(Turn.LEFT), equalTo(Direction.WEST))
        Assert.assertThat(Direction.NORTH.turn(Turn.RIGHT), equalTo(Direction.EAST))

        Assert.assertThat(Direction.EAST.turn(Turn.LEFT), equalTo(Direction.NORTH))
        Assert.assertThat(Direction.EAST.turn(Turn.RIGHT), equalTo(Direction.SOUTH))

        Assert.assertThat(Direction.WEST.turn(Turn.LEFT), equalTo(Direction.SOUTH))
        Assert.assertThat(Direction.WEST.turn(Turn.RIGHT), equalTo(Direction.NORTH))

        Assert.assertThat(Direction.SOUTH.turn(Turn.LEFT), equalTo(Direction.EAST))
        Assert.assertThat(Direction.SOUTH.turn(Turn.RIGHT), equalTo(Direction.WEST))

        Assert.assertThat(Direction.SOUTH.turn(Turn.FORWARD), equalTo(Direction.SOUTH))
        Assert.assertThat(Direction.NORTH.turn(Turn.FORWARD), equalTo(Direction.NORTH))
        Assert.assertThat(Direction.EAST.turn(Turn.FORWARD), equalTo(Direction.EAST))
        Assert.assertThat(Direction.WEST.turn(Turn.FORWARD), equalTo(Direction.WEST))
    }

    @Test
    fun pointDiff() {
        Assert.assertThat(Direction.NORTH.toPointDiff(), equalTo(Point(0, -1)))
        Assert.assertThat(Direction.SOUTH.toPointDiff(), equalTo(Point(0, 1)))
        Assert.assertThat(Direction.EAST.toPointDiff(), equalTo(Point(1, 0)))
        Assert.assertThat(Direction.WEST.toPointDiff(), equalTo(Point(-1, 0)))
    }
}