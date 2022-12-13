import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class TestDay8 {
  @Test
  fun testFindVisible() {
    assertEquals(21, File("inputs/day_8_1.txt").useLines { findVisible(it) })
    assertEquals(1782, File("inputs/day_8.txt").useLines { findVisible(it) })
  }

  @Test
  fun testFindScenicScore() {
    assertEquals(8, File("inputs/day_8_1.txt").useLines { findScenicScore(it) })
    assertEquals(474606, File("inputs/day_8.txt").useLines { findScenicScore(it) })
  }
}