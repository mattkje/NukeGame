package no.matkje.gui;

import java.io.IOException;
import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import no.matkje.gameClient.GameClientLogic;
import no.matkje.gameClient.GameClientSocket;
import no.matkje.gui.controller.MainMenuController;
import no.matkje.tools.Logger;

public class GameClientApplication extends Application {

  private static final int WIDTH = 1000;
  private static final int HEIGHT = 600;
  private static GameClientLogic logic;
  private static GameClientSocket client;
  private Scene mainScene;

  public static void startApp(GameClientLogic logic, GameClientSocket client) {
    if (logic == null) {
      throw new IllegalArgumentException("Logic can't be null");
    }
    GameClientApplication.logic = logic;
    GameClientApplication.client = client;
    Logger.info("Starting client...");
    if (client.start()) {
      System.out.println("Server found: " + client.getServerHost());
    } else {
      System.out.println("No server was found. Starting in disconnected mode");
    }
    launch();
    client.stop();
  }
  @Override
  public void start(Stage stage) {
    if (client == null) {
      throw new IllegalStateException(
          "No communication channel. See the README on how to use fake event spawner!");
    }

    stage.setTitle("GAYKLM");

    Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/no/matkje/media/icon.png")));
    stage.getIcons().add(icon);

    try {
      // Load FXML file
      FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/no/matkje/fxml/mainMenu.fxml"));
      Parent root = fxmlLoader.load();

      mainScene = new Scene(root);
      mainScene.getStylesheets().add(
          Objects.requireNonNull(getClass().getResource("/no/matkje/css/main.css")).toExternalForm());

      Font.loadFont(
          Objects.requireNonNull(
              this.getClass().getResourceAsStream("/no/matkje/font/Lato.ttf")),
          14
      );

      MainMenuController controller = fxmlLoader.getController();
      controller.setScene(mainScene);
      controller.setSocket(client);

      stage.setScene(mainScene);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace(); // Handle the exception accordingly
    }
  }

  private static Label createMenu() {
    Label l = new Label("Connecting to server");
    l.setAlignment(Pos.CENTER);
    return l;
  }
}
