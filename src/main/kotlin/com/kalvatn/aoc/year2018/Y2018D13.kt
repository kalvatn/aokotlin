package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.common.Day
import com.kalvatn.aoc.common.PuzzleInput
import com.kalvatn.aoc.common.model.Direction
import com.kalvatn.aoc.common.model.Point
import com.kalvatn.aoc.common.model.Turn
import com.kalvatn.aoc.utils.buildArray2D
import kotlin.math.max

class Y2018D13(input: PuzzleInput? = null) : APuzzle2018(Day.D13, input) {

    val grid: Grid = Grid(this.input.lines)

    class Grid(val lines: List<String>) {
        val size: Int = max(lines.size, lines.map { it.length }.max()!!)
        var firstCrash: Point? = null

        private val array = buildArray2D(size, ' ')
        private var trains = mutableSetOf<Train>()

        init {
            var i = 0
            lines.forEachIndexed { y, row ->
                row.forEachIndexed { x, col ->
                    array[y][x] = col
                    when (col) {
                        in listOf('>', '<', 'v', '^') -> {
                            when (col) {
                                '>' -> array[y][x] = '-'
                                '<' -> array[y][x] = '-'
                                'v' -> array[y][x] = '|'
                                '^' -> array[y][x] = '|'
                            }
                            trains.add(Train(i++, Point(x, y), Direction.fromChar(col)))
                        }
                    }
                }
            }
        }


        fun trains(): MutableSet<Train> {
            return trains
        }

        fun tick(): Pair<Point, Point>? {
            val removeTrains = mutableSetOf<Train>()
            trains.sortedWith(compareBy(Train::position)).forEach { train ->
                train.move(this)
                val crashedWith = trains.filter { it != train && it.position == train.position }
                if (crashedWith.any()) {
                    if (firstCrash == null) {
                        firstCrash = train.position
                    }
                    removeTrains.add(train)
                    removeTrains.addAll(crashedWith)
                }
            }
            if (removeTrains.isNotEmpty()) {
                trains.removeAll(removeTrains)
            }
            if (trains.size <= 1) {
                return Pair(firstCrash!!, trains.first().position)
            }
            return null
        }

        fun print() {
            val trainsByPos = trains.map { it.position to it }.toMap()
            array.forEachIndexed { y, row ->
                row.forEachIndexed { x, col ->
                    val point = Point(x, y)
                    if (trainsByPos.containsKey(point)) {
                        print(trainsByPos[point]!!.direction.toChar())
                    } else {
                        print(col)
                    }
                }
                println()
            }
        }

        fun valueAt(pos: Point): Char {
            return array[pos.y][pos.x]
        }


    }

    data class Train(val id: Int, var position: Point, var direction: Direction, var turnStep: Int = 0) {

        fun move(grid: Grid) {
            position = position.plus(direction.toPointDiff())
            val nextGridPosValue = grid.valueAt(position)
            val isVertical = direction == Direction.NORTH || direction == Direction.SOUTH
            when (nextGridPosValue) {
                '\\' -> direction = if (isVertical) direction.turn(Turn.LEFT) else direction.turn(Turn.RIGHT)
                '/' -> direction = if (isVertical) direction.turn(Turn.RIGHT) else direction.turn(Turn.LEFT)
                '+' -> direction = direction.turn(turns[turnStep++ % turns.size])
            }
        }

        override fun equals(other: Any?): Boolean {
            return other is Train && id == other.id
        }

        override fun hashCode(): Int {
            return id
        }
    }

    private fun simulate(): Pair<Point, Point> {
        while (true) {
            val crashes = grid.tick()
            if (crashes != null) {
                return crashes
            }
        }
    }

    private val crashes = simulate()

    override fun partOne(): String {
        return "${crashes.first.x},${crashes.first.y}"
    }

    override fun partTwo(): String {
        return "${crashes.second.x},${crashes.second.y}"
    }

    companion object {
        val turns = listOf(Turn.LEFT, Turn.FORWARD, Turn.RIGHT)
    }

}

fun main(args: Array<String>) {
//    val day = Y2018D13(PuzzleInput.forDay(Year.Y2018, Day.D13, "test"))
    val day = Y2018D13()
    day.run()
}