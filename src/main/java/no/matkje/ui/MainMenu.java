package no.matkje.ui;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Map;
import java.util.Objects;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import no.matkje.fileHandling.DataBase;
import no.matkje.logic.JukeBox;

/**
 * This class represents a starting point for the GUI (Main menu).
 *
 * @author Matti Kjellstadli
 * @version 2023-05-18
 */
public class MainMenu {

  private static final String PACIFICO = "Segoe UI Black";

  private static final String VERSION = "Version: 07.09.23";


  /**
   * The main menu of the game.
   *
   * @param mainScene The scene of the game
   */
  public void startMain(Scene mainScene) {


    //Defining variables
    DataBase dataBase = new DataBase();
    Map<String, String> map = dataBase.readSettingsFromFile();
    double musicVolume = Double.parseDouble(map.get("vlm"));
    double fxVolume = Double.parseDouble(map.get("vlm2"));

    MainMenuController controller = new MainMenuController();



    //Get the root from the scene created in PathsGame and clears it.
    BorderPane root = (BorderPane) mainScene.getRoot();
    root.getChildren().clear();

    //Initializing saved settings.
    controller.changeSettings(Boolean.parseBoolean(map.get("fs")),
        Boolean.parseBoolean(map.get("bg")), musicVolume, fxVolume);

    //Initializing sounds and music.
    JukeBox jukeBox = new JukeBox();
    MediaPlayer player = jukeBox.getMainMenuMusic();
    player.setOnEndOfMedia(() -> player.seek(javafx.util.Duration.ZERO));
    player.play();


    MediaPlayer fxPlayer = controller.getButtonClick(fxVolume);
    mainScene.addEventFilter(ActionEvent.ACTION, event -> {
      fxPlayer.seek(Duration.ZERO);
      fxPlayer.play();
    });


    //Styling.
    mainScene.getStylesheets().add("https://fonts.googleapis.com/css2?family=Fugaz+One");
    mainScene.getStylesheets().add("https://fonts.googleapis.com/css2?family=Comfortaa");
    mainScene.getStylesheets().add("https://fonts.googleapis.com/css2?family=JetBrains+Mono");
    mainScene.getStylesheets().add(
        Objects.requireNonNull(this.getClass().getResource("/no/matkje/css/main.css"))
            .toExternalForm());
    root.setId("mainRoot");
    controller.getBackground(root);


    //Shadows and fonts
    DropShadow dropShadow = new DropShadow();
    dropShadow.setOffsetY(5.0);
    dropShadow.setColor(Color.color(0, 0, 0, 0.5));
    DropShadow solidShadow = new DropShadow();
    solidShadow.setHeight(0.0);
    solidShadow.setOffsetY(10.0);
    solidShadow.setRadius(1.4475);
    solidShadow.setSpread(1.0);
    solidShadow.setWidth(15.79);
    solidShadow.setColor(Color.color(0, 0, 0, 0.5));
    DropShadow titleShadow = new DropShadow();
    titleShadow.setColor(Color.color(1, 1, 1));
    titleShadow.setSpread(1);
    titleShadow.setRadius(3);
    DropShadow glow = new DropShadow();
    glow.setColor(Color.WHITE);
    glow.setSpread(1);
    glow.setRadius(2);

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth();
    Font font = Font.font("Fugaz One", width / 100);
    Font titleFont = Font.font("Fugaz One", FontPosture.ITALIC, width / 9);
    Font titleFontSmall = Font.font("Fugaz One", FontPosture.ITALIC , 100);


    //Title (P and aths are separated to make them closer to each other)
    Label title1 = new Label("GESSER");
    title1.setTextFill(Color.WHITE);
    title1.setAlignment(Pos.CENTER);
    title1.setEffect(solidShadow);
    title1.setFont(titleFont);

    //Title animation
    TranslateTransition translate = new TranslateTransition();
    translate.setNode(title1);
    translate.setDuration(Duration.millis(2500));
    translate.setCycleCount(Animation.INDEFINITE);
    translate.setByY(-25);
    translate.setAutoReverse(true);
    translate.play();
    FadeTransition fade = new FadeTransition();
    fade.setNode(title1);
    fade.setDuration(Duration.millis(2000));
    fade.setInterpolator(Interpolator.LINEAR);
    fade.setFromValue(0);
    fade.setToValue(1);
    fade.play();

    HBox titleBox = new HBox(title1);
    titleBox.setAlignment(Pos.CENTER);
    root.setTop(titleBox);

    //Import file menu.
    Font menuFont = Font.font(PACIFICO, 34);
    Label label = new Label("About story:");
    Label selectGame = new Label("Upload story");
    selectGame.setFont(menuFont);
    selectGame.setTextFill(Color.WHITE);
    label.setFont(menuFont);
    label.setTextFill(Color.WHITE);
    VBox importMenu = new VBox();
    importMenu.getChildren().add(selectGame);
    importMenu.setId("importMenu");
    importMenu.setPrefWidth(400);
    importMenu.setMaxHeight(400);
    importMenu.setSpacing(10);
    importMenu.setAlignment(Pos.TOP_CENTER);
    importMenu.setEffect(dropShadow);
    FileChooser fileChooser = new FileChooser();
    FileChooser.ExtensionFilter extFilter =
        new FileChooser.ExtensionFilter("Paths files (*.paths)", "*.paths");
    FileChooser.ExtensionFilter extFilter2 =
        new FileChooser.ExtensionFilter("GPaths files (*.Gpaths)", "*.gpaths");
    fileChooser.getExtensionFilters().addAll(extFilter, extFilter2);


    //Feedback label
    Label noFile = new Label("No file selected");
    noFile.setStyle("-fx-font-family: Comfortaa");
    noFile.setTextFill(Color.WHITE);
    noFile.setAlignment(Pos.CENTER);
    noFile.setId("noFile");

    //About story
    Label storyNameLabel = new Label("Story name: ");
    Label storyPassagesLabel = new Label("Passages: ");
    Label deadLinksLabel = new Label("Dead links: ");
    Label storyName = new Label();
    Label storyPassages = new Label();
    Label deadLinks = new Label();
    GridPane aboutStoryPane = new GridPane();
    aboutStoryPane.setHgap(10);
    aboutStoryPane.setVgap(10);
    aboutStoryPane.setId("aboutStoryPane");
    aboutStoryPane.setMaxWidth(360);
    aboutStoryPane.setAlignment(Pos.CENTER);
    aboutStoryPane.add(storyNameLabel, 0, 0);
    aboutStoryPane.add(storyPassagesLabel, 0, 1);
    aboutStoryPane.add(deadLinksLabel, 0, 2);
    aboutStoryPane.add(storyName, 1, 0);
    aboutStoryPane.add(storyPassages, 1, 1);
    aboutStoryPane.add(deadLinks, 1, 2);

    //Dead links warning

    //Open paths button.
    Font menuFontLarge = Font.font("Fugaz One", 64);
    Button openPathsFile = new Button("Open paths file");
    openPathsFile.setEffect(dropShadow);
    openPathsFile.setFont(font);
    openPathsFile.setTextFill(Color.WHITE);
    openPathsFile.setOnAction(e -> {



    });

    //Start game Button
    Button startGame = new Button("Start");
    startGame.setEffect(dropShadow);
    startGame.setFont(font);
    startGame.setTextFill(Color.WHITE);
    //Game Starting point. Checks if imported file is valid, and opens the next menu.
    startGame.setOnAction(e -> {
      //Prevents user from starting game if the story has dead links.

      player.dispose();
    });


    HBox gameControl = new HBox();
    gameControl.getChildren().addAll(openPathsFile, startGame);
    gameControl.setAlignment(Pos.CENTER);
    gameControl.setSpacing(3);
    Button openFileEditor = new Button("Open in editor");
    openFileEditor.setOnAction(event -> {

    });
    importMenu.getChildren().addAll(gameControl, noFile, openFileEditor, label, aboutStoryPane);


    //Creates graphics for default story buttons.
    GridPane defaultStories = new GridPane();
    defaultStories.setAlignment(Pos.CENTER);
    defaultStories.setVgap(4);



    //Saved game progress
    Label savedTitle = new Label("Previously played");
    savedTitle.setFont(menuFont);
    savedTitle.setTextFill(Color.WHITE);
    savedTitle.setAlignment(Pos.CENTER);
    Button continueGame = new Button("Continue");
    continueGame.setFont(font);
    continueGame.setOnAction(e -> {
      player.dispose();

    });




    //Back Button
    ImageView backImage = new ImageView("/no/matkje/media/back.png");
    Button backButton = new Button();
    backButton.setGraphic(backImage);
    backButton.setId("backButton");
    backButton.setPrefSize(100, 100);
    backButton.setAlignment(Pos.CENTER);
    backButton.setEffect(dropShadow);

    //Menu center area
    HBox menuBox = new HBox();
    menuBox.setAlignment(Pos.CENTER);
    menuBox.setSpacing(10);
    root.setCenter(menuBox);

    //Tutorial screen
    Label tutorialTitle = new Label("How to play");
    tutorialTitle.setTextFill(Color.WHITE);
    tutorialTitle.setFont(font);
    TextArea tutorialText = new TextArea(dataBase.readTutorial());
    Font textFont = Font.font("JetBrains Mono", width / 120);
    tutorialText.setFont(textFont);
    VBox tutorialBox = new VBox();
    tutorialBox.setId("tutorialBox");
    tutorialBox.setPrefSize(1500, 800);
    tutorialText.setPrefSize(root.getWidth() / 0.7, root.getHeight() / 0.9);
    tutorialBox.setAlignment(Pos.CENTER);
    tutorialBox.setEffect(dropShadow);
    tutorialBox.getChildren().addAll(tutorialTitle, tutorialText);

    //Settings screen
    Label settingsTitle = new Label("Settings");
    settingsTitle.setPadding(new Insets(20));
    settingsTitle.setFont(font);

    Label someLabel = new Label("Display");
    someLabel.setFont(font);
    someLabel.setAlignment(Pos.CENTER);
    Button toggleFullscreen = new Button("Toggle fullscreen");
    toggleFullscreen.setFont(font);
    toggleFullscreen.setAlignment(Pos.CENTER);
    Stage stage = (Stage) mainScene.getWindow();
    toggleFullscreen.setOnAction(e ->
            controller.fullscreenButton(stage, title1, titleBox, titleFontSmall, titleFont));

    //Change Resolution
    Label smallScreen = new Label("Set resolution");
    smallScreen.setFont(font);
    smallScreen.setAlignment(Pos.CENTER);

    //Display resolution combobox
    ComboBox<String> resolutionComboBox = new ComboBox<>();
    resolutionComboBox.setId("resolutionComboBox");
    resolutionComboBox.getItems().addAll("1920 x 1080", "1280 x 1024", "800 x 600");
    resolutionComboBox.getSelectionModel().selectFirst();
    resolutionComboBox.setOnAction(e -> {
      String selectedResolution = resolutionComboBox.getSelectionModel().getSelectedItem();

    });

    Label backgroundLabel = new Label("Background");
    backgroundLabel.setFont(font);
    backgroundLabel.setAlignment(Pos.CENTER);
    Button disableAnimation = new Button("Disable background");
    disableAnimation.setFont(font);
    disableAnimation.setAlignment(Pos.CENTER);
    disableAnimation.setOnAction(e -> {
      controller.changeSettings(Boolean.parseBoolean(map.get("fs")), false,
          Double.parseDouble(map.get("vlm")),
          Double.parseDouble(map.get("vlm2")));
      controller.updateBackground(root, false);
    });
    Button enableBackground = new Button("Enable background");
    enableBackground.setFont(font);
    enableBackground.setAlignment(Pos.CENTER);
    enableBackground.setOnAction(e -> {
      controller.changeSettings(Boolean.parseBoolean(map.get("fs")), true,
          Double.parseDouble(map.get("vlm")),
          Double.parseDouble(map.get("vlm2")));
      controller.updateBackground(root, true);
    });

    HBox fsBox = new HBox(someLabel, toggleFullscreen);
    fsBox.setAlignment(Pos.CENTER);
    fsBox.setSpacing(60);

    HBox ssBox = new HBox(smallScreen, resolutionComboBox);
    ssBox.setAlignment(Pos.CENTER);
    ssBox.setSpacing(60);

    HBox bgBox = new HBox(disableAnimation, enableBackground);
    bgBox.setAlignment(Pos.CENTER);
    bgBox.setSpacing(60);

    //Settings containers
    TabPane settingsPane = new TabPane();
    settingsPane.setId("settingsPane");
    settingsPane.setPrefSize(1000, 800);
    settingsPane.setEffect(dropShadow);

    VBox displayBox = new VBox(fsBox, ssBox, backgroundLabel, bgBox);
    displayBox.setAlignment(Pos.CENTER);
    displayBox.setSpacing(30);
    Tab display = new Tab("Display");
    display.setContent(displayBox);
    display.setClosable(false);
    settingsPane.getTabs().add(display);

    //Volume control
    Slider musicVolumeSlider = new Slider(0, 1, musicVolume);
    Label musicVolumeLabel = new Label("Music volume");
    musicVolumeLabel.setFont(font);
    HBox msBox = new HBox(musicVolumeLabel, musicVolumeSlider);
    msBox.setAlignment(Pos.CENTER);
    msBox.setSpacing(60);

    Slider fxVolumeSlider = new Slider(0, 1, fxVolume);
    Label fxVolumeLabel = new Label("FX volume");
    fxVolumeLabel.setFont(font);
    HBox fxBox = new HBox(fxVolumeLabel, fxVolumeSlider);
    fxBox.setAlignment(Pos.CENTER);
    fxBox.setSpacing(60);

    Button setVol = new Button("Save settings");
    setVol.setFont(font);
    setVol.setAlignment(Pos.CENTER);
    setVol.setOnAction(e ->
        controller.changeSettings(Boolean.parseBoolean(map.get("fs")),
            Boolean.parseBoolean(map.get("bg")),
            musicVolumeSlider.getValue(), fxVolumeSlider.getValue()));


    VBox audioBox = new VBox(msBox, fxBox, setVol);
    audioBox.setAlignment(Pos.CENTER);
    audioBox.setSpacing(30);
    Tab audio = new Tab("Audio");
    audio.setContent(audioBox);
    audio.setClosable(false);
    settingsPane.getTabs().add(audio);

    VBox settingsBox = new VBox(settingsTitle, settingsPane);
    settingsBox.setAlignment(Pos.CENTER);

    //Start menu
    VBox startMenu = new VBox();
    startMenu.setId("startMenu");

    startMenu.setAlignment(Pos.CENTER);
    startMenu.setEffect(dropShadow);
    startMenu.setSpacing(30);

    Button story = new Button("Join Game");
    story.setFont(menuFontLarge);
    story.setTextFill(Color.WHITE);
    story.setId("startButton");
    Button fileEditor = new Button("Add question");
    fileEditor.setFont(font);
    fileEditor.setTextFill(Color.WHITE);
    fileEditor.setId("fileEditorButton");
    Button settings = new Button("Settings");
    settings.setFont(font);
    settings.setTextFill(Color.WHITE);
    settings.setId("settingsButton");
    Button howToPlay = new Button("Host Game");
    howToPlay.setFont(font);
    howToPlay.setTextFill(Color.WHITE);
    howToPlay.setId("howToPlayButton");
    Button exit = new Button("Exit Game");
    exit.setFont(font);
    exit.setTextFill(Color.WHITE);
    exit.setId("exitButton");

    //Story menu button functions
    story.setOnAction(e -> {
      menuBox.getChildren().remove(startMenu);
      menuBox.getChildren().addAll(backButton, importMenu);
    });
    fileEditor.setOnAction(e -> {
      player.dispose();
    });
    settings.setOnAction(e -> {
      menuBox.getChildren().remove(startMenu);
      menuBox.getChildren().addAll(backButton, settingsBox);
      musicVolumeSlider.valueProperty().bindBidirectional(player.volumeProperty());
      fxVolumeSlider.valueProperty().bindBidirectional(fxPlayer.volumeProperty());
    });
    howToPlay.setOnAction(e -> {
      menuBox.getChildren().remove(startMenu);
      root.setTop(null);
      menuBox.getChildren().addAll(backButton, tutorialBox);
    });
    backButton.setOnAction(e -> {
      if (!root.getChildren().contains(titleBox)) {
        root.setTop(titleBox);
      }
      menuBox.getChildren().clear();
      menuBox.getChildren().add(startMenu);
    });

    VBox leftButtons = new VBox(fileEditor, settings);
    leftButtons.setAlignment(Pos.CENTER);
    leftButtons.setSpacing(10);
    VBox rightButtons = new VBox(howToPlay, exit);
    rightButtons.setAlignment(Pos.CENTER);
    rightButtons.setSpacing(10);
    HBox underButtons = new HBox(leftButtons, rightButtons);
    underButtons.setAlignment(Pos.CENTER);
    underButtons.setSpacing(10);
    startMenu.getChildren().addAll(story, underButtons);
    menuBox.getChildren().add(startMenu);

    //About page for the program. Displays Authors and version etc.
    Button about = new Button();
    about.setId("aboutButton");
    Label versionLabel = new Label(VERSION);
    versionLabel.setTextFill(Color.WHITE);
    about.setOnMouseClicked(mouseEvent -> {

      Label aboutTitle = new Label("About");
      Label team = new Label("Created by G4");
      team.setFont(font);
      Button dismissButton = new Button("Dismiss");
      dismissButton.setFont(font);
      aboutTitle.setFont(menuFontLarge);
      aboutTitle.setAlignment(Pos.CENTER);
      VBox aboutBox = new VBox(aboutTitle, team, versionLabel, dismissButton);
      aboutBox.setSpacing(20);
      aboutBox.setPrefSize(600, 400);
      aboutBox.setId("aboutBox");
      aboutBox.setAlignment(Pos.CENTER);
      Popup popup = new Popup();
      popup.getContent().add(aboutBox);
      popup.show(mainScene.getWindow());
      dismissButton.setOnAction(e ->
          popup.hide());
    });


    //Bottom bar.
    HBox bottom = new HBox();
    bottom.setMaxHeight(30);
    HBox growBox = new HBox();
    HBox.setHgrow(growBox, Priority.ALWAYS); // set horizontal grow priority
    growBox.setMaxWidth(Double.MAX_VALUE); // set maximum width to a large value
    bottom.getChildren().addAll(versionLabel, growBox, about);
    root.setBottom(bottom);

    exit.setOnAction(e ->
        controller.exitButton(root, font, menuFontLarge, titleBox, bottom, menuBox, player));
  }

}
