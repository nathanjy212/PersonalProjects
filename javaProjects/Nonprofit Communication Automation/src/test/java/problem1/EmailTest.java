package problem1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmailTest {

  @BeforeEach
  void setUp() {
  }

  @Test
  void writeCommunicationTwo() {
    HashMap<String, String> commandLineOptions = new HashMap<>();
    commandLineOptions.put("email", "email_template.txt");
    commandLineOptions.put("output", "test_output");
    commandLineOptions.put("recipients", "supporters.csv");
    CSVProcessor supporters = new CSVProcessor(commandLineOptions);
    supporters.readSupporters();
    Email testEmail = new Email(commandLineOptions, supporters.getSupporterList());
    try {
      testEmail.writeCommunication();
    } catch (Exception e) {

    }
  }

  @Test
  void getCommandLineOptions() {
    HashMap<String, String> commandLineOptions = new HashMap<>();
    commandLineOptions.put("email", "email_template.txt");
    commandLineOptions.put("output", "test_output");
    HashMap<String, String> supporter = new HashMap<>();
    supporter.put("email", "a@google.com");
    supporter.put("first_name", "Nathan");
    supporter.put("last_name", "Yap");
    List<HashMap> supporters = new ArrayList<>();
    supporters.add(supporter);
    Email testEmail = new Email(commandLineOptions, supporters);
    assertEquals(testEmail.getCommandLineOptions(), commandLineOptions);
  }

  @Test
  void getSupporterList() {
    HashMap<String, String> commandLineOptions = new HashMap<>();
    commandLineOptions.put("email", "email_template.txt");
    commandLineOptions.put("output", "test_output");
    HashMap<String, String> supporter = new HashMap<>();
    supporter.put("email", "a@google.com");
    supporter.put("first_name", "Nathan");
    supporter.put("last_name", "Yap");
    List<HashMap> supporters = new ArrayList<>();
    supporters.add(supporter);
    Email testEmail = new Email(commandLineOptions, supporters);
    assertEquals(testEmail.getSupporterList(), supporters);
  }

  @Test
  void testEquals_Null() {
    HashMap<String, String> commandLineOptions = new HashMap<>();
    commandLineOptions.put("email", "email_template.txt");
    commandLineOptions.put("output", "test_output");
    HashMap<String, String> supporter = new HashMap<>();
    supporter.put("email", "a@google.com");
    supporter.put("first_name", "Nathan");
    supporter.put("last_name", "Yap");
    List<HashMap> supporters = new ArrayList<>();
    supporters.add(supporter);
    Email testEmail = new Email(commandLineOptions, supporters);
    assertFalse(testEmail.equals(null));
  }

  @Test
  void testEquals_DifferentObject() {
    HashMap<String, String> commandLineOptions = new HashMap<>();
    commandLineOptions.put("email", "email_template.txt");
    commandLineOptions.put("output", "test_output");
    HashMap<String, String> supporter = new HashMap<>();
    supporter.put("email", "a@google.com");
    supporter.put("first_name", "Nathan");
    supporter.put("last_name", "Yap");
    List<HashMap> supporters = new ArrayList<>();
    supporters.add(supporter);
    Email testEmail = new Email(commandLineOptions, supporters);
    assertFalse(testEmail.equals("testEmail"));
  }

  @Test
  void testEquals_SameFields() {
    HashMap<String, String> commandLineOptions = new HashMap<>();
    commandLineOptions.put("email", "email_template.txt");
    commandLineOptions.put("output", "test_output");
    HashMap<String, String> supporter = new HashMap<>();
    supporter.put("email", "a@google.com");
    supporter.put("first_name", "Nathan");
    supporter.put("last_name", "Yap");
    List<HashMap> supporters = new ArrayList<>();
    supporters.add(supporter);
    Email testEmail = new Email(commandLineOptions, supporters);
    Email testEmailTwo = new Email(commandLineOptions, supporters);
    assertEquals(testEmail, testEmailTwo);
  }

  @Test
  void testEquals_DifferentFields() {
    HashMap<String, String> commandLineOptions = new HashMap<>();
    commandLineOptions.put("email", "email_template.txt");
    commandLineOptions.put("output", "test_output");
    HashMap<String, String> supporter = new HashMap<>();
    supporter.put("email", "a@google.com");
    supporter.put("first_name", "Nathan");
    supporter.put("last_name", "Yap");
    List<HashMap> supporters = new ArrayList<>();
    supporters.add(supporter);
    Email testEmail = new Email(commandLineOptions, supporters);
    HashMap<String, String> commandLineOptionsTwo = new HashMap<>();
    commandLineOptions.put("email", "email_template.txt");
    commandLineOptions.put("output", "~");
    HashMap<String, String> supporterTwo = new HashMap<>();
    supporter.put("email", "fun@google.com");
    supporter.put("first_name", "Jason");
    supporter.put("last_name", "Tan");
    List<HashMap> supportersTwo = new ArrayList<>();
    Email testEmailTwo = new Email(commandLineOptions, supportersTwo);
    assertFalse(testEmail.equals(testEmailTwo));
  }

  @Test
  void testHashCode() {
    HashMap<String, String> commandLineOptions = new HashMap<>();
    commandLineOptions.put("email", "email_template.txt");
    commandLineOptions.put("output", "test_output");
    HashMap<String, String> supporter = new HashMap<>();
    supporter.put("email", "a@google.com");
    supporter.put("first_name", "Nathan");
    supporter.put("last_name", "Yap");
    List<HashMap> supporters = new ArrayList<>();
    supporters.add(supporter);
    Email testEmail = new Email(commandLineOptions, supporters);
    Email testEmailTwo = new Email(commandLineOptions, supporters);
    assertEquals(testEmail.hashCode(), testEmailTwo.hashCode());
  }

  @Test
  void testToString() {
    HashMap<String, String> commandLineOptions = new HashMap<>();
    commandLineOptions.put("email", "email_template.txt");
    commandLineOptions.put("output", "test_output");
    HashMap<String, String> supporter = new HashMap<>();
    supporter.put("email", "a@google.com");
    supporter.put("first_name", "Nathan");
    supporter.put("last_name", "Yap");
    List<HashMap> supporters = new ArrayList<>();
    supporters.add(supporter);
    Email testEmail = new Email(commandLineOptions, supporters);
    String expectedString = "Email{commandLineOptions={output=test_output, email=email_template.txt}, supporterList=[{last_name=Yap, first_name=Nathan, email=a@google.com}]}";
    assertEquals(expectedString, testEmail.toString());
  }

}