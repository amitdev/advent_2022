import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TestDay4 {
  @Test
  fun testFullyContains() {
    assertEquals(0, computeFullOverlaps(sequenceOf()))
    assertEquals(1, computeFullOverlaps(sequenceOf("11-20,10-21")))
    assertEquals(2, computeFullOverlaps(sequenceOf("2-4,2-4","1-6,2-3","1-4,5-6")))
  }

  @Test
  fun testOverlaps() {
    assertEquals(0, computeOverlaps(sequenceOf()))
    assertEquals(1, computeOverlaps(sequenceOf("11-20,10-21")))
    assertEquals(0, computeOverlaps(sequenceOf("11-20,1-10")))
    assertEquals(3, computeOverlaps(sequenceOf("2-4,1-3","1-6,2-7","1-4,1-4")))
  }
}