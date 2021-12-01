fun main() {
  val expected = mapOf(
    "entotrefirefem" to 15,
    "sjufirenitrettentrettitretrettitre" to 99,
//    InputLoader.load("01") to 142609, // original
    InputLoader.load("01") to 143489,
//    "tohundreogtrettitremillionfirehundreogtitusentrehundreogåttitre" to 233410383,
    "tjueen" to 21,
    "nittitretreåttisjunittini" to 93 + 3 + 87 + 99,
    "etthundreogellevetusenogåttisjuento" to 111088
  )
  val replacements = listOf(
    "ett" to 1, "en" to 1, "to" to 2, "tre" to 3, "fire" to 4, "fem" to 5,
    "seks" to 6, "sju" to 7, "åtte" to 8, "ni" to 9, "ti" to 10,
    "elleve" to 11, "tolv" to 12, "tretten" to 13, "fjorten" to 14,
    "femten" to 15, "seksten" to 16, "sytten" to 17, "atten" to 18,
    "nitten" to 19, "tjue" to 20, "tretti" to 30, "førti" to 40,
    "femti" to 50, "seksti" to 60, "sytti" to 70, "åtti" to 80, "nitti" to 90,
    "hundre" to 100, "tusen" to 1000//, "million" to 1_000_000
  )
  expected.forEach { (input, expected) ->
    val replaced = replacements.reversed()
      .fold(input) { acc, pair ->
        acc.replace(pair.first, " ${pair.second} ")
      }
    val tokens = replaced.split(" ").map { it.trim() }.filter { it.isNotBlank() }
    var sum = 0

    var tmp = 0
    var i = 0

    while(true) {
      if(i + 2 >= tokens.size) {
        sum += tokens[i].toInt()
        sum += tokens[i+1].toInt()
        break
      }
      val v1 = tokens[i].toInt()
      val v2 = tokens[i+1].toInt()
      val v3 = tokens[i+2]
      if (v3 == "og") {
        tmp = (tmp+v1)*v2
      } else {
        sum += tmp + v1 + v2
        tmp =0
      }
      println(listOf(i, v1, v2, v3, tmp, sum))
      i+=3

    }





//    val sum = tokens.sumOf {
//      it.toIntOrNull() ?: 0
//    }
    if (sum != expected) {
      throw Exception("expected $expected, was $sum")
    }
    println(input)
    println(sum)
  }
}
