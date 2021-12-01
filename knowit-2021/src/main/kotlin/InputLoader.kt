import com.kalvatn.aoc.exceptions.Impossiburu

object InputLoader {

  fun load(filename: String): String {
    return InputLoader::class.java.getResource(filename)?.readText() ?: throw Impossiburu()
  }
}
