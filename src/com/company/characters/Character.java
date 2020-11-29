package com.company.characters;

import com.company.Inventory;
import com.company.Item;
import com.company.Room;

import java.util.HashMap;

/**
 * This class represents the characters in the game. They have an inventory, the current room they
 * are in and a name.
 *
 * @author Szabolcs D. Nagy
 * @version 29.11.2020
 */
public abstract class Character {
  private final Inventory inventory;
  private Room currentRoom;
  private String name;

  /**
   * Create an object of the Character class.
   *
   * @param inventorySize the size of the inventory
   * @param currentRoom the default room of the character
   * @param name the name of the character
   */
  public Character(int inventorySize, Room currentRoom, String name) {
    inventory = new Inventory(inventorySize);
    this.currentRoom = currentRoom;
    this.name = name;
  }

  /**
   * Gets the character's inventory as a HashMap.
   *
   * @return inventory of the character
   */
  public HashMap<Item, Integer> getInventoryAsHashMap() {
    return inventory.getInventory();
  }

  /**
   * Gets the character's inventory as an Inventory object.
   *
   * @return inventory of the character
   */
  public Inventory getInventory() {
    return inventory;
  }

  /**
   * Adds an item to the inventory of the character.
   *
   * @param item the item to be added
   */
  public void pickUpItem(Item item) {
    this.getInventory().addItemToInventory(item);
  }

  /**
   * Removes an item from the inventory of the character.
   *
   * @param item the item to be removed
   */
  public void dropItem(Item item) {
    inventory.removeItemFromInventory(item);
  }

  /**
   * Gets the current room of the character.
   *
   * @return the current room
   */
  public Room getCurrentRoom() {
    return currentRoom;
  }

  /**
   * Sets the current room of the character.
   *
   * @param currentRoom the room to be set as the current room
   */
  public void setCurrentRoom(Room currentRoom) {
    this.currentRoom = currentRoom;
  }

  /**
   * Gets the name of the character.
   *
   * @return name of the character
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the character.
   *
   * @param name the name to be set
   */
  public void setName(String name) {
    this.name = name;
  }
}
