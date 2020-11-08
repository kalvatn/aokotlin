package com.kalvatn.aoc.year2016

import com.kalvatn.aoc.core.model.AbstractTask
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Input
import com.kalvatn.aoc.core.model.NoState
import com.kalvatn.aoc.core.model.Output
import com.kalvatn.aoc.core.model.PartResult
import com.kalvatn.aoc.core.model.Year
import com.kalvatn.aoc.extensions.md5
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking

class Y2016D05 : AbstractTask<String, String, NoState>(Year.Y2016, Day.D05) {


  private val hashCache = mutableMapOf<Int, String>()
  private val fiveZeroes = "0".repeat(5)

  private fun hashesStartingWithFiveZeroes(key: String): Sequence<String> {
    return sequence {
      for (i in 0..Int.MAX_VALUE) {
        val hash = hashCache.computeIfAbsent(i) { "${key}$i".md5() }
        if (hash.startsWith(fiveZeroes)) {
          yield(hash)
        }
      }
    }
  }

  private fun printProgress(password: Array<Char?>) {
    print(password.map {
      when (it) {
        null -> '_'
        else -> it
      }
    }.joinToString("") + "\r")
    System.out.flush()
  }

  override suspend fun p1(input: Input) = coroutineScope {
    PartResult.of {
      val doorId = input.singleLine()
      val secret = arrayOfNulls<Char>(8)
      hashesStartingWithFiveZeroes(doorId)
        .takeWhile { secret.any { it == null } }
        .forEachIndexed { index, s ->
          printProgress(secret)
          secret[index] = s[5]
        }
      secret.joinToString("")
    }
  }


  override suspend fun p2(input: Input) = coroutineScope {
    PartResult.of {
      val doorId = input.singleLine()
      val secret = arrayOfNulls<Char>(8)
      hashesStartingWithFiveZeroes(doorId)
        .takeWhile { secret.any { it == null } }
        .forEach { hash ->
          printProgress(secret)
          val pos = hash[5]
          if (pos.isDigit()) {
            val index = Character.getNumericValue(pos)
            if (index in (0..7) && secret[index] == null) {
              secret[index] = hash[6]
            }
          }
        }
      secret.joinToString("")
    }
  }

  override suspend fun solve(input: Input) = coroutineScope {
    Output.of(year, day) {
      runBlocking {
        val (p1, p2) = awaitAll(
          async { p1(input) },
          async { p2(input) }
        )
        Pair(p1, p2)
      }
    }

  }
}

fun main() = runBlocking {
  val task = Y2016D05()
//  task.solve(Input.ofString("abc")).print()
  task.solve(Input.forDay(Year.Y2016, Day.D05)).print()
}
