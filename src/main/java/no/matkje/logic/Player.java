package no.matkje.logic;

import javafx.scene.image.Image;

/**
 * The Player class represents a player with various properties that can be influenced in a game.
 *
 * @author Matti Kjellstadli
 * @version 2023-05-21
 */
public class Player {
  private String name;
  private Image image;
  private int score;
  private int rank;

  /**
   * Creates an instance of Player.
   *
   * @param name   The player name.
   * @param score  The player score.
   */
  private Player(String name, Image image, int score, int rank) {
    this.name = name;
    this.image = image;
    this.score = score;
    this.rank = rank;
  }

  /**
   * Returns the player's name.
   *
   * @return The player's name.
   */
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Image getImage() {
    return image;
  }

  public void setImage(Image image) {
    this.image = image;
  }

  public void setScore(int score) {
    this.score = score;
  }



  /**
   * Adds the specified amount to the player's score and returns the new score value.
   *
   * @param amount The amount to add to the player's score.
   */
  public void addScore(int amount) {
    score += amount;
  }

  public boolean checkScore() {
    return score >= 0;
  }

  /**
   * Returns the player's current score value.
   *
   * @return The player's score value.
   */
  public int getScore() {
    return score;
  }

  /**
   * Uses the Builder design pattern on the Player class, so that it is possible to create different
   * representations of players with a dedicated builder.
   */
  public static class PlayerBuilder {
    private String name;
    private Image image;
    private int score;
    private int rank;

    /**
     * Creates a new instance of the PlayerBuilder class.
     */
    public PlayerBuilder() {
      this.name = "";
      this.image = null;
      this.score = 0;
      this.rank = 0;
    }

    /**
     * Sets the name of the player.
     *
     * @param name Player name.
     * @return The player name.
     */
    public PlayerBuilder name(String name) {
      this.name = name;
      return this;
    }

    /**
     * Sets the image of the player.
     *
     * @param image Player image.
     * @return The player image.
     */
    public PlayerBuilder image(Image image) {
      this.image = image;
      return this;
    }

    /**
     * Sets the score of the player.
     *
     * @param score Player score.
     * @return The player score.
     */
    public PlayerBuilder score(int score) {
      if (score > 0) {
        this.score = score;
      }
      return this;
    }

    /**
     * Sets the rank of the player.
     *
     * @param rank Player rank.
     * @return The player rank.
     */
    public PlayerBuilder rank(int rank) {
      if (rank > 0) {
        this.rank = rank;
      }
      return this;
    }

    /**
     * Builds the player.
     *
     * @return The player.
     */
    public Player build() {
      return new Player(name, image, score, rank);
    }

  }
}
