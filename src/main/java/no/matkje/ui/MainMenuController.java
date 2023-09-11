package no.matkje.ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import no.matkje.fileHandling.DataBase;

/**
 * Represents a controller for the main menu class.
 * This class should handle events triggered by the MainMenu.
 *
 * @author Matti Kjellstadli
 * @version 2023-05-18
 */
public class MainMenuController {

  private static final File CURRENT_PATHS_FILE = new File("Data/currentPathsFile.csv");

  private static final String GPATHS = ".Gpaths";
  private File selectedFile;
  private ImageView citySkyline;
  private ImageView citySkyline2;
  private ImageView citySkyline3;
  private ImageView citySkyline4;
  private ImageView citySkyline5;
  private ImageView citySkyline6;


  /**
   * Responsible for writing the active ".Gpaths" filepath to a file.
   *
   * @param filename Name of the file which is being written.
   */
  public void setActiveGpaths(String filename) {
    Path savedPaths = Path.of(filename);
    try (FileWriter writer = new FileWriter(CURRENT_PATHS_FILE)) {
      writer.write(String.valueOf(savedPaths));

    } catch (IOException ex) {
      String exceptionString = "Something went wrong while setting active Gpaths" + ex;
      System.getLogger(exceptionString);
    }
  }


  /**
   * Sets one of the default paths as the active path.
   *
   * @param filename Default path file name.
   */
  public void setDefaultPath(String filename) {
    Path savedPaths = Path.of("src/main/resources/gruppe/fire/Paths/" + filename);
    try (FileWriter writer = new FileWriter(CURRENT_PATHS_FILE)) {
      writer.write(String.valueOf(savedPaths));

    } catch (IOException ex) {
      String exceptionString = "Something went wrong while setting default path" + ex;
      System.getLogger(exceptionString);
    }
  }

  /**
   * Responsible for the animated background of the game.
   *
   * @param root Root of the game scene.
   */
  public void getBackground(BorderPane root) {

    ColorAdjust colorAdjust = new ColorAdjust();
    colorAdjust.setBrightness(0);
    Image cityImage = new Image("/no/matkje/media/gameBG.png");
    this.citySkyline = new ImageView(cityImage);
    this.citySkyline2 = new ImageView(cityImage);
    this.citySkyline3 = new ImageView(cityImage);
    Image cityImage2 = new Image("/no/matkje/media/gameBG2.png");
    this.citySkyline4 = new ImageView(cityImage2);
    this.citySkyline5 = new ImageView(cityImage2);
    this.citySkyline6 = new ImageView(cityImage2);

    TranslateTransition translateTransition =
        new TranslateTransition(Duration.millis(20000), citySkyline);
    translateTransition.setFromX(0);
    translateTransition.setToX(-1 * (double) 1300);
    translateTransition.setInterpolator(Interpolator.LINEAR);

    TranslateTransition translateTransition2 =
        new TranslateTransition(Duration.millis(20000), citySkyline2);
    translateTransition2.setFromX(1300);
    translateTransition2.setToX(0);
    translateTransition2.setInterpolator(Interpolator.LINEAR);

    TranslateTransition translateTransition3 =
        new TranslateTransition(Duration.millis(20000), citySkyline3);
    translateTransition3.setFromX(2600);
    translateTransition3.setToX(1300);
    translateTransition3.setInterpolator(Interpolator.LINEAR);

    TranslateTransition translateTransition4 =
        new TranslateTransition(Duration.millis(10000), citySkyline4);
    translateTransition4.setFromX(0);
    translateTransition4.setToX(-1 * (double) 1300);
    translateTransition4.setInterpolator(Interpolator.LINEAR);

    TranslateTransition translateTransition5 =
        new TranslateTransition(Duration.millis(10000), citySkyline5);
    translateTransition5.setFromX(1300);
    translateTransition5.setToX(0);
    translateTransition5.setInterpolator(Interpolator.LINEAR);

    TranslateTransition translateTransition6 =
        new TranslateTransition(Duration.millis(10000), citySkyline6);
    translateTransition6.setFromX(2600);
    translateTransition6.setToX(1300);
    translateTransition6.setInterpolator(Interpolator.LINEAR);


    //Need two parallelTransitions as it stops if all are in one.
    ParallelTransition parallelTransition =
        new ParallelTransition(translateTransition, translateTransition2, translateTransition3);
    parallelTransition.setCycleCount(Animation.INDEFINITE);
    ParallelTransition parallelTransition2 =
        new ParallelTransition(translateTransition4, translateTransition5,
            translateTransition6);
    parallelTransition2.setCycleCount(Animation.INDEFINITE);
    parallelTransition2.play();
    parallelTransition.play();

    citySkyline.fitHeightProperty().bind(root.heightProperty());
    citySkyline2.fitHeightProperty().bind(root.heightProperty());
    citySkyline3.fitHeightProperty().bind(root.heightProperty());
    citySkyline4.fitHeightProperty().bind(root.heightProperty());
    citySkyline5.fitHeightProperty().bind(root.heightProperty());
    citySkyline6.fitHeightProperty().bind(root.heightProperty());

    citySkyline4.setLayoutY(200);
    citySkyline5.setLayoutY(200);
    citySkyline6.setLayoutY(200);

    citySkyline4.setEffect(colorAdjust);
    citySkyline5.setEffect(colorAdjust);
    citySkyline6.setEffect(colorAdjust);
    DataBase dataBase = new DataBase();
    Map<String, String> map = dataBase.readSettingsFromFile();
    boolean bg = Boolean.parseBoolean(map.get("bg"));
    if (bg) {
      root.getChildren().addAll(citySkyline, citySkyline2,
          citySkyline3, citySkyline4, citySkyline5, citySkyline6);
    }
  }


  /**
   * This method is responsible for handling the stage switching between
   * fullscreen and windowed mode.
   *
   * @param stage          The game stage.
   * @param title1         Part one of the title labels.
   * @param titleFontSmall Smaller version of title font.
   * @param titleFont      Title font.
   */
  public void fullscreenButton(Stage stage, Label title1,
                               Font titleFontSmall, Font titleFont) {
    if (stage.isFullScreen()) {
      stage.setFullScreen(false);
      stage.setWidth(1380);
      stage.setHeight(800);
      title1.setFont(titleFontSmall);
    } else {
      stage.setFullScreen(true);
      stage.setFullScreenExitHint("");
      stage.setFullScreenExitKeyCombination(null);
      title1.setFont(titleFont);

    }
  }

  /**
   * This method is responsible for handling exit game button.
   * The method will display a confirmation before the user can exit.
   *
   * @param root          Root of the game.
   * @param font          Font used in the exit screen.
   * @param menuFontLarge Font used in the exit screen.
   * @param titleBox      HBox containing both title parts.
   * @param bottom        Bottom HBox of the main menu.
   * @param menuBox       Main menu buttons VBox.
   * @param player        MediaPlayer playing background music.
   */
  public void exitButton(BorderPane root, Font font, Font menuFontLarge, HBox titleBox, HBox bottom,
                         HBox menuBox, MediaPlayer player) {
    player.setVolume(player.getVolume() - 0.4);
    Label confirmationLabel = new Label("Are you sure you want to quit?");
    confirmationLabel.setFont(font);
    Button yesButton = new Button("Yes");
    Button noButton = new Button("No");
    HBox quitButtons = new HBox(yesButton, noButton);
    quitButtons.setSpacing(40);
    quitButtons.setAlignment(Pos.CENTER);
    yesButton.setFont(font);
    noButton.setFont(font);
    Label quitTitle = new Label("Exit the game");
    quitTitle.setFont(menuFontLarge);
    quitTitle.setAlignment(Pos.CENTER);
    VBox quitBox = new VBox(quitTitle, confirmationLabel, quitButtons);
    quitBox.setSpacing(20);
    quitBox.setMinSize(600, 300);
    quitBox.setId("quitBox");
    quitBox.setAlignment(Pos.CENTER);
    root.setTop(null);
    root.setBottom(null);
    root.setCenter(quitBox);


    yesButton.setOnAction(exitEvent ->
        Platform.exit());
    noButton.setOnAction(exitEvent -> {
      root.setTop(titleBox);
      root.setBottom(bottom);
      root.setCenter(menuBox);
      player.setVolume(player.getVolume() + 0.4);

    });
  }

  /**
   * Responsible for handling user input from settings.
   *
   * @param fs   Fullscreen
   * @param bg   Background
   * @param vlm  Music volume
   * @param vlm2 FX volume
   */
  public void changeSettings(boolean fs, boolean bg, double vlm, double vlm2) {
    DataBase dataBase = new DataBase();
    dataBase.writeSettingsToFile(fs, bg, vlm, vlm2);
  }

  /**
   * Responsible for giving the player instant feedback when
   * disabling/enabling the background animation.
   *
   * @param root The game root node.
   * @param bg   Background boolean
   */
  public void updateBackground(BorderPane root, boolean bg) {
    if (!bg) {
      root.getChildren()
          .removeAll(citySkyline, citySkyline2, citySkyline3, citySkyline4,
              citySkyline5,
              citySkyline6);
    }
    if (bg) {
      root.getChildren()
          .addAll(citySkyline, citySkyline2, citySkyline3, citySkyline4, citySkyline5,
              citySkyline6);
      citySkyline6.toBack();
      citySkyline5.toBack();
      citySkyline4.toBack();
      citySkyline3.toBack();
      citySkyline2.toBack();
      citySkyline.toBack();
    }
  }

  public void fullscreenButton(Stage stage, Label title1, HBox titleBox,
                               Font titleFontSmall, Font titleFont) {
    if (stage.isFullScreen()) {
      stage.setFullScreen(false);
      stage.setWidth(1380);
      stage.setHeight(800);
      title1.setFont(titleFontSmall);
      titleBox.setSpacing(-26);
    } else {
      stage.setFullScreen(true);
      stage.setFullScreenExitHint("");
      stage.setFullScreenExitKeyCombination(null);
      title1.setFont(titleFont);
      titleBox.setSpacing(-50);

    }
  }

  /**
   * Responsible for changing the game resolution.
   *
   * @param resolution     Selected resolution as string.
   * @param stage          Game stage
   * @param title1         Part one of title text.
   * @param titleFontSmall Smaller font for title text.
   */
  public void handleResolutionChange(String resolution, Stage stage, Label title1, Font titleFontSmall) {
    if (stage.isFullScreen()) {
      stage.setFullScreen(false);
    }

    switch (resolution) {
      case "1920 x 1080" -> {
        stage.setWidth(1920);
        stage.setHeight(1080);
      }
      case "1280 x 1024" -> {
        title1.setFont(titleFontSmall);
        stage.setWidth(1280);
        stage.setHeight(1024);
      }
      case "800 x 600" -> {
        title1.setFont(titleFontSmall);
        stage.setWidth(800);
        stage.setHeight(600);
      }
      default -> {
        stage.setWidth(0);
        stage.setHeight(0);
      }
    }
  }

  /**
   * Validates the imported Gpaths file by checking if the paths name is right.
   *
   * @return True if the file exists, false otherwise.
   */
  public boolean checkBrokenGpath() {
    String filePath = "Data/currentGpaths/story.paths";
    File file = new File(filePath);
    return file.exists();
  }

  /**
   * Responsible for returning the startup animation.
   *
   * @return Startup animation
   */
  public MediaView getLoadingVideo() {

    Media media = new Media(
        Objects.requireNonNull(getClass().getResource("/no/fire/Media/PathsLoad.mp4"))
            .toString());
    MediaPlayer player = new MediaPlayer(media);
    return new MediaView(player);

  }


  /**
   * Responsible for returning the button noise.
   *
   * @param fxVolume FX volume set in settings.
   * @return FX noises.
   */
  public MediaPlayer getButtonClick(double fxVolume) {
    Media sound = new Media(
        Objects.requireNonNull(getClass().getResource("/no/matkje/media/button.wav")).toString());
    MediaPlayer mediaPlayer = new MediaPlayer(sound);
    mediaPlayer.setVolume(fxVolume);

    return mediaPlayer;

  }

}
