package com.kalvatn.aoc.year2021

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2021
import com.kalvatn.aoc.core.model.Year
import com.kalvatn.aoc.core.runner.PuzzleRunner
import com.kalvatn.aoc.exceptions.Impossiburu
import com.kalvatn.aoc.year2021.Y2021D08.Pattern.Companion.SEGMENT_COUNT_CANDIDATE
import kotlinx.coroutines.runBlocking

//  0:      1:      2:      3:      4:      5:      6:      7:      8:      9:
// aaaa    ....    aaaa    aaaa    ....    aaaa    aaaa    aaaa    aaaa    aaaa
//b    c  .    c  .    c  .    c  b    c  b    .  b    .  .    c  b    c  b    c
//b    c  .    c  .    c  .    c  b    c  b    .  b    .  .    c  b    c  b    c
// ....    ....    dddd    dddd    dddd    dddd    dddd    ....    dddd    dddd
//e    f  .    f  e    .  .    f  .    f  .    f  e    f  .    f  e    f  .    f
//e    f  .    f  e    .  .    f  .    f  .    f  e    f  .    f  e    f  .    f
// gggg    ....    gggg    gggg    ....    gggg    gggg    ....    gggg    gggg

class Y2021D08(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2021(Day.D08, input) {

  private val lines by lazy { this.input.lines }

  data class Pattern(val signal: List<String>, val output: List<String>) {

    companion object {

      val SEGMENT_COUNT_CANDIDATE = mapOf(
        2 to listOf(1),
        3 to listOf(7),
        4 to listOf(4),
        5 to listOf(2, 3, 5),
        6 to listOf(0, 6, 9),
        7 to listOf(8)
      )
    }
  }

  override suspend fun partOne(): String {
    val count = lines.fold(0) { acc, line ->
      val (_, output) = parse(line)
      val single = output.mapNotNull { token ->
        SEGMENT_COUNT_CANDIDATE.filterValues { it.size == 1 }[token.count()]
      }.count()
      acc + single
    }
    return "$count"
  }

  fun decode(line: String): Int {
    val (signal, output) = parse(line)
    return decode(signal, output)
  }

  fun segmentsToDigit(indices: Set<Int>) =
    when (indices) {
      setOf(2, 5) -> 1
      setOf(0, 2, 5) -> 7
      setOf(1, 2, 3, 5) -> 4
      setOf(0, 2, 3, 4, 6) -> 2
      setOf(0, 2, 3, 5, 6) -> 3
      setOf(0, 1, 3, 5, 6) -> 5
      setOf(0, 1, 3, 4, 5, 6) -> 6
      setOf(0, 1, 2, 3, 5, 6) -> 9
      setOf(0, 1, 2, 4, 5, 6) -> 0
      setOf(0, 1, 2, 3, 4, 5, 6) -> 8
      else -> throw Impossiburu()
    }

  fun digitToSegment(digit: Int) =
    when (digit) {
      1 -> setOf(2, 5)
      7 -> setOf(0, 2, 5)
      4 -> setOf(1, 2, 3, 5)
      2 -> setOf(0, 2, 3, 4, 6)
      3 -> setOf(0, 2, 3, 5, 6)
      5 -> setOf(0, 1, 3, 5, 6)
      6 -> setOf(0, 1, 3, 4, 5, 6)
      9 -> setOf(0, 1, 2, 3, 5, 6)
      0 -> setOf(0, 1, 2, 4, 5, 6)
      8 -> setOf(0, 1, 2, 3, 4, 5, 6)
      else -> throw Impossiburu()
    }

  fun possibleSegmentMappings(signal: List<String>): Map<Int, Set<Char>> {
    val segments = (0..6).associateWith { mutableSetOf<Char>() }.toMutableMap()
    val handledIdx = mutableListOf<Int>()
    val handledChr = mutableSetOf<Char>()
    listOf(1, 7, 4, 2, 3, 5, 0, 6, 9, 8).forEach { digit ->
      digitToSegment(digit).filter { it !in handledIdx }.forEach { segmentIndex ->
        val candidates = signal.filter { digit in SEGMENT_COUNT_CANDIDATE[it.count()]!! }
        candidates.forEach { signal ->
          segments[segmentIndex]!!.plusAssign(signal.toSet().filter { it !in handledChr })
        }
        handledIdx.add(segmentIndex)
      }
      handledChr.addAll(segments.values.flatten())
    }
    return segments
  }

  fun lol(cur: List<List<Char>>, foo: String = ""): List<String> {
    if (cur.isEmpty()) return listOf(foo)
    val sdf = cur.first().filter { it !in foo }
    if (sdf.size == 1) {
      return lol(cur.drop(1), foo + sdf.first())
    }
    return lol(cur.drop(1), foo + sdf.first()) + lol(cur.drop(1), foo + sdf[1])
  }

  fun decode(signal: List<String>, output: List<String>): Int {
    val possible = possibleSegmentMappings(signal)
    val possible2 = mutableMapOf<Char, MutableSet<Int>>()
    val aasdfasdf = mutableListOf<List<Char>>()
    possible.flatMap { (pk, pv) ->
      aasdfasdf.add(pv.toList())
      println("$pk $pv")
      pv.map { char ->
        possible2.computeIfAbsent(char) { mutableSetOf() }.add(pk)
        char to pk
      }
    }.also {
      println(it.size)
      println(it.toSet().size)
    }
    val all = lol(aasdfasdf).map {
      it.mapIndexed { index, c -> c to index }.toMap()
    }.iterator()

    while (all.hasNext()) {
      val it = all.next()
      var num = ""
      try {
        output.map { digit ->
          println(it)
          val mapping2 = digit.map { c -> it[c]!! }.toSet()
          val ii = segmentsToDigit(mapping2)
          num += "$ii"
        }
        return num.toInt()
      } catch (e: Exception) {
        continue
      }
    }
    throw Impossiburu()
  }

  override suspend fun partTwo(): String {
    return lines.fold(0) { acc, line ->
      acc + decode(line)
    }.toString()
  }

  private fun parse(line: String) = line.split("|").map { side ->
    side.trim().split(" ").filter { it.isNotBlank() }
  }
}

fun main() = runBlocking {
  val input = PuzzleInput.p1Test(Year.Y2021, Day.D08)
  PuzzleRunner.run(Y2021D08(input))
  PuzzleRunner.run(Y2021D08())
}
