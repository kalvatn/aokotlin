package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.common.Day
import com.kalvatn.aoc.common.PuzzleInput
import com.kalvatn.aoc.common.Year
import java.util.*
import kotlin.coroutines.coroutineContext

class Y2018D08(input: PuzzleInput? = null) : APuzzle2018(Day.D08, input) {

    private val numbers = this.input.singleLine().split(" ").map { it.toInt() }


    data class Node(
        val index: Int,
        val name:Char,
        val parent:Node? = null,
        val countChildren: Int,
        val countMeta: Int,
        val children:MutableList<Node> = mutableListOf(),
        val meta:MutableList<Int> = mutableListOf()

    ) {
        override fun toString(): String {
            return "Node-$name, $index, $meta, parent: ${parent?.name}"
        }
    }

    override fun partOne(): String {
        /*
2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2
A----------------------------------
    B----------- C-----------
                     D-----
2 3 0 3 10 11 12 1 1 2 1 1 2
A----------------------------
    B----------- C-----

         */
        val queue = ArrayDeque<Char>()
            queue.addAll('A'..'Z')

        val nodes:MutableMap<Int, Node> = mutableMapOf()
        var currentParent:Node? = null
        println(numbers)
        var i = 0
        while (i < numbers.size-1) {
            val countChildren = numbers[i]
            val countMeta = numbers[i+1]

            val node = Node(i, queue.pop(), currentParent, countChildren, countMeta)
            nodes[i] = node

            if (countChildren == 0) {
                val startMeta:Int = i+2
                val meta = numbers.slice(startMeta until startMeta+countMeta)
                node.meta.addAll(meta)
                i = startMeta+countMeta
            } else {
                currentParent = node
                i+=2
            }
        }
        var totalMeta = 0
        nodes.forEach {
            println(it)
            totalMeta += it.value.meta.sum()
        }

        return totalMeta.toString()
    }

    override fun partTwo(): String {
        return ""
    }

}


fun main(args: Array<String>) {
    val day = Y2018D08(PuzzleInput.forDay(Year.Y2018, Day.D08, "test2"))
//    val day = Y2018D08()
    day.run()
}
