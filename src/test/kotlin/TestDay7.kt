import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class TestDay7 {
  @Test
  fun testFileSize() {
    assertEquals(95437, File("inputs/day_7_1.txt").useLines { fileSize(it) })
    assertEquals(1749646, File("inputs/day_7.txt").useLines { fileSize(it) })
  }

  @Test
  fun testFileSizeToDelete() {
    assertEquals(24933642, File("inputs/day_7_1.txt").useLines { fileSizeToDelete(it) })
    assertEquals(1498966, File("inputs/day_7.txt").useLines { fileSizeToDelete(it) })
  }
}