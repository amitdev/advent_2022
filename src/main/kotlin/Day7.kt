import java.nio.file.Path

fun fileSizeToDelete(lines: Sequence<String>) = directorySizes(lines).smallestFileToDelete()

fun fileSize(lines: Sequence<String>) =
  directorySizes(lines).directorySizes.values
    .filter { it <=100000 }
    .sum()

private fun directorySizes(lines: Sequence<String>) =
  lines.fold(DirectoryStructure()) { acc, line ->
    when {
      line.startsWith("$ cd ") -> acc.cd(line.split(" ")[2])
      line[0].isDigit() -> acc.addSizes(line.split(" ")[0].toInt())
      else -> acc
    }
  }

data class DirectoryStructure(
  val directorySizes: Map<Path, Int> = mapOf(),
  val current: Path = ROOT_PATH
) {
  fun cd(dirName: String) = when (dirName) {
    ROOT -> copy(current = ROOT_PATH)
    PARENT -> copy(current = current.parent)
    else -> copy(current = current.resolve(dirName))
  }

  fun addSizes(size: Int) = copy(directorySizes = directorySizes + findSizes(current, size))

  fun totalSize() = directorySizes[ROOT_PATH]!!

  private fun findSizes(current: Path, size: Int): List<Pair<Path, Int>> =
    listOf(current to directorySizes.getOrDefault(current, 0) + size) + parentSize(current, size)

  private fun parentSize(current: Path, size: Int) =
    if (current.parent == null) listOf() else findSizes(current.parent, size)
  fun smallestFileToDelete() = directorySizes.values
    .sorted().first { totalSize() - it <= 40000000 }

  companion object {
    val ROOT = "/"
    val PARENT = ".."
    val ROOT_PATH = Path.of(ROOT)
  }
}


