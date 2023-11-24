package no.matkje.gui;

import java.util.Objects;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import no.matkje.gameClient.GameClientLogic;
import no.matkje.gameClient.GameClientSocket;
import no.matkje.tools.Logger;

public class GameClientApplication extends Application {

  private static final int WIDTH = 1000;
  private static final int HEIGHT = 600;
  private static GameClientLogic logic;
  private static GameClientSocket socket;
  private Scene mainScene;

  public static void startApp(GameClientLogic logic, GameClientSocket socket) {
    if (logic == null) {
      throw new IllegalArgumentException("Logic can't be null");
    }
    GameClientApplication.logic = logic;
    GameClientApplication.socket = socket;
    Logger.info("Starting client...");
    launch();
  }
  @Override
  public void start(Stage stage) {
    if (socket == null) {
      throw new IllegalStateException(
          "No communication channel. See the README on how to use fake event spawner!");
    }

    stage.setMinWidth(WIDTH);
    stage.setMinHeight(HEIGHT);
    stage.setTitle("Control panel");
    mainScene = new Scene(createMenu(), WIDTH, HEIGHT);
    mainScene.getStylesheets().add(
        Objects.requireNonNull(this.getClass().getResource("/no/matkje/css/main.css")).toExternalForm());
    stage.setScene(mainScene);
    stage.show();
  }

  private static Label createMenu() {
    Label l = new Label("Connecting to server");
    l.setAlignment(Pos.CENTER);
    return l;
  }
}