package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * This class represents one location in the scenery of the game. It is connected to other rooms *
 * via exits. For each existing exit, the room stores a reference to the neighboring room. The rooms
 * have a list of items they contain.
 *
 * <p>This class is part of the "World of Zuul" text based adventure game.
 *
 * @author Michael Kölling and David J. Barnes (Modified by: Szabolcs D. Nagy)
 * @version 21.11.2020
 */
public class Room {
  private final String description;
  private final HashMap<String, Room> exits; // stores exits of this room.
  private final ArrayList<Item> items;
  private boolean isATeleporter;

  /**
   * Create a room described "description". Initially, it has no exits. "description" is something
   * like "a kitchen" or "an open court yard".
   *
   * @param description The room's description.
   */
  public Room(String description) {
    this.description = description;
    this.isATeleporter = false;
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
   * Add the given item to the room
   *
   * @param name - name of the item
   */
  public void addItemToRoom(Item name) {
    items.add(name);
  }

  public void removeItemFromRoom(Item name) {
    items.remove(name);
  }

  public ArrayList<Item> listAllItemsInRoom() {
    return items;
  }

  public boolean isTeleport() {
    return isATeleporter;
  }

  public void setTeleport(boolean magical) {
    this.isATeleporter = magical;
  }

  public HashMap<String, Room> getExits() {
    return exits;
  }
}
