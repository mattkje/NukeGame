package no.matkje.gui.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class MainMenuController {
  @FXML
  private Scene scene;
  private FXMLLoader fxmlLoader;
  private Parent root;

  public void setScene(Scene scene) {
    this.scene = scene;
  }

  public void joinGame() throws IOException {
    fxmlLoader = new FXMLLoader(getClass().getResource("/no/matkje/fxml/gameMenu.fxml"));
    root = fxmlLoader.load();

    GameClientController controller = fxmlLoader.getController();
    controller.setScene(scene);

    scene.setRoot(root);
  }
}
