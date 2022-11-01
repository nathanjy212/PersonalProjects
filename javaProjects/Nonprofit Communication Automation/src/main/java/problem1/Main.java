package problem1;

import java.io.FileNotFoundException;

/**
 * this is the main class that brings all the various classes and parts of the program
 * together to read, parse commands, process CSV and finally generate the emails and
 * letter communications
 */
public class Main {

  /**
   * Main function that brings all the different parts of the program together
   * @param args takes in the input from the user from the command line, in the form
   *             of an array of strings
   */
  public static void main(String[] args) throws FileNotFoundException {
    // We first create the command line parser to create
    // the validated hashmap
    CommandLineParser parser = new CommandLineParser(args);

    // Now we process the CSV file
    // The end product is an array list of Maps
    // or is a list of supporters with their field already separated and ready to go
    CSVProcessor supporters = new CSVProcessor(parser.getOptions());
    // this generates a List of Hashmaps which is the List of supporters
    supporters.readSupporters();

    if (parser.getOptions().containsKey("email")) {
      Email emailWriter = new Email(parser.getOptions(), supporters.getSupporterList());
      emailWriter.writeCommunication();
    }
    if (parser.getOptions().containsKey("letter")) {
      Letter letterWriter = new Letter(parser.getOptions(), supporters.getSupporterList());
      letterWriter.writeCommunication();
    }
  }
}
