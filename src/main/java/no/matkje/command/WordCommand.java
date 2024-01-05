package no.matkje.command;

import no.matkje.gameServer.GameLogic;
import no.matkje.message.ApprovedMessage;
import no.matkje.message.DeniedMessage;
import no.matkje.message.Message;

public class WordCommand extends Command{

  private final String word;

  public WordCommand(String word) {
    this.word = word;
  }

  @Override
  public Message execute(GameLogic logic) {
    if (logic.checkWord(word)) {
      return new ApprovedMessage("Valid word");
    } else {
      return new DeniedMessage("Word does not fit");
    }

  }
}
