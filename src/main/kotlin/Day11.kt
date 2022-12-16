import java.io.File

fun main() {
  val result = File("inputs/day_11_1.txt").useLines { totalBusiness(monkeys(it), 10000, 1) }
  println(result)
}

fun totalBusiness(startState: Map<Int, Monkey>, times: Int = 20, divideBy: Int = 3) =
  (1..times)
    .fold(startState) { acc, _ -> simulateThrows(acc, divideBy, startState.values.totalDivides()) }
    .values.map { it.count }
    .sortedDescending()
    .take(2)
    .reduce(Long::times)

private fun Collection<Monkey>.totalDivides() = map { it.divisibleBy }.reduce(Int::times)

fun simulateThrows(monkeys: Map<Int, Monkey>, divideBy: Int, totalDivides: Int) =
  monkeys.keys.fold(monkeys) { acc, i -> rearrange(acc, acc[i]!!.throwStuff(divideBy, totalDivides)) }

fun rearrange(acc: Map<Int, Monkey>, newPositions: Map<Int, List<Long>>) =
  newPositions.entries.fold(acc) { a, (n, items) -> a + (n to a[n]!!.updateItems(items)) }

fun monkeys(lines: Sequence<String>) =
  lines.filter { it.isNotBlank() }
    .chunked(6)
    .map { parseMonkey(it) }
    .associateBy { it.n }
fun parseMonkey(lines: List<String>) = Monkey(
  n = lines[0].split(" ")[1].split(":")[0].toInt(),
  items = lines[1].split(": ")[1].split(", ").map { it.toLong() },
  operation = parseOp(lines[2].split("= ")[1].split(" ")),
  next = parseDivisible(lastInt(lines[3]), lastInt(lines[4]), lastInt(lines[5])),
  divisibleBy = lastInt(lines[3])
)

fun parseDivisible(divisibleBy: Int, option1: Int, option2: Int) = { n: Long ->
  if (n % divisibleBy == 0L) option1 else option2
}

private fun lastInt(line: String) = line.split(" ").last().toInt()

fun parseOp(s: List<String>) = when (s[1]) {
  "*" -> operation(s[0], s[2], Long::times)
  "+" -> operation(s[0], s[2], Long::plus)
  "-" -> operation(s[0], s[2], Long::minus)
  "/" -> operation(s[0], s[2], Long::div)
  else -> throw IllegalArgumentException()
}

fun operation(op1: String, op2: String, param: (Long, Long) -> Long) = op1.toLongOrNull()
  ?.let {operand1 ->  op2.toLongOrNull()?.let { { _: Long -> param(operand1, it) } } ?: { n: Long -> param(n, operand1) } }
  ?: op2.toLongOrNull()?.let { { n: Long -> param(n, it) } } ?: { n: Long -> param(n, n) }

data class Monkey(
  val n: Int, val items: List<Long>,
  val operation: Function1<Long, Long>,
  val next: (Long) -> Int,
  val divisibleBy: Int,
  val count: Long = 0) {
  fun throwStuff(divideBy: Int, totalDivides: Int) =
    items.map { (operation(it % totalDivides) / divideBy) }.groupBy { next(it) } + (n to listOf())

  fun updateItems(newItems: List<Long>) =
    if (newItems.isEmpty()) copy(items = listOf(), count = count + items.size)
    else copy(items = items + newItems)
  override fun toString(): String {
    return "Monkey(n=$n, items=$items, count=$count)"
  }
}
