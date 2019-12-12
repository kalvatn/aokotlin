package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2019
import com.kalvatn.aoc.core.runner.PuzzleRunner
import com.kalvatn.aoc.extensions.extractIntegers
import kotlinx.coroutines.runBlocking
import kotlin.math.abs


class Y2019D12(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2019(Day.D12, input) {

    private val moons by lazy { this.input.lines.map { Moon.fromString(it) } }

    data class Vec3(var x: Int, var y: Int, var z: Int) {
        override fun toString(): String {
            return "x=%2d, y=%2d, z=%2d".format(x, y, z)
        }
    }

    data class Moon(var position: Vec3, var velocity: Vec3) {
        companion object {
            fun fromString(string: String): Moon {
                val (ix, iy, iz) = string.split(",").flatMap { it.extractIntegers() }
                return Moon(Vec3(ix, iy, iz), Vec3(0, 0, 0))
            }
        }

        fun pot(): Int = abs(position.x) + abs(position.y) + abs(position.z)
        fun kin(): Int = abs(velocity.x) + abs(velocity.y) + abs(velocity.z)
        fun tot() = pot() * kin()

        fun move() {
            position = Vec3(
                    position.x + velocity.x,
                    position.y + velocity.y,
                    position.z + velocity.z
            )
        }

        fun newVelocity(other: Moon): Vec3 {
            val xDiff = other.position.x.compareTo(position.x)
            val yDiff = other.position.y.compareTo(position.y)
            val zDiff = other.position.z.compareTo(position.z)
            return Vec3(
                    xDiff,
                    yDiff,
                    zDiff
            )
        }

        override fun toString(): String {
            return "pos=<$position>, vel=<$velocity> , ${pot()} * ${kin()} = ${tot()}"
        }
    }


    override suspend fun partOne(): String {
        var minSize = 70
        (0..1000).forEach { second ->
            val total = moons.map { it.tot() }.sum()
            if (second % 10 == 0) {
                println("$second total : $total")
                moons.forEach { println(it) }
            }
            val newVs = mutableMapOf<Moon, Vec3>()
            moons.forEach { moon ->
                val newV = moons.filterNot { it == moon }.map { moon.newVelocity(it) }
//                println(newV)
                val vx = newV.map { it.x }.sum()
                val vy = newV.map { it.y }.sum()
                val vz = newV.map { it.z }.sum()
                newVs[moon] = Vec3(moon.velocity.x + vx, moon.velocity.y + vy, moon.velocity.z + vz)
            }
            moons.forEach {
                it.velocity = newVs[it]!!

                it.move()
            }
        }
        return ""
    }

    override suspend fun partTwo(): String {
        return ""
    }

    companion object {

    }

}

fun main() = runBlocking {
    //    val input = PuzzleInput.forDay(Year.Y2019, Day.D12, "test2")
//    PuzzleRunner(listOf(Y2019D12(input))).run()
    PuzzleRunner(listOf(Y2019D12())).run()
}

