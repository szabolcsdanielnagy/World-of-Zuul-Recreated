package com.company.initializers;

/**
 * This class represents the Initializer in the game. It creates an object for each initializer
 * class.
 *
 * @author Szabolcs D. Nagy
 * @version 29.11.2020
 */
public class Initializer {
  private final InitializeRooms initializeRooms;
  private final InitializeItems initializeItems;
  private final InitializePlayer initializePlayer;
  private final InitializeNpcs initializeNpcs;

  /** Creates an object of the Initializer class. */
  public Initializer() {
    initializeItems = new InitializeItems();
    initializeRooms = new InitializeRooms(initializeItems);
    initializeNpcs = new InitializeNpcs(initializeRooms, initializeItems);
    initializePlayer = new InitializePlayer(initializeRooms, initializeItems);
  }

  /**
   * Gets the initialized rooms in the game.
   *
   * @return initialized rooms
   */
  public InitializeRooms getInitializedRooms() {
    return initializeRooms;
  }

  /**
   * Gets the initialized items in the game.
   *
   * @return initialized items
   */
  public InitializeItems getInitializedItems() {
    return initializeItems;
  }

  /**
   * Gets the initialized player in the game.
   *
   * @return initialized player
   */
  public InitializePlayer getInitializedPlayer() {
    return initializePlayer;
  }

  /**
   * Gets the initialized NPCs in the game.
   *
   * @return initialized NPCs
   */
  public InitializeNpcs getInitializedNpcs() {
    return initializeNpcs;
  }
}
