package com.company;

import java.util.HashMap;

/**
 * This class is responsible for the NPCs in the game. They can have names, location they are at,
 * inventories, messages, interaction messages and they can also move on their own if they are
 * 'moving' NPCs.
 *
 * <p>This class is part of the "World of Zuul" text based adventure game.
 *
 * @author Szabolcs D. Nagy
 * @version 21.10.2020
 */
public class NPC {
  private static int generateId = 0;
  private final String name;
  private final int npcId;
  private Room location;
  private Inventory inventory;
  private String message;
  private String interactionMessage;
  private boolean moving; // Determine whether an NPC moves or stays in one place

  /**
   * Constructor of the class
   *
   * @param name name of the npc
   * @param message default message of the npc (shown if a player enter the same room as the npc)
   * @param interactionMessage interaction message when a player interacts with the npc
   * @param location location of the npc
   */
  public NPC(String name, String message, String interactionMessage, Room location) {
    generateId++;
    npcId = generateId;
    this.location = location;
    this.message = message;
    this.interactionMessage = interactionMessage;
    this.name = name;
    this.inventory = new Inventory(999);
    moving = false;
  }

  /**
   * Method that gets the location of an npc.
   *
   * @return location of the npc
   */
  public Room getLocation() {
    return location;
  }

  /**
   * Method that sets the location of an npc.
   *
   * @param location new location of the npc.
   */
  public void setLocation(Room location) {
    this.location = location;
  }

  /**
   * Method that gets the ID of the npc.
   *
   * @return ID of the npc.
   */
  public int getNpcId() {
    return npcId;
  }

  /**
   * Method that gets the interaction message of the npc.
   *
   * @return interaction message of the npc.
   */
  public String getInteractionMessage() {
    return getName() + ":" + interactionMessage;
  }

  /**
   * Method that gets the message of the npc.
   *
   * @return message of the npc.
   */
  public String getMessage() {
    return message;
  }

  /**
   * Method responsible for adding an item to the inventory of the npc.
   *
   * @param item going to be added
   */
  public void addItemToInventory(Item item) {
    inventory.addItemToInventory(item);
  }

  /**
   * Method responsible for removing an item from the inventory of the npc.
   *
   * @param item item to be removed
   */
  public void removeItemFromInventory(Item item) {
    inventory.removeItemFromInventory(item);
  }

  /**
   * Method that returns the name of the npc.
   *
   * @return name of the npc
   */
  public String getName() {
    return name;
  }

  /**
   * Method that returns the id, name and message of the npc
   *
   * @return id, name, message
   */
  public String longDescriptionOfNpc() {
    return "ID:" + getNpcId() + " " + getName() + ":" + getMessage();
  }

  /**
   * Method that returns the inventory of an npc as a HashMap.
   *
   * @return inventory of npc
   */
  public HashMap<Item, Integer> getInventory() {
    return inventory.getInventory();
  }

  /**
   * Method that checks whether an npc moves automatically or not.
   *
   * @return true if it is moving, false if it is not
   */
  public boolean isMoving() {
    return moving;
  }

  /**
   * Method that sets whether an npc moves or automatically or not.
   *
   * @param moving true if moving, false if not moving
   */
  public void setMoving(boolean moving) {
    this.moving = moving;
  }
}
