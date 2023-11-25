package no.matkje.gui.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import no.matkje.gameClient.GameClientSocket;

public class MainMenuController {
  @FXML
  private Scene scene;
  @FXML
  private TextField textField;
  private FXMLLoader fxmlLoader;
  private Parent root;
  private GameClientSocket socket;



  public void setScene(Scene scene) {
    this.scene = scene;
  }

  public void setSocket(GameClientSocket socket) {
    this.socket = socket;
  }

  public void joinGame() throws IOException {
    String code = textField.getText();

    if (!code.isEmpty()) {
      if (socket.sendCommand("c").equals("ok")) {
        startGame();
      } else {
        textField.setText("failed");
      }

    } else {
      textField.setText("CODE!");
    }


  }

  private void startGame() throws IOException {
    fxmlLoader = new FXMLLoader(getClass().getResource("/no/matkje/fxml/gameMenu.fxml"));
    root = fxmlLoader.load();

    GameClientController controller = fxmlLoader.getController();
    controller.setScene(scene);
    controller.setSocket(socket);

    scene.setRoot(root);
  }
}
