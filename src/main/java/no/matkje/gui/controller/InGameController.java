package no.matkje.gui.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import no.matkje.gameClient.GameClientSocket;

public class InGameController {

  @FXML
  private Scene scene;
  private FXMLLoader fxmlLoader;
  private Parent root;
  private GameClientSocket socket;

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

}
