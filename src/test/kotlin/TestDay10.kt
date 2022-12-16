import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class TestDay10 {
  @Test
  fun testSignalStrength() {
    assertEquals(14360, File("inputs/day_10.txt").useLines { signalStrength(it) })
  }
}