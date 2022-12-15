import Direction.D
import Direction.L
import Direction.R
import Direction.U
import java.io.File
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.sign

fun main() {
  val result = File("inputs/day_9.txt").useLines { tailPositions(it, 9) }
  println(result)
}

fun tailPositions(lines: Sequence<String>, tailLength: Int = 1) =
  max(lines.fold(listOf(Position(t = (0..tailLength).map { Point(0, 0) }))) { acc, line ->
    acc + moves(Move.parse(line), acc.last(), tailLength)
  }
    .distinctBy { it.t[tailLength - 1] }
    .count(), 1)

data class Point(val x: Int, val y: Int) {
  operator fun plus(other: Point) = Point(x + other.x, y + other.y)

  fun diff(other: Point) = Point(other.x - x, other.y - y)
  fun follow(diff: Point) = when {
    max(abs(diff.x), abs(diff.y)) <= 1 -> this
    else -> copy(x = x + diff.x.sign, y = y + diff.y.sign)
  }
}

fun moves(move: Move, current: Position, tailLength: Int) =
  (1..move.steps).fold(listOf(current)) { positions, _ ->
    val previousPos = positions.last()
    val h = previousPos.h + next(move.direction)
    val t =
      (0 until tailLength).fold(listOf(previousPos.t[0].follow(previousPos.t[0].diff(h)))) { acc, i ->
        acc + previousPos.t[i + 1].follow(previousPos.t[i + 1].diff(acc.last()))
      }
    positions + Position(h, t)
  }

fun next(direction: Direction) = when (direction) {
  R -> Point(0, 1)
  U -> Point(-1, 0)
  L -> Point(0, -1)
  D -> Point(1, 0)
}

data class Position(val h: Point = Point(0, 0), val t: List<Point>)

data class Move(val direction: Direction, val steps: Int) {
  companion object {
    fun parse(line: String) = Move(toDirection(line), line.split(" ")[1].toInt())

    private fun toDirection(line: String) = when (line.split(" ")[0]) {
      "R" -> R
      "U" -> U
      "L" -> L
      "D" -> D
      else -> throw IllegalArgumentException()
    }
  }
}

enum class Direction {
  R,
  U,
  L,
  D
}