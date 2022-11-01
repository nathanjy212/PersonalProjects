package problem1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Row class is an arraylist of seat objects, it contains its boolean value on whether it is wheelchair
 * accessible, the rowNumber, and the number of seats.
 */
public class Row extends ArrayList<Seat> {
  private Integer rowNumber;
  private Boolean wheelchairAccessible;
  private Integer numberOfSeats;

  /**
   * Constructs an empty list with the specified initial capacity.
   * @param initialCapacity - the initial capacity of the list, an Integer.
   * @param rowNumber - the row number of the list, an Integer.
   * @param wheelchairAccessible - whether the seats in the list are wheelchair accessible, a Boolean.
   */
  public Row(int initialCapacity, Integer rowNumber, Boolean wheelchairAccessible) {
    super(initialCapacity);
    this.rowNumber = rowNumber;
    this.wheelchairAccessible = wheelchairAccessible;
    this.numberOfSeats = initialCapacity;
  }

  /**
   * Getter for the rowNumber of a row object.
   * @return - returns the row number of the row, an Integer.
   */
  public Integer getRowNumber() {
    return rowNumber;
  }

  /**
   * Getter for the wheelchairAccessible value of a row object.
   * @return - returns the wheelchairAccessible value of the row, a Boolean value.
   */
  public Boolean getWheelchairAccessible() {
    return wheelchairAccessible;
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
    if (!super.equals(o)) {
      return false;
    }
    Row seats = (Row) o;
    return Objects.equals(rowNumber, seats.rowNumber) && Objects.equals(
        wheelchairAccessible, seats.wheelchairAccessible) && Objects.equals(numberOfSeats,
        seats.numberOfSeats);
  }

  /**
   * Hashcode method that assigns an integer unique to an object.
   * @return an integer unique to an object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), rowNumber, wheelchairAccessible, numberOfSeats);
  }

  /**
   * ToString method is a method that returns a string representation of an object.
   * @return a string representation of a theater.
   */
  @Override
  public String toString() {
    return "Row{" +
        "rowNumber=" + rowNumber +
        ", wheelchairAccessible=" + wheelchairAccessible +
        ", numberOfSeats=" + numberOfSeats +
        '}';
  }
}


