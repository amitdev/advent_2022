import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TestDay1 {

  @Test
  fun testMaxCalories() {
    assertEquals(100, maxCalories(sequenceOf("1", "2", "", "50", "50")))
    assertEquals(3, maxCalories(sequenceOf("1", "2")))
    assertEquals(0, maxCalories(sequenceOf()))
    assertEquals(5, maxCalories(sequenceOf("1", "2", "", "5", "", "3", "1", "")))
  }

  @Test
  fun testTop3Calories() {
    assertEquals(103, topNCalories(sequenceOf("1", "2", "", "50", "50")))
    assertEquals(3, topNCalories(sequenceOf("1", "2")))
    assertEquals(0, topNCalories(sequenceOf()))
    assertEquals(12, topNCalories(sequenceOf("1", "2", "", "5", "", "3", "1", "")))
    assertEquals(32, topNCalories(sequenceOf(
      "1", "2", "", "5", "", "3", "1", "", "10", "10", "", "7")))
  }
}