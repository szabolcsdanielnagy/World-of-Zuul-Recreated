package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
public class Player extends Character {

  private Stack<Room> previousRooms;
  private int movementCount;

  /**
   * Constructor of the class
   *
   * @param currentRoom default room
   * @param inventorySize size of the inventory of the player
   */
  public Player(Room currentRoom, int inventorySize, String name) {
    super(inventorySize,currentRoom, name);
    movementCount = 0;
    previousRooms = new Stack<>();
  }

  /** Method that prints the inventory of the player. */
  public void printInventory() {
    getInventory().printInventory();
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
    setCurrentRoom(previousRooms.pop());
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

  /**
   * This command is responsible for giving an item from the players inventory to another character.
   * The item is given to the other character iff they are in the same room.
   *
   * @param command the id of the item and the id of the npc
   */
  public void giveItem(Command command, ArrayList<NPC> npcs) {
    if (!command.hasSecondWord()) {
      System.out.println("Give what? (Type: give [id of item] [id of npc])");
      return;
    }
    if (command.hasSecondWord() && command.hasThirdWord()) {
      HashMap<Item, Integer> inventory = this.getInventoryAsHashMap();
      int tmpId = Integer.parseInt(command.getThirdWord());
      int i = 0;
      boolean exists = false;
      NPC npc = null;
      while (i < npcs.size()) {
        if (npcs.get(i).getNpcId() == tmpId) {
          npc = npcs.get(i);
          exists = true;
          break;
        } else i++;
      }
      if (exists) {
        for (Map.Entry<Item, Integer> item : inventory.entrySet()) {
          if (item.getKey().getId() == Integer.parseInt(command.getSecondWord())
                  && npc.getCurrentRoom().equals(this.getCurrentRoom())) {
            this.dropItem(item.getKey());
            npc.pickUpItem(item.getKey());
            break;
          }
        }
      }
    }
  }

  @Override
  /**
   * With this method the player can pick up an item. If the player and the item is in the same
   * room, and they player can also carry the weight of the item it gets picked up by the player.
   *
   * @param command the id of the item
   */
  public void pickUpItem(Command command) {
    if (!command.hasSecondWord()) {
      System.out.println("Pickup what? (Type 'pickup [id of item]'");
      return;
    }
    for (Item item : this.getCurrentRoom().listAllItemsInRoom()) {
      if (item.getId() == Integer.parseInt(command.getSecondWord())) {
        if (this.getInventory().canAddItemToInventory(item)) {
          this.pickUpItem((item));
          this.getCurrentRoom().removeItemFromRoom(item);
          break;
        }
      }
    }
  }

  @Override
  public void pickUpItem(Item item) {
    //
  }

  /**
   * This method is responsible for dropping an item from the player's inventory. Only possible if
   * the player has the item.
   *
   * @param command the id of the item
   */
  public void dropItem(Command command) {
    if (!command.hasSecondWord()) {
      System.out.println("Drop what? (Type: drop [id of item])");
    }
    for (Map.Entry<Item, Integer> item : this.getInventoryAsHashMap().entrySet()) {
      if (item.getKey().getId() == Integer.parseInt(command.getSecondWord())) {
        System.out.println("You dropped a(n):" + item.getKey().getName());
        this.dropItem(item.getKey());
        this.getCurrentRoom().addItemToRoom(item.getKey());
        break;
      }
    }
  }
}
