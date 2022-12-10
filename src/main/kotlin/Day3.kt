import java.io.File

fun main() {
  val result = File("inputs/day_3.txt").useLines { computePrioritiesPart2(it) }
  println(result)
}

fun computePrioritiesPart1(lines: Sequence<String>) =
  lines.map { line -> listOf(line.firstHalf(), line.secondHalf()) }
    .priority()

fun computePrioritiesPart2(lines: Sequence<String>) = lines.chunked(3).priority()
fun String.firstHalf() = this.substring(0, this.length/2)
fun String.secondHalf() = this.substring(this.length/2)
fun common(first: String, second: String) = first.filter { it in second }
fun Sequence<List<String>>.priority() =
  this.map { it.reduce(::common) }
    .map { priority(it.first()) }
    .sum()
fun priority(c: Char) = if (c.isLowerCase()) c -'a' + 1 else c - 'A' + 27