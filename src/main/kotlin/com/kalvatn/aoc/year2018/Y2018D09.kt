package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.common.Day
import com.kalvatn.aoc.common.PuzzleInput

class Y2018D09(input: PuzzleInput? = null) : APuzzle2018(Day.D09, input) {

    class Marble {
        val value: Int
        var previous: Marble
        var next: Marble

        constructor(value:Int) {
            this.value = value
            previous = this
            next = this
        }
        constructor(value: Int, previous: Marble, next: Marble) {
            this.value = value
            this.previous = previous
            this.previous.next = this
            this.next = next
            this.next.previous = this
        }

    }
    class Circle(initial:Int) {
        private var current = Marble(initial)

        fun insert(value:Int) {
            this.current = Marble(value, previous = current, next = current.next)
        }

        fun removeAndReturn():Int {
            val value = current.value
            current.previous.next = current.next
            current.next.previous = current.previous
            current = current.next
            return value
        }

        fun rotate(times:Int) = when {
            times > 0 -> repeat(times) {
                current = current.next
            }
            else -> repeat(times * -1) {
                current = current.previous
            }
        }
    }

    fun getHighScore(numberOfPlayers:Int, numberOfMarbles:Int): Long {
        val players = mutableMapOf<Int, Long>()
        (1..numberOfPlayers).forEach {
            players[it] = 0
        }

        val circle = Circle(0)
        var currentPlayer = 1
        for (marble in 1..numberOfMarbles) {
            if (marble % 23 == 0) {
                circle.rotate(-7)
                val removedMarble = circle.removeAndReturn()
                players[currentPlayer] = players[currentPlayer]!! + (marble + removedMarble)
            } else {
                circle.rotate(1)
                circle.insert(marble)
            }
            currentPlayer = ((currentPlayer) % players.size) + 1

        }
        return players.values.max()!!
    }


    override fun partTwo(): String {
        val (numberOfPlayers:Int, numberOfMarbles:Int) = "(\\d+) players; last marble is worth (\\d+) points".toRegex().matchEntire(this.input.singleLine())!!.destructured.toList().map { it.toInt() }
        return getHighScore(numberOfPlayers, numberOfMarbles*100).toString()
    }

    override fun partOne(): String {
        val (numberOfPlayers:Int, numberOfMarbles:Int) = "(\\d+) players; last marble is worth (\\d+) points".toRegex().matchEntire(this.input.singleLine())!!.destructured.toList().map { it.toInt() }
        return getHighScore(numberOfPlayers, numberOfMarbles).toString()
    }


}


fun main(args: Array<String>) {
    val day = Y2018D09()
    day.run()
}
