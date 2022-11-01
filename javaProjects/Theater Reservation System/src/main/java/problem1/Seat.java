package problem1;

import java.util.Objects;

/**
 * Seat class contains the information necessary to construct a Seat object which contains the seat
 * name, its reserved name (if one exists, else it will initialize as null), and whether it
 * is wheelchair accessible.
 */
public class Seat {

  private String name;
  private String reservedFor;

  private Boolean wheelchairAccessible;

  /**
   * First constructor for an empty seat object, will construct when a theater is built.
   * @param name - requires a seat name, a String.
   * @param wheelchairAccessible - requires the reservation name, a String.
   *
   * Will also be updated with wheelchair accessibility upon construction.
   */
  public Seat(String name, Boolean wheelchairAccessible) {
    this.name = name;
    this.reservedFor = null;
    this.wheelchairAccessible = wheelchairAccessible;
  }

  /**
   * Second constructor for a seat object, in case a pre-made reservation exists/want to replace the
   * seat with one with a reservation name.
   * @param name - requires a seat name, a String.
   * @param reservedFor - requires a person's name whom the seat is reserved for
   * @param wheelchairAccessible - requires the reservation name, a String.
   *
   * Will also be updated with wheelchair accessibility upon construction.
   */
  public Seat(String name, String reservedFor, Boolean wheelchairAccessible) {
    this.name = name;
    this.reservedFor = reservedFor;
    this.wheelchairAccessible = wheelchairAccessible;
  }

  /**
   * Getter for the seat name.
   * @return - the name of the seat, a String.
   */
  public String getName() {
    return name;
  }

  /**
   * Getter for the reservation name for the seat.
   * @return - the reservation name for the seat, a String.
   */
  public String getReservedFor() {
    return reservedFor;
  }

  /**
   * Getter for the wheelchair accessibility information for the seat.
   * @return - returns the wheelchair accessibility information for the seat, a boolean.
   */
  public Boolean getWheelchairAccessible() {
    return wheelchairAccessible;
  }

  /**
   * Setter for the seat name.
   * @param name - requires the name of the seat, a String.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Setter for the reservation name for the seat.
   * @param reservedFor - requires the reservation name for the seat, a String.
   */
  public void setReservedFor(String reservedFor) {
    this.reservedFor = reservedFor;
  }

  /**
   * Setter for the wheelchair accessibility information for the seat.
   * @param wheelchairAccessible - requires the wheelchair accessibility information for the seat, a boolean.
   */
  public void setWheelchairAccessible(Boolean wheelchairAccessible) {
    this.wheelchairAccessible = wheelchairAccessible;
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
    Seat seat = (Seat) o;
    return Objects.equals(name, seat.name) && Objects.equals(reservedFor,
        seat.reservedFor) && Objects.equals(wheelchairAccessible,
        seat.wheelchairAccessible);
  }

  /**
   * Hashcode method that assigns an integer unique to an object.
   * @return an integer unique to an object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(name, reservedFor, wheelchairAccessible);
  }

  /**
   * ToString method is a method that returns a string representation of an object.
   * @return a string representation of a theater.
   */
  @Override
  public String toString() {
    return "Seat{" +
        "name='" + name + '\'' +
        ", reservedFor='" + reservedFor + '\'' +
        ", wheelchairAccessible=" + wheelchairAccessible +
        '}';
  }
}


