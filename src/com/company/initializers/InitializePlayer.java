package com.company.initializers;

import com.company.characters.Player;

/**
 * This class initializes and creates the player in the game. of them.
 *
 * @author Szabolcs D. Nagy
 * @version 29.11.2020
 */
public class InitializePlayer {

  private final InitializeRooms initializeRooms;
  private final InitializeItems initializeItems;
  private Player player;

  /**
   * Creates an object of the InitializePlayer class.
   *
   * @param initializeRooms initialized rooms in the game
   */
  public InitializePlayer(InitializeRooms initializeRooms, InitializeItems initializeItems) {
    this.initializeRooms = initializeRooms;
    this.initializeItems = initializeItems;
    createPlayer();
    setUpPlayerInventory();
  }

  /** Creates the player. */
  private void createPlayer() {
    player = new Player(initializeRooms.getRoomByName("entrance"), 3, "Player");
  }

  private void setUpPlayerInventory() {
    player.getInventory().addItemToInventory(initializeItems.getItemByName("Beamer"));
  }

  /**
   * Gets the player as a Player object.
   *
   * @return the player
   */
  public Player getPlayer() {
    return player;
  }
}
