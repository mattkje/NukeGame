package no.matkje.gui.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import no.matkje.gameClient.GameClientSocket;
import no.matkje.gameServer.Player;
import no.matkje.tools.CircularPane;

public class InGameController {

  @FXML
  private Scene scene;
  @FXML
  private CircularPane circularPane;
  private FXMLLoader fxmlLoader;
  private Parent root;
  private GameClientSocket socket;
  private Map<Player, VBox> playerMap = new HashMap<>();

  public void setScene(Scene scene) {
    this.scene = scene;
  }
  public void setSocket(GameClientSocket socket) {
    this.socket = socket;
  }

  public void goHome() throws IOException {
    fxmlLoader = new FXMLLoader(getClass().getResource("/no/matkje/fxml/mainMenu.fxml"));
    root = fxmlLoader.load();

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

  public void createDummyPlayers() {
    Player player1 = new Player("Player 1", new Image("/no/matkje/media/noSelect.png"), 2,1);
    Player player2 = new Player("Player 2", new Image("/no/matkje/media/noSelect.png"), 3,2);
    Player player3 = new Player("Player 3", new Image("/no/matkje/media/noSelect.png"), 1,3);

    addPlayer(player1);
    addPlayer(player2);
    addPlayer(player3);
  }

  public void addPlayer(Player player) {
    VBox vBox = new VBox();
    vBox.setAlignment(Pos.CENTER);
    vBox.setMaxWidth(0);
    vBox.setMaxHeight(0);
    Label nameLabel = new Label(player.getName());
    nameLabel.setFont(new Font("System Bold", 17.0));
    nameLabel.setTextFill(Color.WHITE);

    ImageView playerImage = new ImageView(player.getImage());
    playerImage.setFitHeight(60);
    playerImage.setFitWidth(60);

    HBox healthBox = createHealthBox(player.getHealth());
    vBox.getChildren().addAll(nameLabel, healthBox, playerImage);

    circularPane.getChildren().add(vBox);

    playerMap.put(player, vBox);
  }

  /**
   * This method adds health icons to a HBox.
   *
   * @param health Player health
   * @return HBox with player health icons.
   */
  public HBox createHealthBox(int health) {
    HBox healthBox = new HBox();
    healthBox.setAlignment(Pos.CENTER);

    if (health == 0) {
      return setDead();
    }

    for (int i = 0; i < health; i++) {
      ImageView healthView = new ImageView(new Image("/no/matkje/media/life.png"));
      healthView.setFitWidth(15);
      healthView.setFitHeight(15);
      healthBox.getChildren().add(healthView);
    }

    return healthBox;
  }

  public HBox setDead() {
    ImageView deadIcon = new ImageView(new Image("/no/matkje/media/dead.png"));
    deadIcon.setFitHeight(15);
    deadIcon.setFitWidth(15);
    HBox hBox = new HBox(deadIcon);
    hBox.setAlignment(Pos.CENTER);
    return hBox;
  }

  public void updateDummy() {
    Player player1 = new Player("Player 1", new Image("/no/matkje/media/noSelect.png"), 2,1);
    updatePlayer(player1, 0);

  }

  public void updatePlayer(Player player, int updatedHealth) {
    circularPane.getChildren().remove(player.getId());
    player.setHealth(updatedHealth);
    addPlayer(player);
  }

}
