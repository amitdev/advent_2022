import RPS.PAPER
import RPS.ROCK
import RPS.SCISSORS
import java.io.File
import kotlin.IllegalArgumentException

fun main() {
  val result = File("inputs/day_2.txt").useLines { computeScorePart2(it) }
  println(result)
}

// Part 1
fun computeScorePart1(lines: Sequence<String>) =
  lines.map { it.split(" ") }
    .map { scorePart1(it.first().toRPS(), it.last().toRPS()) }
    .sum()


fun scorePart1(opponent: RPS, mine: RPS): Int {
  return mine.points + when {
    opponent == mine -> 3
    opponent.wins() == mine -> 6
    else -> 0
  }
}

// Part 2
fun computeScorePart2(lines: Sequence<String>) =
  lines.map { it.split(" ") }
    .map { scorePart2(it.first().toRPS(), it.last()) }
    .sum()

fun scorePart2(opponent: RPS, result: String) = when (result) {
  "X" -> opponent.lose().points
  "Y" -> 3 + opponent.points
  else -> 6 + opponent.wins().points
}

enum class RPS(val points: Int) {
  ROCK(1),
  PAPER(2),
  SCISSORS(3);

  fun wins() = order.first { (first, _) -> first == this }.second

  fun lose() = order.first { (_, second) -> second == this }.first

  companion object {
    val order = listOf(ROCK to PAPER, PAPER to SCISSORS, SCISSORS to ROCK)
  }
}

fun String.toRPS() = when (this) {
  "A", "X" -> ROCK
  "B", "Y" -> PAPER
  "C", "Z" -> SCISSORS
  else -> throw IllegalArgumentException()
}
