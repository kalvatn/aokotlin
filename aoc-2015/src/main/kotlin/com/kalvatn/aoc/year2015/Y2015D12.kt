package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2015
import com.kalvatn.aoc.core.runner.PuzzleRunner
import com.kalvatn.aoc.extensions.extractIntegers
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonObject

class Y2015D12(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2015(Day.D12, input) {

  private val rawJson by lazy { this.input.singleLine() }

  override suspend fun partOne(): String {
    return rawJson.extractIntegers().sum().toString()
  }

  override suspend fun partTwo(): String {
    return extractNumbersFromJson(rawJson).toString()
  }

  private fun extractNumbersFromJson(s: String): Int {
    return try {
      s.toInt()
    } catch (e: NumberFormatException) {
      return when (val json = Json.parseToJsonElement(s)) {
        is JsonArray -> json.sumOf { extractNumbersFromJson(it.toString()) }
        is JsonObject -> {
          val values = json.jsonObject.values
          return if (!values.contains(JsonPrimitive("red"))) {
            values.sumOf { extractNumbersFromJson(it.toString()) }
          } else {
            0
          }
        }
        else -> 0
      }
    }
  }
}

fun main() = runBlocking {
  PuzzleRunner.run(Y2015D12())
}
