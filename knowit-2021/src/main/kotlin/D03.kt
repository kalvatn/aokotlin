import kotlin.system.exitProcess

fun main() {
  val input = InputLoader.load("03")
//  val input = "JJJJJNNJJNNJJJJJ"
  val counts = input.groupingBy { it }.eachCount()
  println(counts)
  val max = counts.values.minOf { it } * 2
  println(max)

  (2..max)
    .filter { it % 2 == 0 }
    .forEach { size ->
      if (size % 200 == 0) {
        println(size)
      }
      val windowed = input.windowed(size)
      windowed.map { window ->
        val a = window.count { it == 'J' }
        val b = window.count { it == 'N' }
        if (a == b && window == window.reversed()) {
          val index = input.indexOf(window)
          println("$window $a $b : $size, $index")
        }
      }
    }

}
