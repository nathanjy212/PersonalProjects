package problem1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LetterTest {

  @BeforeEach
  void setUp() {
  }

  @Test
  void writeCommunication() {
    HashMap<String, String> commandLineOptions = new HashMap<>();
    commandLineOptions.put("letter", "letter_template.txt");
    commandLineOptions.put("output", "test_output");
    commandLineOptions.put("recipients", "supporters.csv");

    CSVProcessor supporters = new CSVProcessor(commandLineOptions);
    supporters.readSupporters();
    Letter testLetter = new Letter(commandLineOptions, supporters.getSupporterList());
    try {
      testLetter.writeCommunication();
    } catch (Exception e) {
    }
  }

  @Test
  void getCommandLineOptions() {
    HashMap<String, String> commandLineOptions = new HashMap<>();
    commandLineOptions.put("letter", "letter_template.txt");
    commandLineOptions.put("output", "test_output");
    HashMap<String, String> supporter = new HashMap<>();
    supporter.put("email", "a@google.com");
    supporter.put("first_name", "Nathan");
    supporter.put("last_name", "Yap");
    List<HashMap> supporters = new ArrayList<>();
    supporters.add(supporter);
    Letter testLetter = new Letter(commandLineOptions, supporters);
    assertEquals(testLetter.getCommandLineOptions(), commandLineOptions);
  }

  @Test
  void getSupporterList() {
    HashMap<String, String> commandLineOptions = new HashMap<>();
    commandLineOptions.put("letter", "letter_template.txt");
    commandLineOptions.put("output", "test_output");
    HashMap<String, String> supporter = new HashMap<>();
    supporter.put("email", "a@google.com");
    supporter.put("first_name", "Nathan");
    supporter.put("last_name", "Yap");
    List<HashMap> supporters = new ArrayList<>();
    supporters.add(supporter);
    Letter testLetter = new Letter(commandLineOptions, supporters);
    assertEquals(testLetter.getSupporterList(), supporters);
  }

  @Test
  void testEquals_Null() {
    HashMap<String, String> commandLineOptions = new HashMap<>();
    commandLineOptions.put("letter", "letter_template.txt");
    commandLineOptions.put("output", "test_output");
    HashMap<String, String> supporter = new HashMap<>();
    supporter.put("email", "a@google.com");
    supporter.put("first_name", "Nathan");
    supporter.put("last_name", "Yap");
    List<HashMap> supporters = new ArrayList<>();
    supporters.add(supporter);
    Letter testLetter = new Letter(commandLineOptions, supporters);
    assertFalse(testLetter.equals(null));
  }

  @Test
  void testHashCode() {
    HashMap<String, String> commandLineOptions = new HashMap<>();
    commandLineOptions.put("letter", "letter_template.txt");
    commandLineOptions.put("output", "test_output");
    HashMap<String, String> supporter = new HashMap<>();
    supporter.put("email", "a@google.com");
    supporter.put("first_name", "Nathan");
    supporter.put("last_name", "Yap");
    List<HashMap> supporters = new ArrayList<>();
    supporters.add(supporter);
    Letter testLetter = new Letter(commandLineOptions, supporters);
    Letter testLetterTwo = new Letter(commandLineOptions, supporters);
    assertEquals(testLetter.hashCode(), testLetterTwo.hashCode());
  }

  @Test
  void testToString() {
    HashMap<String, String> commandLineOptions = new HashMap<>();
    commandLineOptions.put("letter", "letter_template.txt");
    commandLineOptions.put("output", "test_output");
    HashMap<String, String> supporter = new HashMap<>();
    supporter.put("email", "a@google.com");
    supporter.put("first_name", "Nathan");
    supporter.put("last_name", "Yap");
    List<HashMap> supporters = new ArrayList<>();
    supporters.add(supporter);
    Letter testLetter = new Letter(commandLineOptions, supporters);
    String expectedString = "Letter{commandLineOptions={output=test_output, letter=letter_template.txt}, supporterList=[{last_name=Yap, first_name=Nathan, email=a@google.com}]}";
    assertEquals(testLetter.toString(), expectedString);
  }

}