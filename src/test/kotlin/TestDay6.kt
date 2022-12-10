import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TestDay6 {
  @Test
  fun testFindMarker() {
    assertEquals(7, findMarker("mjqjpqmgbljsphdztnvjfqwrcgsmlb"))
    assertEquals(5, findMarker("bvwbjplbgvbhsrlpgdmjqwftvncz"))
    assertEquals(6, findMarker("nppdvjthqldpwncqszvftbrmjlhg"))
    assertEquals(11, findMarker("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"))
  }
}