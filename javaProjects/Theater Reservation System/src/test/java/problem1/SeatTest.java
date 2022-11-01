package problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SeatTest {
  Seat openSeat = new Seat("A", true);
  Seat reservedSeat = new Seat("A", "Reserved", true);
  Theater otherTheater = new Theater("Roxy", 15, 26, 3);
  Seat newSeat;

  @Test
  void getName() {
    assertEquals(openSeat.getName(), "A");
  }

  @Test
  void getReservedFor() {
    assertEquals(reservedSeat.getReservedFor(), "Reserved");
  }

  @Test
  void getWheelchairAccessible() {
    assertTrue(openSeat.getWheelchairAccessible());
  }

  @Test
  void setName() {
    openSeat.setName("B");
    assertEquals(openSeat.getName(), "B");
  }

  @Test
  void setReservedFor() {
    openSeat.setReservedFor("Me");
    assertEquals(openSeat.getReservedFor(), "Me");
  }

  @Test
  void setWheelchairAccessible() {
    openSeat.setWheelchairAccessible(false);
    assertEquals(openSeat.getWheelchairAccessible(), false);
  }

  @Test
  void testEqualsSame() {
    assertTrue(openSeat.equals(openSeat));
  }

  @Test
  void testEqualsSameFields() {
    newSeat = new Seat("A", "Reserved", true);;
    assertTrue(reservedSeat.equals(newSeat));
  }

  @Test
  void testEqualsDiffName() {
    newSeat = new Seat("B", "Reserved", true);;
    assertFalse(reservedSeat.equals(newSeat));
  }

  @Test
  void testEqualsDiffReservedName() {
    newSeat = new Seat("A", "No", true);;
    assertFalse(reservedSeat.equals(newSeat));
  }

  @Test
  void testEqualsDiffWheelchairAccessible() {
    newSeat = new Seat("A", "Reserved", false);;
    assertFalse(reservedSeat.equals(newSeat));
  }

  @Test
  void testEqualsDiffObjects() {
    assertFalse(reservedSeat.equals(otherTheater));
  }

  @Test
  void testEqualsDiffNull() {
    assertFalse(reservedSeat.equals(newSeat));
  }

  @Test
  void testHashCode() {
    assertTrue(reservedSeat.hashCode() == reservedSeat.hashCode());
  }

  @Test
  void testHashCode_Diff() {
    assertFalse(reservedSeat.hashCode() == openSeat.hashCode());
  }

  @Test
  void testToString() {
    assertEquals(reservedSeat.toString(), "Seat{name='A', reservedFor='Reserved', wheelchairAccessible=true}");
  }
}