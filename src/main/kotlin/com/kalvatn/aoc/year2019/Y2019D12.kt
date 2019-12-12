package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2019
import com.kalvatn.aoc.core.model.Year
import com.kalvatn.aoc.core.runner.PuzzleRunner
import com.kalvatn.aoc.extensions.extractIntegers
import kotlinx.coroutines.runBlocking
import kotlin.math.abs


class Y2019D12(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2019(Day.D12, input) {

    private val moons by lazy { this.input.lines.map { Moon.fromString(it) } }

    data class Vec3(var x: Int, var y: Int, var z: Int) {
        override fun toString(): String {
            return "(%3d, %3d, %3d)".format(x, y, z)
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

    data class Snapshot(val moons:List<Moon>) {
        override fun toString(): String {
            return """
                ${moons.map { it.position.x  }}${moons.map { it.position.y }} ${moons.map { it.position.z }}
            """.trimIndent()
            /*
0=[-1, 2, 4, 3][0, -10, -8, 5] [2, -7, 8, -1]

X aligned REPEATS EVERY 18

0  [2, 1, 3, 2][-2, -4, -7, 0] [1, 4, -3, 0]
18 [2, 1, 3, 2][1, -8, -6, 0] [3, 0, 1, -2]
36 [2, 1, 3, 2][0, -10, -8, 5] [-1, 2, -1, 2]
54 [2, 1, 3, 2][3, -9, -8, 1] [1, -4, 5, 0]
72 [2, 1, 3, 2][2, -7, -9, 1] [-4, -1, 2, 5]
90 [2, 1, 3, 2][-1, -7, -7, 2] [-4, -3, 4, 5]


Y aligned REPEATS EVERY 28
0 [-1, 2, 4, 3]  [0, -10, -8, 5] [2, -7, 8, -1]
28 [2, 1, 3, 2]  [0, -10, -8, 5] [1, -2, 3, 0]
56 [5, 1, 1, 1]  [0, -10, -8, 5] [4, 5, -4, -3]
84 [-1, 4, 2, 3] [0, -10, -8, 5] [-1, 6, -5, 2]
112 [2, 2, 2, 2] [0, -10, -8, 5] [-1, 2, -1, 2]
140 [5, 0, 2, 1] [0, -10, -8, 5] [-2, -5, 6, 3]
168 [-1, 3, 3, 3][0, -10, -8, 5] [1, -4, 5, 0]
196 [2, 3, 1, 2] [0, -10, -8, 5] [5, 0, 1, -4]
224 [5, 2, 0, 1] [0, -10, -8, 5] [0, 7, -6, 1]
252 [-1, 2, 4, 3][0, -10, -8, 5] [1, 4, -3, 0]
280 [2, 1, 3, 2] [0, -10, -8, 5] [-4, -1, 2, 5]

Z aligned REPEATS EVERY 44
0 [-1, 2, 4, 3][0, -10, -8, 5] [2, -7, 8, -1]
44 [5, 2, 0, 1][-4, -4, -3, -2] [2, -7, 8, -1]
88 [2, 3, 1, 2][-8, 1, 3, -9] [2, -7, 8, -1]
132 [-1, 3, 3, 3][-2, -4, -7, 0] [2, -7, 8, -1]
176 [5, 0, 2, 1][2, -7, -9, 1] [2, -7, 8, -1]
220 [2, 2, 2, 2][-6, 0, 1, -8] [2, -7, 8, -1]
264 [-1, 4, 2, 3][-7, 2, -1, -7] [2, -7, 8, -1]
308 [5, 1, 1, 1][0, -10, -8, 5] [2, -7, 8, -1]
352 [2, 1, 3, 2][-4, -4, -3, -2] [2, -7, 8, -1]
396 [-1, 2, 4, 3][-8, 1, 3, -9] [2, -7, 8, -1]
440 [5, 2, 0, 1][-2, -4, -7, 0] [2, -7, 8, -1]



X AND Y aligned
251=[-1, 2, 4, 3][0, -10, -8, 5] [4, 5, -4, -3]
252=[-1, 2, 4, 3][0, -10, -8, 5] [1, 4, -3, 0]
503=[-1, 2, 4, 3][0, -10, -8, 5] [-1, 2, -1, 2]
504=[-1, 2, 4, 3][0, -10, -8, 5] [5, 0, 1, -4]

             */
        }
    }

    override suspend fun partOne(): String {
        repeat(1000) {
            val newVs = mutableMapOf<Moon, Vec3>()
            moons.forEach { moon ->
                val newV = moons.filterNot { it == moon }.map { moon.newVelocity(it) }
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
        return moons.map { it.tot() }.sum().toString()
    }

    override suspend fun partTwo(): String {
        val initial = moons.map { it.copy() }
        val snapshots = mutableMapOf<Int, Snapshot>()

        val Zpos = initial.map { it.position.z }
        val Zvel = initial.map { it.velocity.z }
        (0 until 10000000).forEach { second ->
            snapshots[second] = Snapshot(moons.map { it.copy() })

//            if (moons.map { it.position.x } == initial.map { it.position.x } && moons.map { it.velocity.x } == initial.map { it.velocity.x }) {
//                println("X $second ${snapshots[second]}")
//            }
//            if (moons.map { it.position.y } == initial.map { it.position.y } && moons.map { it.velocity.y } == initial.map { it.velocity.y }) {
//
//                println("Y $second ${snapshots[second]}")
//            }
            if (moons.map { it.position.z } == Zpos && moons.map { it.velocity.z } == Zvel) {
                println("Z $second ${snapshots[second]}")
            }
            val newVs = mutableMapOf<Moon, Vec3>()
            moons.forEach { moon ->
                val newV = moons.filterNot { it == moon }.map { moon.newVelocity(it) }
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
        /*
X 0 [46, -80, 6, 43][12, -81, 60, 6] [-29, -11, 19, 10]
X 161428 [46, -80, 6, 43][-28, -64, -45, 134] [-73, 10, 64, -12]
X 322856 [46, -80, 6, 43][-6, 68, 271, -336] [-543, -61, 744, -151]

Y 0 [46, -80, 6, 43][12, -81, 60, 6] [-29, -11, 19, 10]
Y 167624 [61, -78, 31, 1][12, -81, 60, 6] [-58, 109, 67, -129]
Y 335248 [-80, 415, -328, 8][12, -81, 60, 6] [-935, 333, 711, -120]
Y 502872 [-350, 174, -109, 300][12, -81, 60, 6] [-106, -261, 589, -233]

Z 0 [46, -80, 6, 43][12, -81, 60, 6] [-29, -11, 19, 10]
Z 193052 [-504, 183, -21, 357][-14, -406, -76, 493] [-29, -11, 19, 10]
Z 386104 [-324, -433, -41, 813][530, 144, -347, -330] [-29, -11, 19, 10]
Z 579156 [982, -355, -446, -166][-621, -448, 609, 457] [-29, -11, 19, 10]

http://www.mathematicsmagazine.com/applications/GCF_LCM.htm
         */
        return 326489627728984.toString()
    }

    companion object {

    }

}

fun main() = runBlocking {
//    val input = PuzzleInput.forDay(Year.Y2019, Day.D12, "test")
//    val input = PuzzleInput.forDay(Year.Y2019, Day.D12, "test2")
//    PuzzleRunner(listOf(Y2019D12(input))).run()
    PuzzleRunner(listOf(Y2019D12())).run()
}

