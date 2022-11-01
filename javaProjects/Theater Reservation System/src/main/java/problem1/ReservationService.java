package problem1;

import java.util.Objects;
import java.util.Scanner;

/**
 * A class representing a reservation service. Contains the theater and the console the user
 * interacts with to make the reservation.
 * Will not exit unless the key word "done" is entered.
 */
public class ReservationService {

  private static final Integer RESERVE_ARGUMENT_MIN = 2;
  private static final Integer ROW_MIN = 0;
  private static final String REGEX = "\\s+";

  private static final String[] OPTIONS = {"reserve", "show", "done", "yes"};
  private static final String[] QUESTIONS = {"What do you want to do?", "Do you need wheelchair accessibility seats?",
      "What's your name?"};
  private static final String[] STATEMENTS = {"Please make sure you enter 'reserve + numberOfSeats'. Thank you!",
      "Thank you!", "Hello, all of our non-wheelchair accessible front rows are filled,"
      + "so we will try to find seats for your party in the wheelchair accessible rows."
      + "\nOne moment please....", "I'm sorry, I don't think that was a valid command. Please try again."};
  public static final int ADJUST = 1;
  public static final int OPTION_TWO = 1;
  public static final int OPTION_FOUR = 3;
  public static final int OPTION_THREE = 2;
  public static final int OPTION_ONE = 0;

  private Theater theater;

  /**
   * ReservationService constructor, requires a successfully built theater for use.
   * @param theater - requires a theater object, an object of the class Theater.
   */
  public ReservationService(Theater theater) {
    this.theater = theater;
  }

  /**
   * Private helper method for reserve seat that prints a message to the user.
   * @param row - requires the row for reserve, an integer.
   * @param seats - requires the number of seats reserved, an Integer.
   * @param name - requires the reservation name, a String.
   */
  private void successfulReserve (int row, int seats, String name) {
    System.out.println("I've reserved " + seats + " seats for you at the " +
        this.theater.getName() + " in row " + (row + ADJUST) + ", " + name);

    System.out.println("\n" + this.theater.getName() + " Theater Reservation Receipt");
    System.out.println("Reservation Name: " + name);
    System.out.println("Row of reservation: " + (row + ADJUST));
    System.out.println("Number of seats: " + seats);

    System.out.println(STATEMENTS[OPTION_TWO] + "\n");
  }

  /**
   * Private helper method utilized by interactSystem to reserve a seat when the key word is entered by the user.
   * @param console - requires the console in use from the user.
   * @param reservedNumberSeats - requires the number of seats being attempted for reservation,
   *                            a String.
   */
  private void reserveSeat(Scanner console, int reservedNumberSeats) {
    System.out.println(QUESTIONS[OPTION_TWO]);
    String wheelchairNeed = console.nextLine();

    if (wheelchairNeed.equalsIgnoreCase(OPTIONS[OPTION_FOUR])) {
      System.out.println(QUESTIONS[OPTION_THREE]);
      String answerName = console.nextLine();
      int rowNumber = this.theater.updateTheaterWheelchair(reservedNumberSeats, answerName);
      if (rowNumber >= ROW_MIN) {
        successfulReserve(rowNumber, reservedNumberSeats, answerName);
      }} else {
      System.out.println(QUESTIONS[OPTION_THREE]);
      String answerName = console.nextLine();
      int rowNumber = this.theater.updateTheaterNoWheelchair(reservedNumberSeats, answerName);
      if (rowNumber >= ROW_MIN) {
        successfulReserve(rowNumber, reservedNumberSeats, answerName);
      } else {
        System.out.println(STATEMENTS[OPTION_THREE]);
        int rowNumberTwo = this.theater.updateTheaterWheelchair(reservedNumberSeats, answerName);
        if (rowNumberTwo > ROW_MIN) {
          successfulReserve(rowNumberTwo, reservedNumberSeats, answerName);
        }
      }
    }
  }

  /**
   * Method interactSystem is a method that will allow the user to continuously interact with the
   * theater system to make reservations if the correct commands are entered.
   * @return will return a boolean.
   */
  public boolean interactSystem() {
    Scanner console = new Scanner(System.in);
    boolean quit = false;

    while(quit == false) {
      System.out.println(QUESTIONS[OPTION_ONE]);
      String answer = console.nextLine().toLowerCase();
      if (answer.contains(OPTIONS[OPTION_ONE])) {
        String[] splitAnswer = answer.split(REGEX);
        if (splitAnswer.length < RESERVE_ARGUMENT_MIN) {
          System.out.println(STATEMENTS[OPTION_ONE]);
        } else {
          int reservedNumberSeats = Integer.parseInt(splitAnswer[OPTION_TWO]);
          reserveSeat(console, reservedNumberSeats);
      }} else if (answer.contains(OPTIONS[OPTION_TWO])) {
        this.theater.printTheater();
      } else if (answer.contains(OPTIONS[OPTION_THREE])) {
        quit = true;
      } else {
        System.out.println(STATEMENTS[OPTION_FOUR]);
      }
    }
    return false;
  }

  /**
   * Equals method to determine if two objects have equivalent fields.
   * @param o requires an input object for comparison.
   * @return a boolean, true if equivalent and false if not.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReservationService that = (ReservationService) o;
    return Objects.equals(theater, that.theater);
  }

  /**
   * Hashcode method that assigns an integer unique to an object.
   * @return an integer unique to an object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(theater);
  }

  /**
   * ToString method is a method that returns a string representation of an object.
   * @return a string representation of a theater.
   */
  @Override
  public String toString() {
    return "ReservationService{" +
        "theater=" + theater +
        '}';
  }
}