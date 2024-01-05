package no.matkje.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import no.matkje.command.ConnectCommand;
import no.matkje.gameClient.GameClientSocket;

public class MainMenuController implements Initializable{
  @FXML
  private Scene scene;
  @FXML
  private TextField textField;
  @FXML
  private ImageView logo;
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

    startGame();
    /*
    String code = textField.getText();
    if (!code.isEmpty()) {


    } else {
      textField.setText("CODE!");
    }
     */



  }

  private void startGame() throws IOException {
    fxmlLoader = new FXMLLoader(getClass().getResource("/no/matkje/fxml/gameMenu.fxml"));
    root = fxmlLoader.load();

    GameClientController controller = fxmlLoader.getController();
    controller.setScene(scene);
    controller.setSocket(socket);

    scene.setRoot(root);
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

  }
}
