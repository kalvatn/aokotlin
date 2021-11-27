package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2018
import kotlinx.coroutines.runBlocking

class Y2018D08(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2018(Day.D08, input) {

  private val numbers = this.input.singleLine().split(" ").map { it.toInt() }

  data class Node(
    val index: Int,
    val parent: Node? = null,
    val countChildren: Int,
    val countMeta: Int,
    val children: MutableList<Node> = mutableListOf(),
    val meta: MutableList<Int> = mutableListOf()

  ) {
    override fun toString(): String {
      return "Node index : $index, " +
        "children : $countChildren, " +
        "collected children : ${children.map { it.index }} " +
        "meta: $countMeta ($meta), parent: ${parent?.index}"
    }
  }

  private fun processNodes(): MutableMap<Int, Node> {
    val nodes: MutableMap<Int, Node> = mutableMapOf()
    var currentParent: Node? = null
    var i = 0
    while (i < numbers.size - 1) {
      if (currentParent != null && (currentParent.children.size == currentParent.countChildren)) {
        val startMeta: Int = i
        val meta = numbers.slice(startMeta until startMeta + currentParent.countMeta)
        currentParent.meta.addAll(meta)
        i = startMeta + currentParent.countMeta
        currentParent = currentParent.parent
      } else {
        val countChildren = numbers[i]
        val countMeta = numbers[i + 1]

        val node = Node(i, currentParent, countChildren, countMeta)
        nodes[i] = node

        i += 2
        if (node.parent != null) {
          node.parent.children.add(node)
        }

        if (countChildren == 0) {
          val startMeta: Int = i
          val meta = numbers.slice(startMeta until startMeta + countMeta)
          node.meta.addAll(meta)
          i = startMeta + countMeta
          currentParent = node.parent
        } else {
          currentParent = node
        }
      }
    }
    return nodes
  }

  override suspend fun partOne(): String {
    val nodes = processNodes()
    return nodes.values.sumOf { it.meta.sum() }.toString()
  }

  override suspend fun partTwo(): String {
    val nodes = processNodes()
    val nodeValues = mutableMapOf<Int, Int>()
    nodes.keys.forEach {
      nodeValues[it] = -1
    }
    while (nodeValues.values.contains(-1)) {
      nodes.forEach { (index, node) ->
        if (node.countChildren == 0) {
          nodeValues[index] = node.meta.sum()
        } else {
          var value = 0
          var missing = false
          for (childIndex in node.meta) {
            if (childIndex <= node.children.size) {
              val child = node.children[childIndex - 1]
              if (nodeValues.containsKey(child.index)) {
                val childValue = nodeValues[child.index]!!
                if (childValue < 0) {
                  missing = true
                }
                value += childValue
              }
            }
          }
          if (!missing) {
            nodeValues[index] = value
          }
        }
      }
    }
    return nodeValues[0].toString()
  }
}

fun main() = runBlocking {
  Y2018D08().run()
}
