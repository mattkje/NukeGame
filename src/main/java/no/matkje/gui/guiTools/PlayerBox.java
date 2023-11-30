package no.matkje.gui.guiTools;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import no.matkje.gameServer.Player;

public class PlayerBox extends VBox {

  private final Player player;
  private Label nameLabel;
  private ImageView playerImage;
  private HBox healthBox;

  public PlayerBox(Player player) {
    this.player = player;
    initialize();
  }

  // Initialize the player box components
  private void initialize() {
    setAlignment(Pos.CENTER);
    setSpacing(5);

    nameLabel = new Label(player.getName());
    nameLabel.setFont(Font.font("System Bold", 17.0));
    nameLabel.setTextFill(Color.WHITE);

    playerImage = new ImageView(player.getImage());
    playerImage.setFitHeight(60);
    playerImage.setFitWidth(60);

    healthBox = createHealthBox(player.getHealth());
    setMaxHeight(0);
    setMaxWidth(0);
    getChildren().addAll(nameLabel, healthBox, playerImage);
  }

  // Method to update player's health
  public void updatePlayer(int updatedHealth) {
    player.setHealth(updatedHealth);
    getChildren().remove(1);
    healthBox = createHealthBox(player.getHealth());
    getChildren().add(1, healthBox);
  }

  // Method to display a dead player
  public void setDead() {
    ImageView deadIcon = new ImageView(new Image("/no/matkje/media/dead.png"));
    deadIcon.setFitHeight(15);
    deadIcon.setFitWidth(15);
    HBox deadBox = new HBox(deadIcon);
    deadBox.setAlignment(Pos.CENTER);
    playerImage.setOpacity(0.5);
    nameLabel.setOpacity(0.5);
    nameLabel.setFont(Font.font("System Bold", FontPosture.ITALIC, 17.0));
    getChildren().add(1, deadBox);
  }

  // Method to create the health box based on player's health
  private HBox createHealthBox(int health) {
    HBox healthBox = new HBox();
    healthBox.setAlignment(Pos.CENTER);

    if (health <= 0) {
      setDead();
      return healthBox;
    }

    for (int i = 0; i < health; i++) {
      ImageView healthView = new ImageView(new Image("/no/matkje/media/life.png"));
      healthView.setFitWidth(15);
      healthView.setFitHeight(15);
      healthBox.getChildren().add(healthView);
    }

    return healthBox;
  }
}
