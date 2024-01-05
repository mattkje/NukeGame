package no.matkje.command;

import no.matkje.gameServer.GameLogic;
import no.matkje.message.Message;

/**
 * A command sent from the client to the server .
 */
public abstract class Command implements Message {
  /**
   * Execute the command.
   *
   * @param logic The Game logic to be affected by this command
   * @return The message which contains the output of the command
   */
  public abstract Message execute(GameLogic logic);
}
