fun main() {
  val expected = mapOf(
    "entotrefirefem" to 15,
    "sjufirenitrettentrettitretrettitre" to 99,
    InputLoader.load("01") to 142609
  )
  val replacements = listOf(
    "en" to 1, "to" to 2, "tre" to 3, "fire" to 4, "fem" to 5,
    "seks" to 6, "sju" to 7, "åtte" to 8, "ni" to 9, "ti" to 10,
    "elleve" to 11, "tolv" to 12, "tretten" to 13, "fjorten" to 14,
    "femten" to 15, "seksten" to 16, "sytten" to 17, "atten" to 18,
    "nitten" to 19, "tjue" to 20, "tretti" to 30, "førti" to 40,
    "femti" to 50
  )
  expected.forEach { (input, expected) ->
    val sum = replacements.reversed()
      .fold(input) { acc, pair ->
        acc.replace(pair.first, " ${pair.second} ")
      }.split(" ").sumOf {
        it.toIntOrNull() ?: 0
      }
    if (sum != expected) {
      throw Exception("expected $expected, was $sum")
    }
    println(input)
    println(sum)
  }
}
