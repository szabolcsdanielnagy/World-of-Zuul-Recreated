package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * This class represents one location in the scenery of the game. It is connected to other rooms *
 * via exits. For each existing exit, the room stores a reference to the neighboring room. The rooms
 * have a list of items they contain. Rooms can also be locked.
 *
 * @author Michael Kölling and David J. Barnes (Modified by: Szabolcs D. Nagy)
 * @version 29.11.2020
 */
public class Room {
  private final String description;
  private final HashMap<String, Room> exits; // stores exits of this room.
  private final ArrayList<Item> items;
  private final String name;
  private boolean isATeleport;
  private boolean isLocked;

  /**
   * Create a room described "description". Initially, it has no exits. "description" is something
   * like "a kitchen" or "an open court yard".
   *
   * @param description The room's description.
   */
  public Room(String description, String name) {
    this.name = name;
    this.description = description;
    this.isATeleport = false;
    this.isLocked = false;
    this.exits = new HashMap<>();
    this.items = new ArrayList<>();
  }

  /**
   * Define an exit from this room.
   *
   * @param direction The direction of the exit.
   * @param neighbor The room to which the exit leads.
   */
  public void setExit(String direction, Room neighbor) {
    exits.put(direction, neighbor);
  }

  /**
   * Return a description of the room in the form: You are in the kitchen. Exits: north west
   *
   * @return A long description of this room
   */
  public String getLongDescription() {
    StringBuilder description =
        new StringBuilder("You are " + this.description + ".\n" + getExitString() + "\n");
    if (!items.isEmpty()) {
      int count = 0;
      description.append("The following item(s) can be found in the room: ").append("\n");
      for (Item item : items) {
        if (listAllItemsInRoom().contains(item)) {
          description.append(item.getFullDescription()).append("\n");
          count++;
        }
      }
      if (count == 0) {
        return "You are " + this.description + ".\n" + getExitString() + "\n";
      }
    }
    return description.toString();
  }

  /**
   * Return a string describing the room's exits, for example "Exits: north west".
   *
   * @return Details of the room's exits.
   */
  public String getExitString() {
    StringBuilder returnString = new StringBuilder("Exits:");
    Set<String> keys = exits.keySet();
    for (String exit : keys) {
      returnString.append(" ").append(exit);
    }
    return returnString.toString();
  }

  /**
   * Return the room that is reached if we go from this room in direction "direction". If there is
   * no room in that direction, return null.
   *
   * @param direction The exit's direction.
   * @return The room in the given direction.
   */
  public Room getExit(String direction) {
    return exits.get(direction);
  }

  /**
   * Adds an item to the room.
   *
   * @param name name of the item
   */
  public void addItemToRoom(Item name) {
    items.add(name);
  }

  /**
   * Removes an item from the room.
   *
   * @param name name of the item
   */
  public void removeItemFromRoom(Item name) {
    items.remove(name);
  }

  /**
   * Gets all the items in the room as a list
   *
   * @return list of the items
   */
  public ArrayList<Item> listAllItemsInRoom() {
    return items;
  }

  /**
   * Gets whether a room is a teleport or no.
   *
   * @return true if it is, false otherwise
   */
  public boolean isTeleport() {
    return isATeleport;
  }

  /**
   * Sets the room to a teleport as the given value.
   *
   * @param teleportValue the boolean value whether it is a teleport or not
   */
  public void setTeleport(boolean teleportValue) {
    this.isATeleport = teleportValue;
  }

  /**
   * Gets all the exits of a room as a HashMap.
   *
   * @return the exits of the room
   */
  public HashMap<String, Room> getExits() {
    return exits;
  }

  /**
   * Gets the name of the room as a String
   *
   * @return the name of the room
   */
  public String getName() {
    return name;
  }

  /**
   * Gets whether a room is locked.
   *
   * @return true if it is locked, false otherwise
   */
  public boolean isLocked() {
    return isLocked;
  }

  /**
   * Sets the locked status of the room to the given value
   *
   * @param isLocked locked status
   */
  public void setLocked(boolean isLocked) {
    this.isLocked = isLocked;
  }
}
