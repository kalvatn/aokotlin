package com.kalvatn.aoc.common.graph

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

@Suppress("LocalVariableName")
internal class GraphTest : StringSpec({
  val graph = Graph<String>()

  val A = "A"
  val B = "B"
  val C = "C"
  val D = "D"
  val E = "E"
  graph.connect(A, B)
  graph.connect(A, C)
  graph.connect(B, D)
  graph.connect(C, D)
  graph.connect(D, E)

  "connect" {
    graph.edges[A] shouldBe setOf(B, C)
    graph.edges[B] shouldBe setOf(A, D)
    graph.edges[C] shouldBe setOf(A, D)
    graph.edges[D] shouldBe setOf(B, C, E)
    graph.edges[E] shouldBe setOf(D)
  }

  "DFS" {
    graph.DFS(A) shouldBe listOf(A, C, D, E, B)
  }
  "BFS" {
    graph.BFS(A) shouldBe listOf(A, B, C, D, E)
  }

  "distances" {
    graph.distances(A) shouldBe mapOf(
      A to 0,
      B to 1,
      C to 1,
      D to 2,
      E to 3
    )
  }

  "shortest path" {
    graph.shortestPath(A, E) shouldBe 3
  }
})
