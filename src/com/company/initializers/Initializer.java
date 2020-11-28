package com.company.initializers;

/**
 * This class creates and initializes all the rooms, players, NPCs and items. It contains a method
 * to set an end-game scenario for the game, and a method to control the movement of all the NPCs in
 * the game.
 *
 * <p>This class is part of the "World of Zuul" text based adventure game.
 *
 * @author Szabolcs D. Nagy
 * @version 21.20.2020
 */
public class Initializer {
  private InitializeRooms initializeRooms;
  private InitializeItems initializeItems;
  private InitializePlayer initializePlayer;
  private InitializeNpcs initializeNpcs;

  /** Constructor for the class, creates the rooms, players, items and initializes them. */
  public Initializer() {
    initializeItems = new InitializeItems();
    initializeRooms = new InitializeRooms(initializeItems);
    initializeNpcs = new InitializeNpcs(initializeRooms, initializeItems);
    initializePlayer = new InitializePlayer(initializeRooms);
  }

  public InitializeRooms getInitializedRooms() {
    return initializeRooms;
  }

  public InitializeItems getInitializedItems() {
    return initializeItems;
  }

  public InitializePlayer getInitializedPlayer() {
    return initializePlayer;
  }

  public InitializeNpcs getInitializedNpcs() {
    return initializeNpcs;
  }
}
