package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2019
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking

class Y2019D23(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2019(Day.D23, input) {

  val program by lazy { this.input.singleLineLongs() }

  override suspend fun partOne(): String {
    val computers = (0L..49).map {
      val pc = IntcodeComputer(program)
      pc.input(it)
      it to pc
    }.toMap()
    while (true) {
      computers.forEach { (k, v) ->
        v.input(-1)
        v.run()
        val message = v.output()
        message.chunked(3).map {
          val (dest, x, y) = it
          if (dest == 255L) {
            return y.toString()
          }
          computers[dest]!!.apply {
            input(x)
            input(y)
          }
        }
      }
    }

    return ""
  }

  override suspend fun partTwo(): String {
    val computers = (0L..49).map {
      val pc = IntcodeComputer(program)
      pc.input(it)
      it to pc
    }.toMap()

    val nat = mutableListOf<Long>()
    var yTwice = -1L
    val router = mutableMapOf<Long, MutableList<Long>>()
    while (true) {
      val allIdle = router.values.flatten().isEmpty() && computers.values.all { it.output().isEmpty() }
      if (allIdle && nat.isNotEmpty()) {
        val (x, y) = nat
        if (y == yTwice) {
          return y.toString()
        }
        yTwice = y
        router.computeIfAbsent(0) { mutableListOf() }.apply {
          add(x)
          add(y)
        }
      }
      router.forEach { (k, v) ->
        (computers[k] ?: error("impossiburu")).apply {
          v.forEach {
            input(it)
          }
        }
        v.clear()
      }
      computers.forEach { (k, v) ->
        if (v.inputQueue().isEmpty()) {
          v.input(-1)
        }
        v.run()
        val message = v.output()
        if (v.inputQueue().isNotEmpty() && v.output().isNotEmpty()) {
          println("$k input=${v.inputQueue()} output=$message")
        }
        message.chunked(3).forEach {
          val (dest, x, y) = it
          if (dest == 255L) {
            println("NAT $x $y")
            nat.clear()
            nat.add(x)
            nat.add(y)
          } else {
            router.computeIfAbsent(dest) { mutableListOf() }.apply {
              add(x)
              add(y)
            }
          }
        }
        v.clearOutput()
      }
    }

    return ""
  }

  companion object {
  }
}

fun main() = runBlocking {
  PuzzleRunner(listOf(Y2019D23())).run()
}
