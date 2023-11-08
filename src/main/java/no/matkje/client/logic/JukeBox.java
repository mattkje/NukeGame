package no.matkje.client.logic;

import no.matkje.client.fileHandling.DataBase;
import java.util.Objects;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Represents the music in the game.
 * This class should handle music requests from different points in the game engine.
 *
 * @author Matti Kjellstadli
 * @version 2023-05-18
 */
public class JukeBox {

  private MediaPlayer mediaPlayer;

  private static final String MAIN_MENU_MUSIC = "/no/matkje/media/backgroundMusic.mp3";

  /**
   * This method is responsible for returning the main menu background music.
   *
   * @return The main menu background music.
   */
  public MediaPlayer getMainMenuMusic() {
    DataBase dataBase = new DataBase();
    Media sound = new Media(
        Objects.requireNonNull(getClass().getResource("/no/matkje/media/backgroundMusic.mp3"))
            .toString());
    this.mediaPlayer = new MediaPlayer(sound);
    mediaPlayer.setVolume(Double.parseDouble(dataBase.readSettingsFromFile().get("vlm")));
    return mediaPlayer;
  }

  /**
   * This method is responsible for returning the player menu background music.
   *
   * @return The player menu background music.
   */
  public MediaPlayer getPlayerMenuMusic() {
    DataBase dataBase = new DataBase();
    Media sound = new Media(
        Objects.requireNonNull(getClass().getResource(MAIN_MENU_MUSIC))
            .toString());
    this.mediaPlayer = new MediaPlayer(sound);
    mediaPlayer.setVolume(Double.parseDouble(dataBase.readSettingsFromFile().get("vlm")));
    return mediaPlayer;
  }

  /**
   * This method is responsible for returning the right music for the stories.
   *
   * @return Gameplay background music.
   */
  public MediaPlayer getGameplayMusic() {
    DataBase dataBase = new DataBase();

    Media defaultSound = new Media(
        Objects.requireNonNull(getClass().getResource(MAIN_MENU_MUSIC))
            .toString());

    this.mediaPlayer = new MediaPlayer(defaultSound);
    mediaPlayer.setVolume(Double.parseDouble(dataBase.readSettingsFromFile().get("vlm")));
    return mediaPlayer;
  }
}
