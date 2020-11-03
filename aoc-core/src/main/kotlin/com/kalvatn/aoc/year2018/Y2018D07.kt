package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2018
import kotlinx.coroutines.runBlocking
import java.util.ArrayDeque

class Y2018D07(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2018(Day.D07, input) {

  data class Step(val name: Char, val prereq: MutableSet<Char> = sortedSetOf())

  fun parseInput(): Map<Char, Step> {
    val steps = mutableMapOf<Char, Step>()
    for (line in this.input.lines) {
      "^Step (\\w) must be finished before step (\\w) can begin.$".toRegex()
        .matchEntire(line)
        ?.destructured
        ?.let { (prereq, name) ->
          steps.putIfAbsent(name.first(), Step(name.first()))
          steps.putIfAbsent(prereq.first(), Step(prereq.first()))
        }
    }
    for (line in this.input.lines) {
      "^Step (\\w) must be finished before step (\\w) can begin.$".toRegex()
        .matchEntire(line)
        ?.destructured
        ?.let { (prereq, name) ->
          val pre = steps[prereq.first()]!!
          val step = steps[name.first()]!!
          step.prereq.add(pre.name)
          steps[pre.name] = pre
          steps[step.name] = step
        }
    }
    return steps
  }

  fun processSteps(teamWork: Boolean = false): Pair<String, Int> {
    val steps = parseInput()
    var numberOfWorkers = 1
    val stepCosts: MutableMap<Char, Int>
    if (teamWork) {
      numberOfWorkers = if (steps.size > 6) 5 else 2
      stepCosts = steps.keys.sorted().map { it to it.toInt() - 64 + if (steps.size > 6) 60 else 0 }
        .toMap().toMutableMap()
    } else {
      stepCosts = steps.keys.sorted().map { it to 0 }.toMap().toMutableMap()
    }
    val done = mutableSetOf<Char>()

    val queue = ArrayDeque<Step>()
    steps.filter { it.value.prereq.isEmpty() }.keys.sorted().map { queue.add(steps[it]) }

    var secondsElapsed = 0
    val processing = mutableSetOf<Step>()
    while (queue.size > 0 || processing.size > 0) {
      if (processing.size < numberOfWorkers) {
        processing.addAll(queue.sortedWith(compareBy(Step::name)).take(numberOfWorkers - processing.size))
        queue.removeAll(processing)
      }
      for (step in processing) {
        stepCosts[step.name] = stepCosts[step.name]!!.dec()
        if (stepCosts[step.name]!! <= 0) {
          queue.remove(step)
          done.add(step.name)
          steps.map { it.value.prereq.remove(step.name) }
          steps.filter { it.value.prereq.isEmpty() }.keys.sorted().map { it ->
            if (!done.contains(it)) {
              queue.add(steps[it])
            }
          }
        }
      }
      processing.removeAll(processing.filter { done.contains(it.name) })
      secondsElapsed += 1
    }
    return Pair(done.joinToString(""), secondsElapsed)
  }

  override suspend fun partOne(): String {
    return processSteps().first
  }

  override suspend fun partTwo(): String {
    return processSteps(true).second.toString()
  }
}

fun main() = runBlocking {
  Y2018D07().run()
}
