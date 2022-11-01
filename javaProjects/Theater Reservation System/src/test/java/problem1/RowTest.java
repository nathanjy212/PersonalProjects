package problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for Row class contains tests for the Row class and tests classes that are connected
 * to its methods (Seat and additional coverage for theater).
 */
class RowTest {
  Theater testTheater = new Theater("Roxy", 15, 26, 3);
  Theater otherTheater = new Theater("Roxy", 15, 26, 3);
  Theater newTheater;

  @BeforeEach
  void setUp() {
    testTheater.buildTheater();
    otherTheater.buildTheater();
  }

  @Test
  void getRowNumberFront() {
    assertTrue(testTheater.getRowCollection().get(0).getRowNumber() == 1);
  }

  @Test
  void getRowNumberBack() {
    assertTrue(testTheater.getRowCollection().get(14).getRowNumber() == 15);
  }

  @Test
  void getWheelchairAccessibleFront() {
    assertTrue(testTheater.getRowCollection().get(0).getWheelchairAccessible() == false);
  }

  @Test
  void getWheelchairAccessibleBack() {
    assertTrue(testTheater.getRowCollection().get(14).getWheelchairAccessible() == true);
  }

  @Test
  void testEqualsSame() {
    for (Row row : testTheater.getRowCollection()) {
      int count = 1;
      assertTrue(row.equals(row));
      count++;
    }
  }

  @Test
  void testEqualsSameFields() {
    assertTrue(testTheater.getRowCollection().get(1).equals(otherTheater.getRowCollection().get(1)));
  }

  @Test
  void testEqualsDiffNameSameRows() {
    newTheater = new Theater("Our Theater", 15, 26, 3);
    newTheater.buildTheater();
    assertTrue(testTheater.getRowCollection().get(14).equals(otherTheater.getRowCollection().get(14)));
  }

  @Test
  void testEqualsDiffRows() {
    newTheater = new Theater("Roxy", 15, 10, 3);
    newTheater.buildTheater();
    assertFalse(testTheater.getRowCollection().get(1).equals(newTheater.getRowCollection().get(1)));
  }

  @Test
  void testEqualsDiffSeats() {
    newTheater = new Theater("Roxy", 15, 5, 3);
    newTheater.buildTheater();
    assertFalse(testTheater.getRowCollection().get(1).equals(newTheater.getRowCollection().get(1)));
  }

  @Test
  void testEqualsDiffWAccessibleSeats() {
    newTheater = new Theater("Roxy", 15, 13, 2);
    newTheater.buildTheater();
    assertFalse(testTheater.getRowCollection().get(13).equals(newTheater.getRowCollection().get(13)));
  }
  @Test
  void testEqualsDiffWAccessible() {
    newTheater = new Theater("Roxy", 15, 26, 1);
    newTheater.buildTheater();
    assertFalse(testTheater.getRowCollection().get(13).equals(newTheater.getRowCollection().get(13)));
  }

  @Test
  void testEqualsDiffObjects() {
    newTheater = new Theater("Roxy", 15, 26, 1);
    newTheater.buildTheater();
    assertFalse(testTheater.getRowCollection().get(13).equals(newTheater));
  }

  @Test
  void testEqualsNull() {
    newTheater = new Theater("Roxy", 15, 26, 1);
    assertFalse(testTheater.getRowCollection().get(13).equals(newTheater));
  }


  @Test
  void testHashCode() {
    for (Row row : testTheater.getRowCollection()) {
      assertTrue(row.hashCode() == row.hashCode());
    }
  }

  @Test
  void testHashCodeDiffEverything() {
    newTheater = new Theater("Our Theater", 15, 2, 1);
    newTheater.buildTheater();
    for (Row row : testTheater.getRowCollection()) {
      int count = 0;
      assertFalse(row.hashCode() == newTheater.getRowCollection().get(count).hashCode());
      count++;
    }
  }

  @Test
  void testHashCodeDiffClasses() {
    Row row = testTheater.getRowCollection().get(1);
      assertFalse(row.hashCode() == otherTheater.hashCode());
  }

  @Test
  void testToString() {
    newTheater = new Theater("Roxy", 3, 5, 3);
    newTheater.buildTheater();
    Row test = newTheater.getRowCollection().get(1);
    assertEquals(test.toString(), "Row{rowNumber=2, wheelchairAccessible=true, numberOfSeats=5}");
  }
}