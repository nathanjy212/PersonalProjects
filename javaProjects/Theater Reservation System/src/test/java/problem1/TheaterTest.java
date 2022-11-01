package problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for Theater class contains tests for theater class and tests classes that are connected
 * to its methods (Row, Seat).
 */
class TheaterTest {
  private Theater testTheater = new Theater("Test Theater", 10, 10, 2);
  private Theater otherTheater;
  private Seat testSeat = new Seat("A", "Test", true);

  @BeforeEach
  void setUp() {
  }

  @Test
  void buildTheater() {
    testTheater.buildTheater();
    assertTrue(testTheater.getRowCollection() != null);
  }

  /**
   * INDIRECTLY, then DIRECTLY tests that the returnAlphabet function works as expected --> it does.
   */
  @Test
  void testTheaterSeatNames() {
    otherTheater = new Theater("Small", 2, 100, 1);
    otherTheater.buildTheater();
    for (Row row : otherTheater.getRowCollection()) {
      for (Seat seat : row) {
        System.out.print(seat.getName() + " ");
      }
    }
    // direct test
    assertEquals(otherTheater.getRowCollection().get(0).get(28).getName(), "CC");
  }

  @Test
  void printTheaterSmall() {
    otherTheater = new Theater("Small", 2, 5, 1);
    otherTheater.buildTheater();
    assertEquals(otherTheater.printTheater(), 0);
  }

  @Test
  void printTheaterWithReservedSeat() {
    otherTheater = new Theater("Small", 2, 5, 1);
    otherTheater.buildTheater();
    otherTheater.getRowCollection().get(0).get(2).setReservedFor("Me");
    otherTheater.getRowCollection().get(1).get(2).setReservedFor("You");
    assertEquals(otherTheater.printTheater(), 0);
  }

  @Test
  void printTheaterLarge() {
    otherTheater = new Theater("Large", 25, 30, 5);
    otherTheater.buildTheater();
    assertEquals(otherTheater.printTheater(), 0);
  }

  @Test
  void updateTheaterNoWheelchairFail() {
    otherTheater = new Theater("Small", 2, 5, 1);
    otherTheater.buildTheater();
    assertEquals(otherTheater.updateTheaterNoWheelchair(50, "no"), -1);
  }

  @Test
  void updateTheaterNoWheelchairSuccess() {
    testTheater.buildTheater();
    assertEquals(testTheater.updateTheaterNoWheelchair(10, "no"), 4);
  }


  @Test
  void updateTheaterWheelchairFail() {
    otherTheater = new Theater("Small", 2, 5, 1);
    otherTheater.buildTheater();
    assertEquals(otherTheater.updateTheaterWheelchair(50, "no"), 0);
  }

  @Test
  void updateTheaterWheelchairSuccess() {
    otherTheater = new Theater("Small", 10, 15, 1);
    otherTheater.buildTheater();
    assertEquals(otherTheater.updateTheaterWheelchair(5, "no"), 9);
  }

  @Test
  void getName() {
    assertEquals(testTheater.getName(), "Test Theater");
  }

  @Test
  void testEquals() {
    assertTrue(testTheater.equals(testTheater));
  }

  @Test
  void testEqualsSameFields() {
    otherTheater = new Theater("Test Theater", 10, 10, 2);
    assertTrue(testTheater.equals(otherTheater));
  }

  @Test
  void testEqualsDiffNames() {
    otherTheater = new Theater("Other Theater", 10, 10, 2);
    assertFalse(testTheater.equals(otherTheater));
  }

  @Test
  void testEqualsDiffRows() {
    otherTheater = new Theater("Test Theater", 5, 10, 2);
    assertFalse(testTheater.equals(otherTheater));
  }

  @Test
  void testEqualsDiffSeats() {
    otherTheater = new Theater("Test Theater", 10, 50, 2);
    assertFalse(testTheater.equals(otherTheater));
  }

  @Test
  void testEqualsDiffWheelchairAccessibleRows() {
    otherTheater = new Theater("Test Theater", 10, 10, 15);
    assertFalse(testTheater.equals(otherTheater));
  }

  @Test
  void testEqualsDiffObjects() {
    otherTheater = new Theater("Test Theater", 10, 10, 2);
    assertFalse(testTheater.equals(testSeat));
  }

  @Test
  void testHashCodeSame() {
    assertTrue(testTheater.hashCode() == testTheater.hashCode());
  }

  @Test
  void testHashCodeDiffFields() {
    otherTheater = new Theater("Other Theater", 2, 4, 1);
    assertFalse(testTheater.hashCode() == otherTheater.hashCode());
  }

  @Test
  void testHashCodeDiffObjects() {
    assertFalse(testTheater.hashCode() == testSeat.hashCode());
  }

  @Test
  void testToString() {
    assertEquals(testTheater.toString(), "Theater{name='Test Theater', rowCollection=[], numberOfRows=10, numberOfSeats=10, numberOfWheelChairAccessibleRows=2}");
  }

  @Test
  void preBuildCheckTest() {
    otherTheater = new Theater("Other Theater", 2, 4, 0);
    otherTheater.buildTheater();
  }

  @Test
  void checkToReserveBack() {
    otherTheater = new Theater("Small", 15, 10, 2);
    otherTheater.buildTheater();
    otherTheater.updateTheaterNoWheelchair(9, "ben");
    otherTheater.updateTheaterNoWheelchair(9, "ben");
    otherTheater.updateTheaterNoWheelchair(9, "ben");
    otherTheater.updateTheaterNoWheelchair(9, "ben");
    otherTheater.updateTheaterNoWheelchair(9, "ben");
    otherTheater.updateTheaterNoWheelchair(9, "ben");
    assertEquals(otherTheater.getRowCollection().get(7).get(3).getReservedFor(), "ben");
  }
}