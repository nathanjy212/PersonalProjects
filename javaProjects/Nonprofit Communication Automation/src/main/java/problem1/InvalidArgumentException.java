package problem1;

/**
 * this is a class specifically created to provide an exception for any invalid arguments
 */
public class InvalidArgumentException extends RuntimeException {

  /**
   * this is the constructor to create a message that informs the user that the input argument
   * is invalid
   * @param message - message that has been designed to tell the user about the invalid argument
   */
  public InvalidArgumentException(String message) {
    super(message);
  }

}
