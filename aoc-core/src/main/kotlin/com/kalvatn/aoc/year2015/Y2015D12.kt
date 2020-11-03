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

  fun extractNumbersFromJson(s: String): Int {
    var sum = 0
    try {
      return s.toInt()
    } catch (e: Exception) {
      when (val json = Json.parseToJsonElement(s)) {
        is JsonArray -> json.forEach { sum += extractNumbersFromJson(it.toString()) }
        is JsonObject -> {
          if (!json.jsonObject.values.contains(JsonPrimitive("red"))) {
            json.jsonObject.values.forEach {
              sum += extractNumbersFromJson(it.toString())
            }
          }
        }
      }
    }
    return sum
  }
}

fun main() = runBlocking {
  PuzzleRunner.run(Y2015D12())
}
