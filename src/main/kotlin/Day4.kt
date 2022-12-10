import java.io.File

fun main() {
  val result = File("inputs/day_4.txt").useLines { computeOverlaps(it) }
  println(result)
}

fun computeFullOverlaps(lines: Sequence<String>) =
  lines.map { toPairs(it) }
    .count { it.first.contains(it.second) || it.second.contains(it.first)}

fun computeOverlaps(lines: Sequence<String>) =
  lines.map { toPairs(it) }
    .count { it.first.overlaps(it.second) || it.second.overlaps(it.first)}

fun toPairs(it: String) =
  it.split(",")
    .map { it.split("-").map { it.toInt() } }
    .map { it.toPair() }
    .toPair()

fun <T> List<T>.toPair() = Pair(this[0], this[1])
fun Pair<Int, Int>.contains(that: Pair<Int, Int>) =
  this.first <= that.first && this.second >= that.second

fun Pair<Int, Int>.overlaps(that: Pair<Int, Int>) =
  this.first <= that.first && this.second >= that.first ||
    this.first >= that.first && this.first <= that.second
