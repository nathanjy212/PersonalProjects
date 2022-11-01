package problem1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * this is a class representing an email
 */
public class Email extends AbstractCommunicationWriter{


  private static final Pattern PATTERN = Pattern.compile("\\[\\[(.*?)\\]\\]");


  /**
   * Constructor for an Email object.
   * @param commandLineOptions a map of commandLine operations, a Map as a String, String.
   * @param supporterList a List of Hashmaps for each row of a csv File, an ArrayList of Hashmaps.
   */
  public Email(Map<String, String> commandLineOptions, List<HashMap> supporterList) {
    super(commandLineOptions, supporterList);
  }

  @Override
  public String toString() {
    return "Email{" +
        "commandLineOptions=" + commandLineOptions +
        ", supporterList=" + supporterList +
        '}';
  }

  /**
   * Method that opens an email template, replaces the fields
   * with csv data fields, and writes the new output into a specified
   * output directory.
   * @throws FileNotFoundException will throw an exception if the
   * file is not found.
   */
  @Override
  public void writeCommunication() throws FileNotFoundException {
    // Need to start at index 1, because index 0 is a map of the column titles
    for (int i = 1; i < this.supporterList.size(); i++) {
      try {
        File myObj = new File(this.commandLineOptions.get("output"), (String) supporterList.get(i).get("first_name") + "_email_" + i + ".txt");
        myObj.createNewFile();
        PrintStream fileStream = new PrintStream(myObj);
        Scanner input = new Scanner(System.in);
        File file = new File(this.commandLineOptions.get("email"));
        input = new Scanner(file);
        while (input.hasNextLine()) {
          String line = input.nextLine();
          //System.out.println(line);
          Matcher regexMatcher = PATTERN.matcher(line);
          String replacedString = line;
          while (regexMatcher.find()) {
            String one = regexMatcher.group(1);//Finds Matching Pattern in String
            replacedString = replacedString.replace((CharSequence) ("[["+one+"]]"),
                (CharSequence) ((supporterList.get(i)).get(one)));
          }
          fileStream.println(replacedString);
        }
        input.close();
      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
    }
  }
}
