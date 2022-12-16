import java.io.File

fun main() {
  val result = File("inputs/day_10.txt").useLines { draw(it) }
  println(result)
}

fun signalStrength(lines: Sequence<String>) =
  moves(lines)
    .filter { it.cycle in setOf(20, 60, 100, 140, 180, 220) }
    .sumOf { it.x * it.cycle }

fun draw(lines: Sequence<String>) =
  moves(lines)
    .chunked(40)
    .mapIndexed { i, row -> row.map { it.toChar(i)  }.joinToString("") }
    .forEach { println(it) }
private fun moves(lines: Sequence<String>) =
  lines.fold(listOf(Register())) { acc, line ->
    val last = acc.last()
    when (line) {
      "noop" -> acc + last.copy(cycle = last.cycle + 1)
      else -> acc + listOf(
        last.copy(cycle = last.cycle + 1),
        last.copy(cycle = last.cycle + 2, x = last.x + line.split(" ")[1].toInt())
      )
    }
  }
data class Register(val x: Int = 1, val cycle: Int = 1) {
  fun toChar(i: Int) = if (x + 40*i in (cycle-2..cycle)) '#' else '.'
}