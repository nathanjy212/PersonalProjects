package problem1;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;

/**
 * this represents a class that parses and validates the command from the user that is input
 * from the command line
 */
public class CommandLineParser {

  public static final int MIN_ARGS = 5;
  private HashMap<String, String> options;

  /**
   * this is a constructor for the command line parser
   * @param args - takes in the args, which are the commands given by the user as an array of
   *             strings
   */
  public CommandLineParser(String[] args) {
    this.options = new HashMap<>();
    validateArgs(args);
    fillMap(args);
    validateMap();
  }

  /**
   * this is a getter to get the options hashmap
   * @return a hashmap of the possible expected commands in the form of a Hashmap where the key
   * is a string and the value is also a string
   */
  public HashMap<String, String> getOptions() {
    return options;
  }

  /**
   * this method validates the length of the args which should be a minimum of 6
   * @param args
   */
  private static void validateArgs(String[] args){
    if (args.length > MIN_ARGS){
      return;
    } else {
      throw new InvalidArgumentException("option flags required!");
    }
  }

  /**
   * this is a method to ensure that the paths that is input from the command line is valid
   * @param path takes in path from the command line, in the form of a String
   */
  private void validatePaths(String path){

    File current = new File(path);
    if (current.exists()){
      return;
    } else throw new RuntimeException("Invalid path.");
  }

  /**
   * this method basically creates the options map by filling it with the appropriate keys and
   * values
   * @param args - takes in the args from the command line as an array of Strings
   */
  private void fillMap(String[] args) {
    int i;
    for (i = 0; i < args.length; i++) {
      switch (args[i]) {
        case OptionFlags.EMAIL_TEMPLATE:
          i++;
          validatePaths(args[i]);
          this.options.put("email", args[i]);
          break;
        case OptionFlags.LETTER_TEMPLATE:
          i++;
          validatePaths(args[i]);
          this.options.put("letter", args[i]);
          break;
        case OptionFlags.OUTPUT_DIR:
          i++;
          //validatePaths(args[i]);
          this.options.put("output", args[i]);
          break;
        case OptionFlags.CSV_FILE:
          i++;
          validatePaths(args[i]);
          this.options.put("recipients", args[i]);
          break;
        default:
          throw new InvalidArgumentException(args[i] + " is not a valid argument!"
              + "The following commands are necessary to run the program:"
              + "First argument: '--email-template' or '--letter-template'"
              + "Second argument: ' <path/to/file>'"
              + "Third argument: ' --output-dir emails' or ' --output-dir letters'"
              + "Fourth argument: ' <path/to/folder>'"
              + "Fifth argument: ' --csv-file'"
              + "Sixth argument: ' <path/to/file>'"
              + ""
              + "Example: '--email --email-template email-template.txt --output-dir emails --csv-file customers.csv'");
      }
    }
  }

  /**
   * this is another check for the map to ensure that it passes all the requirements as set forth
   * by the customer
   */
  private void validateMap(){
    if (this.options.containsKey("output") &&
        !this.options.get("output").isEmpty() &&
        this.options.containsKey("recipients") &&
        !this.options.get("recipients").isEmpty() &&
        (this.options.containsKey("letter") &&
            !this.options.get("letter").isEmpty() ||
            this.options.containsKey("email") &&
                !this.options.get("email").isEmpty())){
      return;
    } else {
      throw new InvalidArgumentException("Missing required files.");
    }
  }

  /**
   * this method is used to return a string representation of an object
   * @return - returns the representation of the object, as a String
   */
  @Override
  public String toString() {
    return "CommandLineParser{" +
        "options=" + options +
        '}';
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
    CommandLineParser that = (CommandLineParser) o;
    return Objects.equals(options, that.options);
  }

  /**
   * this is a hashcode method to test for equality of the hashcode
   * @return returns a hash value of the sequence of input values
   */
  @Override
  public int hashCode() {
    return Objects.hash(options);
  }
}
