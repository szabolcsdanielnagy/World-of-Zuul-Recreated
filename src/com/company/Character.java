package com.company;

import java.util.HashMap;

public abstract class Character {
  private Inventory inventory;
  private Room currentRoom;
  private String name;

  public Character(int inventorySize, Room currentRoom, String name) {
    inventory = new Inventory(inventorySize);
    this.currentRoom = currentRoom;
    this.name = name;
  }

  public HashMap<Item, Integer> getInventoryAsHashMap() {
    return inventory.getInventory();
  }

  /**
   * Method responsible for returning the inventory of the player as an Inventory object.
   *
   * @return inventory of the character
   */
  public Inventory getInventory() {
    return inventory;
  }

  /**
   * Method responsible for picking up an item.
   *
   * @param item to be picked up
   */
  public abstract void pickUpItem(Item item) ;
  public abstract void pickUpItem(Command command);

  /**
   * Method responsible for removing an item from the inventory of the npc.
   *
   * @param item item to be removed
   */
  public void dropItem(Item item) {
    inventory.removeItemFromInventory(item);
  }

  /**
   * Method that gets the location of a character.
   *
   * @return location of the npc
   */
  public Room getCurrentRoom() {
    return currentRoom;
  }

  /**
   * Method that sets the location of an npc.
   *
   * @param currentRoom new location of the npc.
   */
  public void setCurrentRoom(Room currentRoom) {
    this.currentRoom = currentRoom;
  }

  public String getName() {
    return name;
  }
}
