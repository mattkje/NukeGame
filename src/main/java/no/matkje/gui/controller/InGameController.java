package no.matkje.gui.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import no.matkje.gameClient.GameClientSocket;
import no.matkje.gameServer.Player;
import no.matkje.gui.guiTools.CircularPane;
import no.matkje.gui.guiTools.PlayerBox;

public class InGameController {

  @FXML
  private Scene scene;
  @FXML
  private CircularPane circularPane;
  @FXML
  private Label prompt;
  @FXML
  private ImageView arrow;
  private GameClientSocket socket;
  private Map<Integer, PlayerBox> playerBoxMap;
  public void setScene(Scene scene) {
    this.scene = scene;
  }
  public void setSocket(GameClientSocket socket) {
    this.socket = socket;
  }

  public void goHome() throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/no/matkje/fxml/mainMenu.fxml"));
    Parent root = fxmlLoader.load();

    MainMenuController controller = fxmlLoader.getController();
    controller.setScene(scene);
    controller.setSocket(socket);

    scene.setRoot(root);
  }

  @FXML
  private TextField textField;

  @FXML
  public void handleKeyTyped() {
    textField.textProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue != null) {
        textField.setText(newValue.toUpperCase());
      }
    });
  }

  public void spawnPlayer(Player player) {
    PlayerBox playerBox = new PlayerBox(player);

    if (playerBoxMap == null) {
      playerBoxMap = new HashMap<>();
    }

    playerBoxMap.put(player.getId(), playerBox);
    circularPane.getChildren().add(playerBox);
  }


  public void updatePlayerState(int playerId, int health) {
    playerBoxMap.get(playerId).updatePlayer(health);
  }

  public void setPrompt(String promptText) {
    prompt.setText(promptText);
  }

  public void pointArrow(int playerId) {
    arrow.setVisible(true);
    int numOfPositions = playerBoxMap.size();

    if (numOfPositions > 0) {
      if (playerBoxMap.containsKey(playerId)) {
        int playerIndex = 1;
        for (Map.Entry<Integer, PlayerBox> entry : playerBoxMap.entrySet()) {
          if (entry.getKey() == playerId) {
            double targetAngle = 360.0 * playerIndex / numOfPositions - 35;

            Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1),
                    new KeyValue(arrow.rotateProperty(), targetAngle)
                )
            );
            timeline.play();
            return;
          }
          playerIndex++;
        }
      } else {
        arrow.setRotate(0);
      }
    } else {
      arrow.setRotate(0);
    }
  }


  /**
   * Debug methods below
   */
  public void createDummyPlayers() {
    Player player1 = new Player("Player 1", new Image("/no/matkje/media/noSelect.png"), 2,1);
    Player player2 = new Player("Player 2", new Image("/no/matkje/media/noSelect.png"), 3,2);
    Player player3 = new Player("Player 3", new Image("/no/matkje/media/noSelect.png"), 1,3);
    spawnPlayer(player1);
    spawnPlayer(player2);
    spawnPlayer(player3);

  }

  public void dummyPlayerUpdates() {
    updatePlayerState(1,0);
    updatePlayerState(2, 2);
    updatePlayerState(3, 5);

    dummyArrowMovement();
  }

  public void dummyArrowMovement() {
    pointArrow(1);
    pointArrow(2);
    pointArrow(3);
  }

}
