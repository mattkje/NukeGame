package no.matkje.command;

import javafx.scene.image.Image;
import no.matkje.gameServer.GameLogic;
import no.matkje.gameServer.Player;
import no.matkje.message.ApprovedMessage;
import no.matkje.message.DeniedMessage;
import no.matkje.message.Message;

public class ConnectCommand extends Command{

  private final String playerString;

  public ConnectCommand(String playerString) {
    this.playerString = playerString;
  }

  @Override
  public Message execute(GameLogic logic) {
    Player player = stringToPlayer(playerString);

    if (logic.assignPlayer(player)) {
      return new ApprovedMessage("Player " + player.getName() + " successfully added to the game");
    } else {
      return new DeniedMessage("Something went wrong while getting player info");
    }

  }

  private Player stringToPlayer(String playerString) {
    //TODO: DO SOME SPLITTING
    
    int id = 0;
    int health = 0;
    String name = "";
    Image image = new Image("");
    return new Player.PlayerBuilder().id(id)
        .health(health)
        .name(name)
        .image(image)
        .build();

  }
}
