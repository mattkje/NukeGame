package no.matkje.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.MediaView;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Represents the game startup phase.
 * The class should play an intro video
 * before changing the scene root to the main menu.
 *
 * @author Matti Kjellstadli
 * @version 2023-05-18
 */
public class GesserGame extends Application {

  /**
   * This method should start the game after playing an intro video.
   *
   * @param stage The Game stage.
   */
  @Override
  public void start(Stage stage) {

    MainMenuController controller = new MainMenuController();
    BorderPane root = new BorderPane();
    root.setPrefSize(1920, 1080);
    root.setStyle("-fx-background-color: linear-gradient(#ff9200, #ffe000)");

    ImageView loading = new ImageView(new Image("/no/matkje/media/loading.png"));
    root.setCenter(loading);


    Scene scene = new Scene(root, 1920, 1080);

    MainMenu mainMenu = new MainMenu();

    //Give program time to load stylesheets
    Platform.runLater(() -> {
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      mainMenu.startMain(scene);
    });




    stage.setFullScreen(true);
    stage.setScene(scene);
    stage.setTitle("Gesser");
    stage.getIcons().add(new Image("/no/matkje/media/icon.png"));
    stage.setFullScreenExitHint("");
    stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
    stage.show();
  }

  /**
   * Responsible for launching the game.
   *
   * @param args Launch args.
   */
  public static void appMain(String[] args) {
    launch(args);
  }

}
