package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2019
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking
import java.util.ArrayDeque
import kotlin.math.ceil

class Y2019D14(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2019(Day.D14, input) {

  private val regex = "(\\d+)\\s(\\w+)".toRegex()
  private val recipes by lazy {
    this.input.lines.map { reaction ->
      val quantities = regex.findAll(reaction).map {
        it.destructured.let { mr ->
          val name = mr.component2()
          Quantity(name, mr.component1().toInt())
        }
      }.toList()
      val produces = quantities.last()
      val requires = quantities.take(quantities.count() - 1)
      val recipe = Recipe(produces, requires)
      recipe.produces.component to recipe
    }.toMap()
  }

  data class Quantity(val component: String, val quantity: Int)
  data class Recipe(val produces: Quantity, val requires: List<Quantity>)

  private fun oreNeededForFuel(fuelCount: Long): Long {
    val leftovers = mutableMapOf<String, Long>()

    val queue = ArrayDeque<Pair<String, Long>>()
    queue.add(Pair("FUEL", fuelCount))

    var oreCount = 0L
    while (queue.isNotEmpty()) {
      val (component, requested) = queue.remove()
      val leftover = leftovers[component] ?: 0
      if (requested < leftover) {
        if (leftover > requested) {
          leftovers[component] = leftover - requested
          continue
        }
      }
      val required = (requested - leftover).also {
        leftovers[component] = 0
      }

      if (component == "ORE") {
        oreCount += required
      } else {
        val recipe = recipes[component] ?: error("impossiburu")
        val multiplier = ceil(required.toDouble() / recipe.produces.quantity).toLong()
        val produced = multiplier * recipe.produces.quantity
        leftovers[component] = produced - required
        recipe.requires.forEach {
          queue.add(Pair(it.component, it.quantity * multiplier))
        }
      }
    }
    return oreCount
  }

  override suspend fun partOne(): String {
    return oreNeededForFuel(1).toString()
  }

  override suspend fun partTwo(): String {
    var min = 1L
    var max = ORE_AVAILABLE
    while (min < max) {
      val count = (min + max + 1) / 2
//            println("$min $mid $max")
      if (oreNeededForFuel(count) <= ORE_AVAILABLE) {
        min = count
      } else {
        max = count - 1
      }
    }
    return min.toString()
  }

  companion object {
    const val ORE_AVAILABLE = 1000000000000L
  }
}

fun main() = runBlocking {
  PuzzleRunner(listOf(Y2019D14())).run()
}
