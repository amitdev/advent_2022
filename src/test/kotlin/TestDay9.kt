import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TestDay9 {
  @Test
  fun testTailPosition() {
    assertEquals(4, tailPositions(sequenceOf("R 4")))
    assertEquals(4, tailPositions(sequenceOf("L 4")))
    assertEquals(5, tailPositions(sequenceOf("L 4", "R 6")))
    assertEquals(13, tailPositions(sequenceOf("R 4",
      "U 4",
      "L 3",
      "D 1",
      "R 4",
      "D 1",
      "L 5",
      "R 2")))

  }

  @Test
  fun testLongTailPosition() {
    assertEquals(1, tailPositions(sequenceOf("R 4"), 9))
    assertEquals(3, tailPositions(sequenceOf("R 11"), 9))
    assertEquals(1, tailPositions(sequenceOf("R 4",
      "U 4",
      "L 3",
      "D 1",
      "R 4",
      "D 1",
      "L 5",
      "R 2"), 9))
  }
}