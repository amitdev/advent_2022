import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TestDay3 {
  @Test
  fun testPriorityPart1() {
    assertEquals(16, computePrioritiesPart1(sequenceOf("vJrwpWtwJgWrhcsFMMfFFhFp")))
    assertEquals(
      54, computePrioritiesPart1(
        sequenceOf(
          "vJrwpWtwJgWrhcsFMMfFFhFp", "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL"
        )
      )
    )
    assertEquals(0, computePrioritiesPart1(sequenceOf()))
  }

  @Test
  fun testPriorityPart2() {
    assertEquals(0, computePrioritiesPart2(sequenceOf()))
    assertEquals(
      18, computePrioritiesPart2(
        sequenceOf(
          "vJrwpWtwJgWrhcsFMMfFFhFp",
          "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
          "PmmdzqPrVvPwwTWBwg"
        )
      )
    )
    assertEquals(
      70, computePrioritiesPart2(
        sequenceOf(
          "vJrwpWtwJgWrhcsFMMfFFhFp",
          "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
          "PmmdzqPrVvPwwTWBwg",
          "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
          "ttgJtRGJQctTZtZT",
          "CrZsJsPPZsGzwwsLwLmpwMDw"
        )
      )
    )
  }
}