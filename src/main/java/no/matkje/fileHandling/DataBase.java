package no.matkje.fileHandling;

import no.matkje.logic.Question;
import no.matkje.logic.Round;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * Represents reading from and writing to the database.
 *
 * @author Matti Kjellstadli
 * @version 2023-05-19
 */
public class DataBase {

  private static final String PP_FILE = "data/PlayerData/Images/pp";
  private static final String PLAYER_FILE = "data/PlayerData/Players/player";

  private boolean gameState = true;


  public boolean getGameState() {
    return this.gameState;
  }



  /**
   * This method is responsible for writing settings to file.
   *
   * @param fs   Fullscreen status.
   * @param bg   Background status.
   * @param vlm  Music volume status.
   * @param vlm2 FX volume status.
   */
  public void writeSettingsToFile(Boolean fs, Boolean bg, double vlm, double vlm2) {
    try (FileWriter fileWriter = new FileWriter("data/settings.cfg")) {
      fileWriter.write("fullscreen=" + fs + "\n");
      fileWriter.write("background=" + bg + "\n");
      fileWriter.write("music=" + vlm + "\n");
      fileWriter.write("fx=" + vlm2 + "\n");
    } catch (IOException e) {
      String exceptionString = "Something went wrong while writing settings to file" + e;
      System.getLogger(exceptionString);
    }
  }

  /**
   * This method is responsible for reading setting file and returning
   * a map containing the status of the setting.
   *
   * @return Map containing settings status.
   */
  public Map<String, String> readSettingsFromFile() {
    Map<String, String> map = new HashMap<>();
    File settingsFile = new File("data/settings.cfg");
    try (Scanner scanner = new Scanner(settingsFile)) {
      String fs = scanner.nextLine();
      String fullscreen = (fs.replace("fullscreen=", ""));
      String bg = scanner.nextLine();
      String background = (bg.replace("background=", ""));
      String vlm = scanner.nextLine();
      String volume = (vlm.replace("music=", ""));
      String vlm2 = scanner.nextLine();
      String volume2 = (vlm2.replace("fx=", ""));


      map.put("fs", fullscreen);
      map.put("bg", background);
      map.put("vlm", volume);
      map.put("vlm2", volume2);

    } catch (FileNotFoundException e) {
      String exceptionString = "Something went wrong while reading settings from file" + e;
      System.getLogger(exceptionString);
    }
    return map;
  }

  /**
   * This method is responsible for handling delete player requests based
   * on the player id.
   *
   * @param i Player ID.
   */
  public void deletePlayer(int i) {
    File playerFile = new File(PLAYER_FILE + i + ".dat");
    File playerImageFile = new File(PP_FILE + i + ".png");
    if (playerFile.delete() && playerImageFile.delete()) {
      System.getLogger("Deleted the file player");
    } else {
      System.getLogger("Failed to delete the player.");
    }
  }

  /**
   * This method is responsible for reading tutorial file and returning it as a string.
   *
   * @return Tutorial as String.
   */
  public String readTutorial() {
    StringBuilder tutorial = new StringBuilder();
    try (InputStream inputStream = getClass().getResourceAsStream("/no/matkje/tutorial.txt")) {
      assert inputStream != null;
      try (Scanner scanner = new Scanner(inputStream)) {
        while (scanner.hasNextLine()) {
          tutorial.append(scanner.nextLine()).append("\n");
        }
      }
    } catch (IOException e) {
      String exceptionString = "Something went wrong while reading tutorial from file" + e;
      System.getLogger(exceptionString);
    }
    return tutorial.toString();
  }

  public Round getQuestions(String category){
    //Reads all files with the given category, and returns an object of Round.


    return null;
  }
}
