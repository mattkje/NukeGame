package no.matkje.message;

import no.matkje.command.ConnectCommand;
import no.matkje.command.DisconnectCommand;
import no.matkje.command.IgnoreCommand;
import no.matkje.command.WordCommand;

public class MessageSerializer {
  public static final String CONNECT_COMMAND = "1";
  public static final String DISCONNECT_COMMAND = "0";
  public static final String WORD_COMMAND = "w";

  /**
   * Create message from a string, according to the communication protocol.
   *
   * @param s The string sent over the communication channel
   * @return The logical message, as interpreted according to the protocol
   */
  public static Message fromString(String s) {
    if (s.isEmpty() || s.equals("null")) {
      return new IgnoreCommand();
    }
    char firstS = s.charAt(0);
    return switch (firstS) {
      case '1' -> new ConnectCommand(s);
      case '0' -> new DisconnectCommand(s);
      case 'w' -> new WordCommand(s);
      default -> new IgnoreCommand();
    };
  }

  /**
   * Returns the command as a string.
   *
   * @param m message to be sent
   * @return command as message
   */
  public static String toString(Message m) {
    String s = null;
    if (m instanceof ConnectCommand) {
      s = CONNECT_COMMAND;
    } else if (m instanceof DisconnectCommand) {
      s = DISCONNECT_COMMAND;
    } else if (m instanceof WordCommand) {
      s = WORD_COMMAND;
    }
    return s;
  }
}