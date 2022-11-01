package problem1;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Theater class containing the fields necessary to set up, build, and update a theater.
 * A theater object contains rows, an arraylist of seats as well as its name.
 *
 * The theater maximum capacity is arbitrary and can be set based upon the venue in question.
 */
public class Theater {

  private static final Integer START = 0;
  private static final Integer MAX_SEAT_CAPACITY = 500;
  private static final Integer MIN_SEATS_ROWS = 1;
  private static final Integer HALF = 2;
  private static final Integer SPACING = 10;
  private static final Integer NA_FILLED = -1;
  private static final Integer NO_SEATS = 0;
  private static final Integer LETTERS_IN_ALPHA = 26;
  private static final String[] ALPHABET = {"A", "B", "C", "D", "E", "F", "G",
      "H", "I", "J", "K", "L", "M", "N",
      "O", "P", "Q", "R", "S", "T", "U",
      "V", "W", "X", "Y", "Z"};
  public static final int EMPTY = 0;
  public static final int ADJUST = 1;

  private String name;
  private ArrayList<Row> rowCollection;
  private Integer numberOfRows;
  private Integer numberOfSeats;
  private Integer numberOfWheelChairAccessibleRows;

  /**
   * Constructor for a Theater object containing rows of seats, a name, and the number of wheelchair
   * rows.
   * @param name - the name of the theater, a String.
   * @param numberOfRows - the number of rows, an Integer.
   * @param numberOfSeats - the number of seats, an Integer.
   * @param numberOfWheelChairAccessibleRows - the number of wheelchair accessible rows, an Integer.
   */
  public Theater(String name, Integer numberOfRows,
      Integer numberOfSeats, Integer numberOfWheelChairAccessibleRows) {
    this.name = name;
    this.rowCollection = new ArrayList<Row>();
    this.numberOfRows = numberOfRows;
    this.numberOfSeats = numberOfSeats;
    this.numberOfWheelChairAccessibleRows = numberOfWheelChairAccessibleRows;
  }

  /**
   * ReturnAlphabet is a method that will use an alphabet list and the seat number to return
   * the corresponding letter (or double/triple/etc) letter for the seat name (ie Seat 27 will be AA).
   * @param seat requires an input seat number, an Integer.
   * @return returns the String name for the seat, a String.
   */
  private String returnAlphabet(int seat) {
    int letterValue = seat % LETTERS_IN_ALPHA;
    String letter = ALPHABET[letterValue];
    String returnAlpha = "";
    int multiplier = (int) Math.ceil(seat / LETTERS_IN_ALPHA);
    for (int i = START; i <= multiplier; i++) {
      returnAlpha = returnAlpha.concat(letter);
    }
    return returnAlpha;
  }

  /**
   * Prebuild check will ensure the theater about to be built meets the
   * requirements of a theater.
   *
   * If the theater does not meet specifications, then the system will exit.
   */
  private void preBuildCheck() {
    if (this.numberOfWheelChairAccessibleRows == EMPTY) {
      System.out.println("You cannot have a theater with no wheelchair"
          + "accessible rows.");
      System.exit(0);
    }
    if (this.numberOfSeats > MAX_SEAT_CAPACITY) {
      System.out.println("You cannot have a theater with more than 50 seats."
          + "That is too large for your facility! Please rebuild and try"
          + "again.");
      System.exit(0);
    }
    if (this.numberOfSeats < MIN_SEATS_ROWS || this.numberOfRows < MIN_SEATS_ROWS) {
      System.out.println("You must have at least one seat per row and at least one row in"
          + "your theater. Please rebuild and try again.");
      System.exit(0);
    }
  }

  /**
   * A private helper method that builds rows that are non-wheelchair accessible.
   * @return - will return the current count of built rows.
   */
  private int buildNonAccessibleRows() {
    int count = START;
    for (int i = START; i < this.numberOfRows - this.numberOfWheelChairAccessibleRows; i++) {
      Row newRow = new Row(this.numberOfSeats, (i+ADJUST), false);
      // now we build a row of seats
      for (int j = START; j < this.numberOfSeats; j++) {
        // we create a seat
        String seatName = returnAlphabet(j);
        Seat newSeat = new Seat(seatName, false);
        // we insert this seat into the row array list
        newRow.add(newSeat);
      }
      // then we add this row into the theater's list of rows
      this.rowCollection.add(newRow);
      count++;
    }
    return count;
  }

  /**
   * A private helper method that builds rows that are wheelchair accessible.
   * @param rowsBuilt requires an input of the current number of rows that are built.
   */
  private void buildWheelchairRows(int rowsBuilt) {
    int count = rowsBuilt;
    for (int i = START; i < this.numberOfWheelChairAccessibleRows; i++) {
      Row newRow = new Row(this.numberOfSeats, (count + ADJUST), true);
      for (int j = START; j < this.numberOfSeats; j++) {
        String seatName = returnAlphabet(j);
        Seat newSeat = new Seat(seatName, true);
        // we insert this seat into the row array list
        newRow.add(newSeat);
      }
      this.rowCollection.add(newRow);
      count++;
    }
  }

  /**
   * Method that builds the theater, including all non wheelchair accessible and wheelchair
   * accessible rows. Will also call helper methods to ensure that the theater specifications meet
   * the requirements before and after the build.
   */
  public void buildTheater() {
    this.preBuildCheck();
    int count = this.buildNonAccessibleRows();
    this.buildWheelchairRows(count);
  }

  /**
   * Method that displays a string representation of the theater when called.
   * @return - returns a 1 when complete.
   */
  public int printTheater() {
    System.out.println(this.name + " Theater Seating\n");
    System.out.println("[[[[   SCREEN   ]]]]");
    for (Row row : this.rowCollection) {
      System.out.print(row.getRowNumber());
      if ((row.getRowNumber()) < SPACING) {
        System.out.print(" ");
      }
      System.out.print(" ");
      for (Seat seat : row) {
        if (seat.getReservedFor() == null && row.getWheelchairAccessible() == false) {
          System.out.print("_ ");
        } else if (seat.getReservedFor() == null && row.getWheelchairAccessible() == true) {
          System.out.print("= ");
        } else {
          System.out.print("X ");
        }
      }
      System.out.println();
    }
    System.out.println("|| Entrance || Exit ||\n");
    return START;
  }

  /**
   * Helper function to make sure you can reserve the appropriate number of seats.
   * If so, the appropriate number will be reserved.
   * @param reservedNumberSeats - requires the intended reserved number of seats, an Integer.
   * @param answerName - requires the reservation name, a String.
   * @param row - requires the current row from the main method, an Integer.
   * @return - returns the row of the reservation for the main method to continue.
   */
  private int checkToReserve(int reservedNumberSeats, String answerName, int row) {
    int middle = (int) Math.floor((this.numberOfRows - this.numberOfWheelChairAccessibleRows) / HALF);
    int reserve = START;
    // now, we want to make sure we can reserve the seats "reservedNumberSeats" times
    for (int k = START; k < reservedNumberSeats; k++) {
      // we need this while loop, if the seat is taken, move to next seat and check
      while (this.rowCollection.get(middle - row).get(reserve).getReservedFor() != null) {
        reserve++;
      }
      // once an empty seat is found, we exit the while loop and we go to this if statement
      if (this.rowCollection.get(middle - row).get(reserve).getReservedFor() == null) {
        this.rowCollection.get(middle - row).get(reserve).setReservedFor(answerName);
        reserve++;
      }
    }
    return (middle - row);
  }

  /**
   * Helper function to make sure you can reserve the appropriate number of seats.
   * If so, the appropriate number will be reserved. This will reserve the seats
   * closer to the back end of the theater if the first front is filled. Essentially,
   * we need this method to appropriately rank the rows
   * @param reservedNumberSeats - requires the intended reserved number of seats, an Integer.
   * @param answerName - requires the reservation name, a String.
   * @param row - requires the current row from the main method, an Integer.
   * @return - returns the row of the reservation for the main method to continue.
   */
  private int checkToReserveBack(int reservedNumberSeats, String answerName, int row) {
    int middle = (int) Math.floor((this.numberOfRows - this.numberOfWheelChairAccessibleRows) / HALF);
    int reserve = START;
    // now, we want to make sure we can reserve the seats "reservedNumberSeats" times
    for (int k = START; k < reservedNumberSeats; k++) {
      // we need this while loop, if the seat is taken, move to next seat and check
      while (this.rowCollection.get(middle + row).get(reserve).getReservedFor() != null) {
        reserve++;
      }
      // once an empty seat is found, we exit the while loop and we go to this if statement
      if (this.rowCollection.get(middle + row).get(reserve).getReservedFor() == null) {
        this.rowCollection.get(middle + row).get(reserve).setReservedFor(answerName);
        reserve++;
      }
    }
    return (middle + row);
  }

  /**
   * A method that will update non-wheelchair accessible rows for a theater reservation.
   * @param reservedNumberSeats - requires the intended number of seats for reservation, an Integer.
   * @param answerName - requires the reservation name, a String.
   * @return will return an Integer (positive number = row of the reservation to be reported to
   * the user, or a negative number indicating wheelchair rows need to be checked for seats for this
   * reservation).
   */
  public int updateTheaterNoWheelchair(int reservedNumberSeats, String answerName) {
    int middle = (int) Math.floor((this.numberOfRows - this.numberOfWheelChairAccessibleRows) /HALF);
    for (int i = START; i <= (middle); i++) {
      int count = START;
      int countTwo = START;
      // first we check how many empty seats are there in this row
      for (int j = START; j < this.numberOfSeats; j++) {
        if (this.rowCollection.get(middle - i).get(j)
            .getReservedFor() == null) {
          count++;
        }
      }
      if (count >= reservedNumberSeats) {
        return checkToReserve( reservedNumberSeats, answerName, i);
      }

      for (int j = START; j < this.numberOfSeats; j++) {
        if (this.rowCollection.get(middle + i).get(j)
            .getReservedFor() == null) {
          countTwo++;
        }
      }
      if (countTwo >= reservedNumberSeats) {
        return checkToReserveBack( reservedNumberSeats, answerName, i);
      }
    }
    System.out.println("Sorry we don't have that many seats together for you.");
    return NA_FILLED;
  }

  /**
   * A method that will update wheelchair accessible rows for a theater reservation.
   * @param reservedNumberSeats - requires the intended number of seats for reservation, an Integer.
   * @param answerName - requires the reservation name, a String.
   * @return will return an Integer (non-zero number = row of the reservation to be reported to
   * the user.
   *
   * A zero return indicated the theater is completely filled for a reservation of this size.
   */
  public int updateTheaterWheelchair(int reservedNumberSeats, String answerName) {
    int wheelChairStart = this.numberOfRows - this.numberOfWheelChairAccessibleRows;
    for (int i = START; i < this.numberOfWheelChairAccessibleRows; i++) {
      int count = START;
      for (int j = START; j < this.numberOfSeats; j++) {
        if (this.rowCollection.get(((wheelChairStart + i))).get(j).getReservedFor() == null) {
          count++;
        }
      }
      if (count >= reservedNumberSeats) {
        int reserve = START;
        for (int k = START; k < reservedNumberSeats; k++) {
          while (this.rowCollection.get(((wheelChairStart + i))).get(reserve).getReservedFor() != null) {
            reserve++;
          }
          if (this.rowCollection.get(((wheelChairStart + i)))
              .get(reserve).getReservedFor() == null) {
            this.rowCollection.get(((wheelChairStart + i))).get(reserve).setReservedFor(answerName);
            reserve++;
          }
        }
        return (((wheelChairStart + i)));
      }
    }
    System.out.println("Sorry we don't have that many wheelchair accessible seats together for you.");
    return NO_SEATS;
  }

  /**
   * Getter for the theater rowCollection.
   * @return the theater rowCollection, an Arraylist of Seats.
   */
  public ArrayList<Row> getRowCollection() {
    return rowCollection;
  }

  /**
   * Getter for the theater name.
   * @return the name of the theater, a String.
   */
  public String getName() {
    return name;
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
    Theater theater = (Theater) o;
    return Objects.equals(name, theater.name) && Objects.equals(rowCollection,
        theater.rowCollection) && Objects.equals(numberOfRows, theater.numberOfRows)
        && Objects.equals(numberOfSeats, theater.numberOfSeats) && Objects.equals(
        numberOfWheelChairAccessibleRows, theater.numberOfWheelChairAccessibleRows);
  }

  /**
   * Hashcode method that assigns an integer unique to an object.
   * @return an integer unique to an object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(name, rowCollection, numberOfRows, numberOfSeats,
        numberOfWheelChairAccessibleRows);
  }

  /**
   * ToString method is a method that returns a string representation of an object.
   * @return a string representation of a theater.
   */
  @Override
  public String toString() {
    return "Theater{" +
        "name='" + name + '\'' +
        ", rowCollection=" + rowCollection +
        ", numberOfRows=" + numberOfRows +
        ", numberOfSeats=" + numberOfSeats +
        ", numberOfWheelChairAccessibleRows=" + numberOfWheelChairAccessibleRows +
        '}';
  }
}