import java.io.File

fun main() {
  val result = File("inputs/day_1.txt").useLines { topNCalories(it) }
  println(result)
}

fun maxCalories(lines: Sequence<String>) =
  lines.runningFolds().max()

fun topNCalories(lines: Sequence<String>, n: Int = 3) =
  (lines.runningFolds() + 0).zipWithNext()
    .filter { (_, b) -> b == 0 }
    .map { (a, _) -> a }
    .sortedDescending()
    .take(n)
    .sum()

private fun Sequence<String>.runningFolds() =
  this.runningFold(0) { acc, elem -> if (elem.isBlank()) 0 else acc + elem.toInt() }
