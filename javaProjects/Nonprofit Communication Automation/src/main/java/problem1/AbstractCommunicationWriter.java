package problem1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * this is an abstract class that represents an abstract communication writer
 */
public abstract class AbstractCommunicationWriter implements ICommunicationWriter{

  protected Map<String, String> commandLineOptions;
  protected List<HashMap> supporterList;



  /**
   * Constructor for an AbstractCommunicationWriter.
   * @param commandLineOptions a map of commandLine operations, a Map of a string and a  String.
   * @param supporterList a List of Hashmaps for each row of a csv File, an ArrayList of Hashmaps.
   */
  public AbstractCommunicationWriter(Map<String, String> commandLineOptions,
      List<HashMap> supporterList) {
    this.commandLineOptions = commandLineOptions;
    this.supporterList = supporterList;
  }

  /**
   * getter for commandLineOptions
   * @return commandLineOptions, as a Map with key value strings and value strings
   */
  public Map<String, String> getCommandLineOptions() {
    return commandLineOptions;
  }

  /**
   * getter for SupporterList
   * @return the supporterList as a List of hashmaps
   */
  public List<HashMap> getSupporterList() {
    return supporterList;
  }

  /**
   * This is the method to test equals
   * @param o - this is the object that we can pass in to check for equality, represented by an
   *          object
   * @return returns a true or false based on whether the test is passed, as a boolean
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AbstractCommunicationWriter that = (AbstractCommunicationWriter) o;
    return Objects.equals(commandLineOptions, that.commandLineOptions)
        && Objects.equals(supporterList, that.supporterList);
  }

  /**
   * this is a hashcode method to test for equality of the hashcode
   * @return returns a hash value of the sequence of input values
   */
  @Override
  public int hashCode() {
    return Objects.hash(commandLineOptions, supporterList);
  }

  /**
   * this method is used to return a string representation of an object
   * @return - returns the representation of the object, as a String
   */
  @Override
  public String toString() {
    return "AbstractCommunicationWriter{" +
        "commandLineOptions=" + commandLineOptions +
        ", supporterList=" + supporterList +
        '}';
  }
}
