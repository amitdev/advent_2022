import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class TestDay11 {
  @Test
  fun testTotalBusiness() {
    assertEquals(10605, File("inputs/day_11_1.txt").useLines { totalBusiness(monkeys(it), 20, 3) })
    assertEquals(2713310158, File("inputs/day_11_1.txt").useLines { totalBusiness(monkeys(it), 10000, 1) })
    assertEquals(56595, File("inputs/day_11.txt").useLines { totalBusiness(monkeys(it), 20, 3) })
    assertEquals(15693274740, File("inputs/day_11.txt").useLines { totalBusiness(monkeys(it), 10000, 1) })
  }
}