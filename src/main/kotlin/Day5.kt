fun solve(lines: Sequence<String>, part1: Boolean) =
  lines.fold(Input()) { acc, line -> acc.merge(acc.parse(line)) }
    .arrangeCrates()
    .rearrange(part1)
    .top()


data class Input(
  val crates: Map<Int, List<String>> = mapOf(),
  val instructions: List<Instruction> = listOf(),
  private val parsedCrates: Boolean = false) {
  fun merge(that: Input) = when {
    parsedCrates -> copy(instructions = instructions + that.instructions)
    that.parsedCrates -> copy(parsedCrates = true)
    else -> Input(
      (this.crates.keys + that.crates.keys).associateWith {
        this.crates.getOrDefault(it, listOf()) + that.crates.getValue(it)
      })
  }

  fun arrangeCrates() = copy(
    crates = crates.mapValues { it.value.filter { it.startsWith("[") }
      .map { it.substring(1,2) }
    }
  )

  fun parse(line: String) = when {
    line.isBlank() -> Input(parsedCrates = true)
    parsedCrates -> Input(instructions = parseInstruction(line))
    else -> Input(parseCrate(line))
  }

  private fun parseInstruction(line: String) =
    listOf(line.split(" ").toInstruction())

  fun parseCrate(line: String) =
    line.chunked(4).withIndex().associate { v -> (v.index + 1) to listOf(v.value.trim()) }

  fun rearrange(inOrder: Boolean) =
    instructions.fold(crates) { acc, next -> acc.mapValues { when (it.key) {
      next.from -> it.value.subList(next.count, it.value.size)
      next.to -> acc.getValue(next.from).subList(0, next.count).let {
        if (inOrder) it.reversed() else it
      } + it.value
      else -> it.value
    }}}
}

fun Map<Int, List<String>>.top() = values.mapNotNull { it.firstOrNull() }.joinToString("")
private fun List<String>.toInstruction() = Instruction(this[1].toInt(), this[3].toInt(), this[5].toInt())

data class Instruction(val count: Int, val from: Int, val to: Int)