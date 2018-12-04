package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.common.Day
import com.kalvatn.aoc.common.PuzzleInput
import com.kalvatn.aoc.extensions.extractIntegers


class Day04 : Day {

    constructor() : super(2018, 4)
    constructor(input: PuzzleInput) : super(2018, 4, input)

    enum class Action {
        BEGINS, SLEEPS, WAKES_UP
    }

    data class Event(
        val yyyy: Int,
        val mm: Int,
        val dd: Int,
        val HH: Int,
        val MM: Int,
        val guard: Int,
        val action: Action
    )

    fun parseLine(line: String): Event {
        //[1518-11-01 00:00] Guard #10 begins shift
        //[1518-11-01 00:05] falls asleep
        //[1518-11-01 00:25] wakes up
        val split = line.split(" ")
        val date = split[0].slice(1 until 11)
        val time = split[1].slice(0 until 5)
        val guardNumberList = split[3].extractIntegers()
        var guardNumber = -1
        val action: Action
        if (!guardNumberList.isEmpty()) {
            action = Action.BEGINS
            guardNumber = guardNumberList.first()
        } else {
            action = if (split[3].contains("asleep"))
                Action.SLEEPS
            else {
                Action.WAKES_UP
            }
        }
        val (yyyy, mm, dd) = date.split("-").map { it.toInt() }
        val (HH, MM) = time.split(":").map { it.toInt() }
        return Event(yyyy, mm, dd, HH, MM, guardNumber, action)

    }

    private val events =
        input.map { parseLine(it) }.sortedWith(compareBy(Event::yyyy, Event::mm, Event::dd, Event::HH, Event::MM))
    private val guardSleepMinutes = mutableMapOf<Int, Int>()
    private val guardSleepMinuteFrequency = mutableMapOf<Int, MutableMap<Int, Int>>()

    init {
        processEvents()
    }

    fun processEvents() {
        var guard = 0
        var sleepStart = 0
        for (event in events) {
            when (event.action) {
                Action.BEGINS -> {
                    guard = event.guard
                    guardSleepMinutes.putIfAbsent(guard, 0)
                    guardSleepMinuteFrequency.putIfAbsent(guard, mutableMapOf())
                }
                Action.SLEEPS -> {
                    sleepStart = event.MM
                }
                Action.WAKES_UP -> {
                    val slept = (event.MM - sleepStart)
                    guardSleepMinutes[guard] = guardSleepMinutes[guard]!! + slept
                    for (i in sleepStart..event.MM) {
                        guardSleepMinuteFrequency[guard]!!.putIfAbsent(i, 0)
                        guardSleepMinuteFrequency[guard]!![i] = guardSleepMinuteFrequency[guard]!![i]!!.inc()
                    }
                }
            }
        }
    }

    override fun partOne(): String {
        val guardMaxTotalSleep = guardSleepMinutes.maxBy { it.value }
        val guardMaxMinute = guardSleepMinuteFrequency[guardMaxTotalSleep!!.key]!!.maxBy { it.value }
        return (guardMaxTotalSleep.key * guardMaxMinute!!.key).toString()

    }

    override fun partTwo(): String {
        var guard = 0
        var max = 0
        var maxMinute = 0
        for (i in guardSleepMinutes.keys) {
            val guardMaxMinute = guardSleepMinuteFrequency[i]!!.maxBy { it.value }
            if (guardMaxMinute != null) {
                if (guardMaxMinute.value > max) {
                    max = guardMaxMinute.value
                    maxMinute = guardMaxMinute.key
                    guard = i
                }
            }

        }
        return (maxMinute * guard).toString()
    }
}

