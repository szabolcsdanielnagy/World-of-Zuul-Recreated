package com.company;

import java.util.HashMap;
import java.util.Stack;

/**
 * This class is responsible for the players in the game. Players have an inventory, a location and
 * a movement counter, which counts how many steps the player has taken.
 *
 * <p>This class is part of the "World of Zuul" text based adventure game.
 *
 * @author Szabolcs D. Nagy
 * @version 21.10.2020
 */
public class Player {

  private Inventory inventory;
  private Room currentRoom;
  private Stack<Room> previousRooms;
  private int movementCount;

  /**
   * Constructor of the class
   *
   * @param room default room
   * @param inventorySize size of the inventory of the player
   */
  public Player(Room room, int inventorySize) {
    movementCount = 0;
    inventory = new Inventory(inventorySize);
    this.currentRoom = room;
    previousRooms = new Stack<>();
  }

  /**
   * Method that returns the inventory of the player as a HashMap.
   *
   * @return inventory of the character
   */
  public HashMap<Item, Integer> characterInventory() {
    return inventory.getInventory();
  }

  /**
   * Method responsible for returning the inventory of the player as an Inventory object.
   *
   * @return inventory of the character
   */
  public Inventory getCharacterInventory() {
    return inventory;
  }

  /** Method that prints the inventory of the player. */
  public void printInventory() {
    inventory.printInventory();
  }

  /**
   * Method responsible for picking up an item.
   *
   * @param item to be picked up
   */
  public void pickUpItem(Item item) {
    inventory.addItemToInventory(item);
  }

  /**
   * Method responsible for removing an item from the player's inventory.
   *
   * @param item to be dropped
   */
  public void dropItem(Item item) {
    inventory.removeItemFromInventory(item);
  }

  /**
   * Method for getting the current room of the player.
   *
   * @return current room of the player
   */
  public Room getRoom() {
    return currentRoom;
  }

  /**
   * Method for setting the player's current room to another room
   *
   * @param room next room of the player
   */
  public void setRoom(Room room) {
    this.currentRoom = room;
  }

  /**
   * Method that adds a room to the previous rooms of the player
   *
   * @param room room to be added
   */
  public void addRoomsToPreviousRooms(Room room) {
    this.previousRooms.push(room);
  }

  /** Method that creates a new stack of the previous rooms, which also means removing them. */
  public void removePreviousRooms() {
    this.previousRooms = new Stack<>();
  }

  /**
   * Method that sets the players current room to the previous room. If there is no previous room it
   * prints out that there is none.
   */
  public void goBack() {
    if (previousRooms.empty()) {
      System.out.println("No where to go back");
      return;
    }
    setRoom(previousRooms.pop());
  }

  /**
   * Method that returns the number of movements the player has moved.
   *
   * @return number of movement
   */
  public int getMovementCount() {
    return movementCount;
  }

  /**
   * Method that increases the movement of the player.
   */
  public void incrementMovementCount() {
    movementCount++;
  }
}
