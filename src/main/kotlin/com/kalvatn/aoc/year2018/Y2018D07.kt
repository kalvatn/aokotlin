package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.common.Day
import com.kalvatn.aoc.common.PuzzleInput
import com.kalvatn.aoc.common.Year
import com.kalvatn.aoc.exceptions.Impossiburu
import java.util.*

class Y2018D07(input: PuzzleInput? = null) : APuzzle2018(Day.D07, input) {


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
    val steps = parseInput()

    override fun partOne(): String {


        val done = mutableSetOf<Char>()

        val queue = ArrayDeque<Step>()
        steps.filter { it.value.prereq.isEmpty() }.keys.sorted().map { queue.add(steps[it]) }
        println(queue)

        while (queue.size > 0) {
            val step = queue.sortedWith(compareBy(Step::name)).first()
            queue.remove(step)
            if (step.prereq.isEmpty()) {
                done.add(step.name)
                steps.map { it.value.prereq.remove(step.name) }
                steps.filter { it.value.prereq.isEmpty() }.keys.sorted().map { it ->
                    if (!done.contains(it))
                        queue.add(steps[it]) }
            } else {
                throw Impossiburu()
            }
        }
        return done.joinToString("")
    }

    override fun partTwo(): String {
        for (key in steps.keys.sorted()) {
            println("$key : ${key.toInt()-64}")
        }
        return ""
    }

}


fun main(args: Array<String>) {
    val day = Y2018D07(PuzzleInput.forDay(Year.Y2018, Day.D07, "test"))
//    val day = Y2018D07()
    day.run()
}
