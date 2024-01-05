package no.matkje.gameServer;

import java.util.ArrayList;
import java.util.List;

public class GameLogic {

  private Player currentPlayer;
  private String prompt;
  private List<Player> players;

  public GameLogic() {
    this.currentPlayer = currentPlayer;
    this.players = new ArrayList<>();
    prompt = "";
  }


  public void setCurrentPlayer(Player player) {
    currentPlayer = player;
  }

  /**
   * This method should check if the prompt matches the word.
   *
   * @param word Given word.
   * @return True if word is valid, false otherwise.
   */
  public boolean checkWord(String word) {
    return (word.contains(prompt));
  }

  /**
   * This method should add player info of a player to the game.
   *
   * @param player The player which is connecting to the server.
   * @return True if player is able to join, false otherwise.
   */
  public boolean assignPlayer(Player player) {
    if (players.contains(player)) {
      return false;
    } else {
      players.add(player);
      return true;
    }
  }

  /**
   * This method should remove a player from the game.
   *
   * @param player The leaving player.
   * @return True if player is able to leave, false otherwise.
   */
  public boolean disconnectPlayer(Player player) {
    if (!players.contains(player)) {
      return false;
    } else {
      players.remove(player);
      return true;
    }
  }
}
