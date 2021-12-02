import com.kalvatn.aoc.common.graph.WeightedGraph
import com.kalvatn.aoc.common.graph.dijkstra
import kotlin.math.abs

data class Point(val x: Double, val y: Double) {

  fun distance(other: Point): Int {
    return abs(other.x - this.x).toInt() + abs(other.y - this.y).toInt()
  }
}

fun main() {
  val input = InputLoader.load("02_fix")
  val cityLoc = input.lines().drop(1).associate { line ->
    val (city, loc) = line.split(",")
    println("$city $loc")
    val (x, y) = loc
      .replace("Point(", "")
      .replace(")", "")
      .split(" ").map { it.trim().toDouble() }
    city to Point(x, y)
  }.toMutableMap().apply {
    this["NP"] = Point(0.0, 0.0)
  }

}
