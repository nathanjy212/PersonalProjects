package problem1;

import static java.lang.System.console;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for ReservationService.
 * Not possible to test the main function here, tested equals, hashcode, toString.
 */
class ReservationServiceTest {

  Theater testTheater = new Theater("Roxy", 15, 26, 3);
  Theater otherTheater = new Theater("Other", 10, 13, 2);
  Theater newTheater;
  ReservationService testR;
  ReservationService testOtherR;


  @BeforeEach
  void setUp() {
    testTheater.buildTheater();
    otherTheater.buildTheater();
    testR = new ReservationService(testTheater);
  }

  @Test
  void testEquals() {
    assertTrue(testR.equals(testR));
  }

  @Test
  void testEqualsSameFields() {
    testOtherR = new ReservationService(testTheater);
    assertTrue(testR.equals(testOtherR));
  }

  @Test
  void testEqualsDiffTheaters() {
    testOtherR = new ReservationService(otherTheater);
    assertFalse(testR.equals(testOtherR));
  }

  @Test
  void testEqualsNull() {
    assertFalse(testR.equals(newTheater));
  }

  @Test
  void testEqualsDiffObjects() {
    assertFalse(testR.equals(otherTheater));
  }

  @Test
  void testHashCode() {
    assertTrue(testR.hashCode() == testR.hashCode());
  }

  @Test
  void testHashCodeDiff() {
    testOtherR = new ReservationService(otherTheater);
    assertFalse(testR.hashCode() == testOtherR.hashCode());
  }

  @Test
  void testToString() {
    assertEquals(testR.toString(), "ReservationService{theater=Theater{name='Roxy', rowCollection=[Row{rowNumber=1, wheelchairAccessible=false, numberOfSeats=26}, Row{rowNumber=2, wheelchairAccessible=false, numberOfSeats=26}, Row{rowNumber=3, wheelchairAccessible=false, numberOfSeats=26}, Row{rowNumber=4, wheelchairAccessible=false, numberOfSeats=26}, Row{rowNumber=5, wheelchairAccessible=false, numberOfSeats=26}, Row{rowNumber=6, wheelchairAccessible=false, numberOfSeats=26}, Row{rowNumber=7, wheelchairAccessible=false, numberOfSeats=26}, Row{rowNumber=8, wheelchairAccessible=false, numberOfSeats=26}, Row{rowNumber=9, wheelchairAccessible=false, numberOfSeats=26}, Row{rowNumber=10, wheelchairAccessible=false, numberOfSeats=26}, Row{rowNumber=11, wheelchairAccessible=false, numberOfSeats=26}, Row{rowNumber=12, wheelchairAccessible=false, numberOfSeats=26}, Row{rowNumber=13, wheelchairAccessible=true, numberOfSeats=26}, Row{rowNumber=14, wheelchairAccessible=true, numberOfSeats=26}, Row{rowNumber=15, wheelchairAccessible=true, numberOfSeats=26}], numberOfRows=15, numberOfSeats=26, numberOfWheelChairAccessibleRows=3}}");
  }
}