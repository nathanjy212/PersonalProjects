package problem1;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * A Main class called the reservation system, that will call the necessary builds to run the
 * reservation service with a theater. When the user exits the service, the quit boolean
 * will trigger the program to exit and a print statement will display to the user.
 */
public class ReservationSystem {

  /**
   * Main method that takes an input of string arguments from a user.
   * @param args - requires an input of string arguments (will not be used).
   */
  public static void main(String[] args) {
    // Theater for the build, must have > 1 row and seat and > 1 wheelchair accessible row
    // can have up to 500 seats (or more if the max capacity is adjusted) otherwise program will quit
    // This is where you can adjust the fields of a theater
    Theater theaterOne = new Theater("Roxy", 15, 26, 2);
    theaterOne.buildTheater();
    boolean runSystem = true;
    ReservationService reservationServiceOne = new ReservationService(theaterOne);
    while (runSystem == true) {
      runSystem = reservationServiceOne.interactSystem();
    }
    System.out.println("Thank you! Have a nice day!");
  }
}

