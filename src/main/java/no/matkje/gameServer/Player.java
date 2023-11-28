package no.matkje.gameServer;

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
  private int health;
  private final int id;

  /**
   * Creates an instance of Player.
   *
   * @param name   The player name.
   * @param health  The player score.
   */
  public Player(String name, Image image, int health, int id) {
    this.name = name;
    this.image = image;
    this.health = health;
    this.id = id;
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

  public void setHealth(int health) {
    this.health = health;
  }



  /**
   * Adds the specified amount to the player's score and returns the new score value.
   *
   * @param amount The amount to add to the player's score.
   */
  public void addHealth(int amount) {
    health += amount;
  }

  public boolean checkHealth() {
    return health >= 0;
  }

  /**
   * Returns the player's current score value.
   *
   * @return The player's score value.
   */
  public int getHealth() {
    return health;
  }


  public int getId() {
    return id;
  }

  /**
   * Uses the Builder design pattern on the Player class, so that it is possible to create different
   * representations of players with a dedicated builder.
   */
  public static class PlayerBuilder {
    private String name;
    private Image image;
    private int health;
    private int id;

    /**
     * Creates a new instance of the PlayerBuilder class.
     */
    public PlayerBuilder() {
      this.name = "";
      this.image = null;
      this.health = 0;
      this.id = 0;
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
     * @param health Player score.
     * @return The player score.
     */
    public PlayerBuilder health(int health) {
      if (health > 0) {
        this.health = health;
      }
      return this;
    }

    public PlayerBuilder id(int id) {
      this.id = id;
      return this;
    }

    /**
     * Builds the player.
     *
     * @return The player.
     */
    public Player build() {
      return new Player(name, image, health, id);
    }

  }
}
