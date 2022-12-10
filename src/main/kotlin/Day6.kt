import java.io.File

fun main() {
  val result = File("inputs/day_6.txt").useLines { findMarker(it.first(), 14) }
  println(result)
}

fun findMarker(line: String, SIZE: Int = 4) =
  (0..line.length-SIZE)
    .map { it to line.substring(it, it+SIZE) }
    .first { (_, mark) -> mark.toSet().size == SIZE }
    .first + SIZE