import java.io.File
import java.lang.Integer.max

fun main() {
  val result = File("inputs/day_8.txt").useLines { findScenicScore(it) }
  println(result)
}

fun findVisible(lines: Sequence<String>) =
  Grid(lines.map { line -> line.map { it.digitToInt()} }.toList())
    .toVisbile()

fun findScenicScore(lines: Sequence<String>) =
  Grid(lines.map { line -> line.map { it.digitToInt()} }.toList())
    .score()

data class Grid(val data: List<List<Int>>) {
  fun toVisbile() = data.indices.flatMap { x ->
    (0 until data[0].size).filter { y ->
      left(x, y) || right(x, y) || top(x, y) || down(x, y)
    }
  }.count()

  fun score() = data.indices.flatMap { x ->
    (0 until data[0].size).map { y ->
      leftScore(x, y) * rightScore(x, y) * topScore(x, y) * downScore(x, y)
    }
  }.max()

  private fun leftScore(x: Int, y: Int) = y - max(data[x].subList(0, y).indexOfLast { it >= data[x][y] }, 0)
  private fun rightScore(x: Int, y: Int) = data[x].subList(y+1, data[x].size)
    .indexOfFirst { it >= data[x][y] }
    .let { if (it == -1) data[x].size-1-y else it + 1 }
  private fun topScore(x: Int, y: Int) = x - max((0 until x).indexOfLast { data[x][y] <= data[it][y] }, 0)
  private fun downScore(x: Int, y: Int) = (x+1 until data.size)
    .indexOfFirst { data[x][y] <= data[it][y] }
    .let { if (it == -1) data.size-1-x else it + 1 }

  private fun left(x: Int, y: Int) = data[x].subList(0, y).maxOrZero() < data[x][y]
  private fun right(x: Int, y: Int) = data[x].subList(y+1, data[x].size).maxOrZero() < data[x][y]
  private fun top(x: Int, y: Int) = (0 until x).map { data[it][y] }.maxOrZero() < data[x][y]
  private fun down(x: Int, y: Int) = (x+1 until data.size).map { data[it][y] }.maxOrZero() < data[x][y]

  fun List<Int>.maxOrZero() = if (this.isEmpty()) -1 else this.max()
}