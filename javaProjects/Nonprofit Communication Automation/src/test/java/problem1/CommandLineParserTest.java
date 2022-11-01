
package problem1;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.InvalidPathException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommandLineParserTest {

  private String emailTemplate = "email_template.txt";
  private String letterTemplate = "letter_template.txt";
  private String output = "parser_output/";
  private String csv = "supporters.csv";


  private String[] args = {OptionFlags.EMAIL_TEMPLATE, emailTemplate, OptionFlags.LETTER_TEMPLATE, letterTemplate, OptionFlags.OUTPUT_DIR, output, OptionFlags.CSV_FILE, csv};
  private String[] tooFewArgs = {OptionFlags.EMAIL_TEMPLATE, OptionFlags.OUTPUT_DIR, output, OptionFlags.CSV_FILE, csv};
  private String[] argWithBadPath = {OptionFlags.EMAIL_TEMPLATE, "bruhh", OptionFlags.LETTER_TEMPLATE, letterTemplate, OptionFlags.OUTPUT_DIR, output, OptionFlags.CSV_FILE, csv};
  private String[] argsWithBadCommand = {"--email", emailTemplate, OptionFlags.LETTER_TEMPLATE, letterTemplate, OptionFlags.OUTPUT_DIR, output, OptionFlags.CSV_FILE, csv};
  private String[] argsWithMissingOutput = {OptionFlags.EMAIL_TEMPLATE, emailTemplate, OptionFlags.LETTER_TEMPLATE, letterTemplate, OptionFlags.CSV_FILE, csv};
  private String[] argsWithMissingCSV = {OptionFlags.EMAIL_TEMPLATE, emailTemplate, OptionFlags.LETTER_TEMPLATE, letterTemplate, OptionFlags.OUTPUT_DIR, output};

  private CommandLineParser cli;


  @BeforeEach
  void setUp() {
    cli = new CommandLineParser(args);
  }

  @Test
  void full_args() {

    System.out.println(cli.toString());
    String expectedString = "CommandLineParser{options={output=parser_output/, recipients=supporters.csv,"
        + " letter=letter_template.txt, email=email_template.txt}}";
    assertEquals(expectedString, cli.toString());
  }
  @Test
  void full_notEnoughArgs() {
    assertThrows(InvalidArgumentException.class, ()-> new CommandLineParser(tooFewArgs));
  }

  @Test
  void fileDoesNotExist() {
    assertThrows(RuntimeException.class, ()-> new CommandLineParser(argWithBadPath));
  }

  @Test
  void InvalidCommand() {
    assertThrows(InvalidArgumentException.class, ()-> new CommandLineParser(argsWithBadCommand));
  }

  @Test
  void missingRequiredOutput() {
    assertThrows(InvalidArgumentException.class, ()-> new CommandLineParser(argsWithMissingOutput));
  }
  @Test
  void missingRequiredCSV() {
    assertThrows(InvalidArgumentException.class, ()-> new CommandLineParser(argsWithMissingCSV));
  }

  @Test
  void testEquals_Null() {
    assertFalse(cli.equals(null));
  }

  @Test
  void testEquals_DifferentObject() {
    assertFalse(cli.equals("cli"));
  }

  @Test
  void testEquals_SameFields() {
    cli = new CommandLineParser(args);
    CommandLineParser cliTwo = new CommandLineParser(args);
    assertEquals(cli, cliTwo);
  }

  @Test
  void testHashCode() {
    cli = new CommandLineParser(args);
    CommandLineParser cliTwo = new CommandLineParser(args);
    assertEquals(cli.hashCode(), cliTwo.hashCode());
  }
}