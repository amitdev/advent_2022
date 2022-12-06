import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TestDay2 {
  @Test
  fun testScorePart1() {
    assertEquals(15, computeScorePart1(sequenceOf("A Y", "B X", "C Z")))
    assertEquals(8, computeScorePart1(sequenceOf("A Y")))
    assertEquals(0, computeScorePart1(sequenceOf()))
    assertEquals(9, computeScorePart1(sequenceOf("A X", "B Y")))
    assertEquals(18, computeScorePart1(sequenceOf("B Z", "A Z", "C Z")))
  }

  @Test
  fun testScorePart2() {
    assertEquals(12, computeScorePart2(sequenceOf("A Y", "B X", "C Z")))
    assertEquals(4, computeScorePart2(sequenceOf("A Y")))
    assertEquals(0, computeScorePart2(sequenceOf()))
  }
}