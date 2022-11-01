package problem1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Class containing the methods necessary to open, read through a csv data file,
 * and generate an array list of maps for each row of the csv file.
 * The class will open and read through a CSV file of NonProfit Supporters, process the file,
 * and extract the necessary information.
 */
public class CSVProcessor {
  private Map<String, String> commandLineOptions;
  // HashMap of the column titles (key = number of rows, value = column title)
  private HashMap<Integer, String> supporterFields;
  private final String DELIMITER = "(\",\")";
  // List of HashMaps, one HashMap/Supporter
  private List<HashMap> supporterList;

  /**
   * Constructor for the CSVProcessor. Initializes an empty arraylist of supporterField hashmaps,
   * and an empty HashMap of supporterFields (CSV Column titles).
   */
  public CSVProcessor(HashMap<String, String> commandLineOptions) {
    this.commandLineOptions = commandLineOptions;
    this.supporterFields = new HashMap<>();
    this.supporterList = new ArrayList();
  }

  /**
   * Method that creates a titleMap of the column titles (Key = Integer/Index, Value = column title)
   * @param line requires an input line, the first line of the CSV file.
   */
  private void createTitleMap(String line) {
    String[] title = line.split(DELIMITER);
    int length = title.length;
    if (length == 0) {
      System.out.println("File may not be formatted correctly. Please check.");
      System.exit(0);
    }
    int i;
    for (i = 0; i < length; i++) {
      // Removes the first and last parenthesis accidentally captured
      title[i] = title[i].replaceAll("\"", "");
      this.supporterFields.put(i, title[i]);
    }
  }

  /**
   * Method that creates a SupporterMap based on the CSV row being read
   * (Key = the column title, a String, Value = the supporter's information, a String)
   * @param line requires an input line, the current row being read.
   */
  private void createSupporterMap(String line) {
    HashMap supporterInfo = new HashMap<String, String>();
    String[] fields = line.split(DELIMITER);
    int length = fields.length;
    int i;
    // Putting the supporters' info into their own map Key = field title
    for (i = 0; i < length; i++) {
      fields[i] = fields[i].replaceAll("\"", "");
      if (fields[i].length() == 0) {
        fields[i] = "No Information On File";
      }
      supporterInfo.put(this.supporterFields.get(i), fields[i]);
    }
    this.supporterList.add(supporterInfo);
  }

  /**
   * A method that opens, reads through a csv file and then will call helper
   * methods to extract and store the relevant data from the csv file.
   */
  public void readSupporters() {
    BufferedReader inputFile = null;
    try {
      inputFile = new BufferedReader(new FileReader(this.commandLineOptions.get("recipients")));
      String line;
      int count = 0;

      while ((line = inputFile.readLine()) != null) {
        if (count == 0) {
          this.createTitleMap(line);
          count += 1;
        }
        this.createSupporterMap(line);
      }
    } catch (
        FileNotFoundException e) {
      throw new InvalidArgumentException("File was not found!" + e.getMessage());
    } catch (IOException ioe) {
      throw new InvalidArgumentException("Something went wrong when reading the file." + ioe.getMessage());
    }
    if (inputFile == null) {
      try {
        inputFile.close();
      } catch (IOException ioe) {
        System.out.println("Failed to close the file." + ioe.getMessage());
        ioe.printStackTrace();
      }
    }
  }


  /**
   * Getter for the supporter fields, a HashMap of column titles.
   * @return returns a HashMap.
   */
  public HashMap<Integer, String> getSupporterFields() {
    return this.supporterFields;
  }

  /**
   * Getter for the SupporterListMap.
   * @return A list of Hashmaps, one for each supporter.
   */
  public List<HashMap> getSupporterList() {
    return this.supporterList;
  }

  /**
   * A method that compares two objects to see if the fields are the same.
   * @param o requires an input object to compare.
   * @return returns a boolean true or false.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CSVProcessor that = (CSVProcessor) o;
    return Objects.equals(supporterFields, that.supporterFields)
        && Objects.equals(DELIMITER, that.DELIMITER) && Objects.equals(
        supporterList, that.supporterList);
  }

  /**
   * A method that sees if two objects are the same.
   * @return an integer value unique to the input.
   */
  @Override
  public int hashCode() {
    return Objects.hash(supporterFields, DELIMITER, supporterList);
  }

  /**
   * A method that generates a string representation of the object.
   * @return a string representation of the object.
   */
  @Override
  public String toString() {
    return "CSVProcessor{" +
        "supporterFields=" + supporterFields +
        ", supporterList=" + supporterList +
        '}';
  }
}
