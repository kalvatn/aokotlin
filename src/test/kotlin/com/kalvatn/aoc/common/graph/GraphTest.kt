package com.kalvatn.aoc.common.graph

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

@Suppress("LocalVariableName")
internal class GraphTest : StringSpec({
    val graph = Graph<Node<String>>()


    val A = Node("A")
    val B = Node("B")
    val C = Node("C")
    val D = Node("D")
    val E = Node("E")
    graph.connect(A, B)
    graph.connect(A, C)
    graph.connect(B, D)
    graph.connect(C, D)
    graph.connect(D, E)


    "connect" {
        graph.connections[A] shouldBe setOf(B, C)
        graph.connections[B] shouldBe setOf(A, D)
        graph.connections[C] shouldBe setOf(A, D)
        graph.connections[D] shouldBe setOf(B, C, E)
        graph.connections[E] shouldBe setOf(D)
    }

    "DFS" {
        graph.DFS(A) shouldBe listOf(A, C, D, E, B)
    }
    "BFS" {
        graph.BFS(A) shouldBe listOf(A, B, C, D, E)
    }

})