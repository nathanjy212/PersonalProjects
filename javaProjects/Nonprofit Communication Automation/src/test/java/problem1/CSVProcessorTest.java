package problem1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CSVProcessorTest {
  private String emailTemplate = "email_template.txt";
  private String letterTemplate = "letter_template.txt";
  private String output = "test_output";
  private String csv = "supporters.csv";
  private String input2 = "supporter_empty_field.csv";
  private String falseCSV = "nope.csv";
  private String emptyFile = "empty_test.csv";



  private String[] args = {OptionFlags.EMAIL_TEMPLATE, emailTemplate, OptionFlags.LETTER_TEMPLATE, letterTemplate, OptionFlags.OUTPUT_DIR, output, OptionFlags.CSV_FILE, csv};
  private String[] args2 = {OptionFlags.EMAIL_TEMPLATE, emailTemplate, OptionFlags.LETTER_TEMPLATE, letterTemplate, OptionFlags.OUTPUT_DIR, output, OptionFlags.CSV_FILE, input2};
  private String[] argsWithEmptyFile = {OptionFlags.EMAIL_TEMPLATE, emailTemplate, OptionFlags.LETTER_TEMPLATE, letterTemplate, OptionFlags.OUTPUT_DIR, output, OptionFlags.CSV_FILE,
      emptyFile};
  private String[] argsFalseCSV = {OptionFlags.EMAIL_TEMPLATE, emailTemplate, OptionFlags.LETTER_TEMPLATE, letterTemplate, OptionFlags.OUTPUT_DIR, output, OptionFlags.CSV_FILE, falseCSV};

  private CommandLineParser cli = new CommandLineParser(args);
  private CommandLineParser cli2 = new CommandLineParser(args2);
  private CSVProcessor newSP = new CSVProcessor(cli.getOptions());
  private CSVProcessor newSP2;
  private HashMap<Integer, String> testHash;
  private List<HashMap> testList;

  @BeforeEach
  void setUp() {
    // Using the parser to setup the test path --> this is the path that will instantiate the processor
    newSP.readSupporters();
  }

  @Test
  void readSupporters() {
    newSP2 = new CSVProcessor(cli.getOptions());
    newSP2.readSupporters();
    System.out.println(newSP2.getSupporterList().get(3).get("city"));
  }
  @Test
  void readSupportersOneEmptyField() {
    newSP2 = new CSVProcessor(cli2.getOptions());
    newSP2.readSupporters();
  }

  @Test
  void readSupporters_InvalidFile() {
    HashMap<String, String> commandLineOptions = new HashMap<>();
    commandLineOptions.put("recipients", "csvNotFound.txt");
    commandLineOptions.put("output", "~");
    newSP2 = new CSVProcessor(commandLineOptions);
    assertThrows(InvalidArgumentException.class, ()-> newSP2.readSupporters());
  }

  @Test
  void getSupporterFields_First() {
    testHash = new HashMap<>();
    testHash =  newSP.getSupporterFields();
    System.out.println(testHash);
    assertTrue(testHash.get(0).equals("first_name"));
  }

  @Test
  void getSupporterFields_Last() {
    testHash = new HashMap<>();
    testHash =  newSP.getSupporterFields();
    assertTrue(testHash.get(11).equals("web"));
  }

  @Test
  void getSupporterFields_Middle() {
    testHash = new HashMap<>();
    testHash =  newSP.getSupporterFields();
    assertTrue(testHash.get(5).equals("county"));
  }

  @Test
  void getSupporterList_FirstName() {
    testList = new ArrayList();
    testList =  newSP.getSupporterList();
    assertTrue(testList.get(4).get("first_name").equals("Lenna"));
  }

  @Test
  void getSupporterList_Empty() {
    testHash = new HashMap<>();
    testList = new ArrayList();
    newSP2 = new CSVProcessor(cli2.getOptions());
    newSP2.readSupporters();
    testHash =  newSP2.getSupporterFields();
    testList =  newSP2.getSupporterList();
    System.out.println(testHash);
    System.out.println(testHash.get(3));
    System.out.println(testHash);
    System.out.println(testList.get(1));
    assertEquals(testList.get(1).get("county"), "No Information On File");
  }

  @Test
  void getSupporterList_NotEmpty() {
    testHash = new HashMap<>();
    testList = new ArrayList();
    newSP = new CSVProcessor(cli.getOptions());
    newSP.readSupporters();
    testHash =  newSP.getSupporterFields();
    testList =  newSP.getSupporterList();
    System.out.println(testHash);
    System.out.println(testHash.get(3));
    System.out.println(testList.get(1));
    assertEquals(testList.get(1).get("last_name"), "Butt");
  }
  @Test
  void getSupporterList_Address() {
    testList = new ArrayList();
    testList =  newSP.getSupporterList();
    assertTrue(testList.get(5).get("address").equals("34 Center St"));
  }

  @Test
  void getSupporterList_State() {
    testList = new ArrayList();
    testList =  newSP.getSupporterList();
    assertTrue(testList.get(8).get("state").equals("CA"));
  }

  @Test
  void getSupporterList_web() {
    testList = new ArrayList();
    testList =  newSP.getSupporterList();
    assertTrue(testList.get(8).get("web").equals("http://www.commercialpress.com"));
  }
  @Test
  void HashCode() {
    assertTrue(newSP.hashCode() == newSP.hashCode());
  }

  @Test
  void HashCode_False_Diff() {
    assertFalse(newSP.hashCode() == cli.hashCode());
  }
  @Test
  void String() {
    assertEquals(newSP.toString(), "CSVProcessor{" +
        "supporterFields=" +
        newSP.getSupporterFields().toString() + ", supporterList=" +
        newSP.getSupporterList().toString() +
        '}');
  }


  @Test
  void Equals_Same() {
    assertTrue(newSP.equals(newSP));
  }

  @Test
  void Equals_SameFields() {
    newSP2 = new CSVProcessor(cli.getOptions());
    newSP2.readSupporters();
    assertTrue(newSP.equals(newSP2));
  }

  @Test
  void Equals_DifferentFile() {
    newSP2 = new CSVProcessor(cli2.getOptions());
    newSP2.readSupporters();
    assertFalse(newSP.equals(newSP2));
  }

  @Test
  void Equals_DifferentObjects() {
    assertFalse(newSP.equals(cli));
  }

  @Test
  void Equals_Different() {
    testHash = new HashMap<>();
    testHash =  newSP.getSupporterFields();
    assertFalse(newSP.equals(testHash));
  }

  @Test
  void Equals_Null() {
    assertFalse(newSP.equals(newSP2));
  }


}