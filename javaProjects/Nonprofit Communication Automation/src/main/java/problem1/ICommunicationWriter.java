package problem1;

import java.io.FileNotFoundException;

/**
 * this is an interface for a communication writer
 */
public interface ICommunicationWriter {

  /**
   * Method that opens a writing template, replaces the fields
   * with csv data fields, and writes the new output into a specified
   * output directory.
   * @throws FileNotFoundException will throw an exception if the
   * file is not found.
   */
  public void writeCommunication() throws FileNotFoundException;

}
