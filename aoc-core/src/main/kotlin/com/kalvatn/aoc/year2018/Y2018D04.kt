package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzleYearDay
import com.kalvatn.aoc.core.model.Year
import com.kalvatn.aoc.exceptions.Impossiburu
import com.kalvatn.aoc.year2018.Y2018D04.GuardRecord.Companion.fromString
import kotlinx.coroutines.runBlocking

class Y2018D04(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzleYearDay(Year.Y2018, Day.D04, input) {

  enum class Action {
    BEGINS, SLEEPS, WAKES;

    companion object {
      fun fromString(string: String): Action {
        return when (string) {
          "begins shift" -> BEGINS
          "falls asleep" -> SLEEPS
          "wakes up" -> WAKES
          else -> {
            throw Impossiburu()
          }
        }
      }
    }
  }

  data class GuardRecord(
    val y: Int,
    val m: Int,
    val d: Int,
    val hh: Int,
    val mm: Int,
    val guardId: Int,
    val action: Action
  ) {

    companion object {
      fun fromString(string: String): GuardRecord {
        val regex = "^\\[(\\d+)-(\\d+)-(\\d+) (\\d+):(\\d+)\\] (?:Guard #(\\d+) )?([\\w\\s]+)".toRegex()
        return regex.matchEntire(string)
          ?.destructured
          ?.let { (y, m, d, hh, mm, guardId, action) ->
            GuardRecord(
              y.toInt(), m.toInt(), d.toInt(), hh.toInt(), mm.toInt(),
              when (guardId) {
                "" -> -1
                else -> guardId.toInt()
              },
              Action.fromString(action)
            )
          }!!
      }
    }
  }

  data class Guard(
    val id: Int,
    val sleepTime: Int = 0,
    val sleepMinuteFrequency: MutableMap<Int, Int> = mutableMapOf()
  ) {
    fun sleep(start: Int, stop: Int): Guard {
      for (i in start..stop) {
        sleepMinuteFrequency[i] = sleepMinuteFrequency[i]?.inc() ?: 1
      }
      return Guard(id, sleepTime + (stop - start), sleepMinuteFrequency)
    }
  }

  private val records =
    this.input.map { fromString(it) }
      .sortedWith(compareBy(GuardRecord::y, GuardRecord::m, GuardRecord::d, GuardRecord::hh, GuardRecord::mm))
  private val guards = processRecords(records.iterator(), mapOf(), Guard(-1), 0)

  private tailrec fun processRecords(
    records: Iterator<GuardRecord>,
    guards: Map<Int, Guard>,
    currentGuard: Guard,
    sleepStart: Int
  ): Map<Int, Guard> {
    if (!records.hasNext()) {
      return guards
    }
    val event = records.next()
    return when (event.action) {
      Action.BEGINS -> {
        val guardId = event.guardId
        val guard = guards.getOrDefault(guardId, Guard(guardId))
        processRecords(records, guards.plus(Pair(guardId, guard)), guard, 0)
      }
      Action.SLEEPS -> {
        processRecords(records, guards, currentGuard, event.mm)
      }
      Action.WAKES -> {
        val guard = currentGuard.sleep(sleepStart, event.mm)
        processRecords(records, guards.plus(Pair(guard.id, guard)), guard, sleepStart)
      }
    }
  }

  override suspend fun partOne(): String {
    val guard = guards.maxBy { it.value.sleepTime }!!.value
    val maxMinute = guard.sleepMinuteFrequency.maxBy { it.value }!!.key
    return (guard.id * maxMinute).toString()
  }

  override suspend fun partTwo(): String {
    val guard = guards.maxBy {
      it.value.sleepMinuteFrequency.maxBy { mf -> mf.value }?.value ?: 0
    }!!.value
    val maxMinute = guard.sleepMinuteFrequency.maxBy { it.value }!!.key
    return (guard.id * maxMinute).toString()
  }
}

fun main() = runBlocking {
  Y2018D04().run()
}
