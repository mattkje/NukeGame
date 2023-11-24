package no.matkje.tools;

/**
 * A logger class for encapsulating all the logging. We can either reduce the number of SonarLint
 * warnings, or implement it properly. This class makes sure we sue the same logging in all
 * places of our code.
 */
public class Logger {

  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_WHITE = "\u001B[0m";
  public static final String ANSI_RED = "\u001B[31m";

  /**
   * Not allowed to create an instance of this class.
   */
  private Logger() {
  }

  /**
   * Log an information message.
   *
   * @param message The message to log. A newline is appended automatically.
   */
  public static void info(String message) {
    System.out.println(message);
  }

  /**
   * Log an info message without appending a newline to the log.
   *
   * @param message The message to log
   */
  public static void infoNoNewline(String message) {
    System.out.print(message);
  }

  /**
   * Log an error message.
   *
   * @param message The error message to log
   */
  public static void error(String message) {
    System.err.println(message);
  }

  /**
   * Log a request message.
   *
   * @param message The message to log. A newline is appended automatically.
   */
  public static void request(String message) {
    int fixedLength = 25;
    String formattedMessage = String.format("%-" + fixedLength + "s", message);
    formattedMessage += " >>> ";
    System.out.print(formattedMessage);
  }


  /**
   * Log an information message in cursive.
   *
   * @param message The message to log. A newline is appended automatically.
   */
  public static void acceptMessage(String message) {
    System.out.println(ANSI_GREEN + message + ANSI_WHITE);
  }

  /**
   * Log an information message in cursive.
   *
   * @param message The message to log. A newline is appended automatically.
   */
  public static void deniedMessage(String message) {
    System.out.println(ANSI_RED + message + ANSI_WHITE);
  }

  /**
   * Log an information message in cursive.
   *
   * @param message The message to log. A newline is appended automatically.
   */
  public static void help(String message) {
    System.out.println("\033[3m" + message + "\033[0m");
  }

}
