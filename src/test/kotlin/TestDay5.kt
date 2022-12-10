import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class TestDay5 {
  @Test
  fun testSolve() {
    assertEquals("CMZ", solve(File("inputs/day_5_1.txt").readLines().asSequence(), true))
    assertEquals("MCD", solve(File("inputs/day_5_1.txt").readLines().asSequence(), false))
    assertEquals("BSDMQFLSP", solve(File("inputs/day_5.txt").readLines().asSequence(), true))
    assertEquals("PGSQBFLDP", solve(File("inputs/day_5.txt").readLines().asSequence(), false))
  }
}