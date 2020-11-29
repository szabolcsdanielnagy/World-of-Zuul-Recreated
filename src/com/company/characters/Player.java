package com.company.characters;

import com.company.Game;
import com.company.Item;
import com.company.Room;
import com.company.commands.Command;
import com.company.initializers.Initializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * This class represents the Player in the game. This class extends the Character class. Players
 * have a stack of previous rooms and a movement counter. The class contains methods which are used
 * upon typing a command in the game.
 *
 * @author Szabolcs D. Nagy
 * @version 29.11.2020
 */
public class Player extends Character {

  // Holds the previous rooms of the character in a stack. (Used with the 'back' command)
  private Stack<Room> previousRooms;
  // Number of moves the player has made.
  private int movementCount;

  /**
   * Create an object of the Player class.
   *
   * @param currentRoom the default room of the player
   * @param inventorySize the size of the inventory
   * @param name name of the player (can be changed when game starts)
   */
  public Player(Room currentRoom, int inventorySize, String name) {
    super(inventorySize, currentRoom, name);
    movementCount = 0;
    previousRooms = new Stack<>();
  }

  /** Prints the inventory of the character. */
  public void printInventory() {
    getInventory().printInventory();
  }

  /**
   * Adds the room to the previous rooms stack.
   *
   * @param room the room to be added
   */
  public void addRoomToPreviousRooms(Room room) {
    this.previousRooms.push(room);
  }

  /** Clears the previous rooms stack. */
  public void removeAllPreviousRooms() {
    this.previousRooms = new Stack<>();
  }

  /**
   * Sets the player's current room as the previous room. If the stack is empty it prints out that
   * it is not possible.
   */
  public void goBack() {
    if (previousRooms.empty()) {
      System.out.println("No where to go back");
      return;
    }
    setCurrentRoom(previousRooms.pop());
  }

  /**
   * Gets the number of moves the player has made.
   *
   * @return the number of moves
   */
  public int getMovementCount() {
    return movementCount;
  }

  /** Increments the player's movement by one. */
  public void incrementMovementCount() {
    movementCount++;
  }

  /**
   * The player tries to give an item. Reads the command. If it is valid, it removes an item from
   * the player's inventory and adds it to the target NPC. (Only works if the player and the target
   * NPC are in the same room.)
   *
   * @param command command to be read
   * @param initializer initializer to get the initialized NPCs
   */
  public void giveItem(Command command, Initializer initializer) {
    if (!command.hasSecondWord()) {
      System.out.println("Give what? (Type: give [id of item] [id of npc])");
      return;
    }
    if (command.hasSecondWord() && command.hasThirdWord()) {
      ArrayList<NPC> npcs = initializer.getInitializedNpcs().getNpcs();
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

  /**
   * The player tries to pick up an item. Reads the command. If it is valid the player picks up the
   * item from the ground. (Only works if the player can carry the item, which has to be in the same
   * room as the player.)
   *
   * @param command command to be read
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

  /**
   * The player tries to drop an item. Reads the command. If it is valid the player drops the item
   * on the ground. (Only works if the player has the item.)
   *
   * @param command
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

  /**
   * The player tries to interact with an NPC. Reads the command. If the player and the NPC are in
   * the same room the player interacts with the NPC.
   *
   * @param command command to be read
   * @param initializer initializer to get the initialized NPCs
   */
  public void interactNpc(Command command, Initializer initializer) {
    if (!command.hasSecondWord()) {
      System.out.println("Interact with whom? (Type: interact [id of npc])");
      return;
    }
    ArrayList<NPC> npcs = initializer.getInitializedNpcs().getNpcs();
    for (NPC npc : npcs) {
      if (npc.getCurrentRoom().equals(getCurrentRoom())) {
        System.out.println(npc.getInteractionMessage());
        break;
      }
    }
  }

  /**
   * The player tries to go in a room. Reads the command. If it is valid and the direction is
   * possible the player moves that way. The game prints the next location's information. Upon
   * movement all the NPCs in the game also move.
   *
   * @param command command to be read
   * @param player player to be moved
   * @param initializer initializer to get the initialized NPCs.
   * @param game game to print the location information
   */
  public void goRoom(Command command, Player player, Initializer initializer, Game game) {
    if (!command.hasSecondWord()) {
      // no second word -> nowhere to go
      System.out.println("Go where? (Type: go [direction])");
      return;
    }
    String direction = command.getSecondWord();
    // Try to leave current room.
    Room nextRoom = player.getCurrentRoom().getExit(direction);
    if (nextRoom == null) {
      System.out.println("There is no door!");
    } else if (nextRoom.isLocked()) {
      System.out.println("The door is locked! If you have a key use it to unlock it.");
    } else {
      player.incrementMovementCount();
      initializer.getInitializedNpcs().npcMovement();
      if (nextRoom.isTeleport()) {
        System.out.println("You are being teleported to a room . . .");
        player.removeAllPreviousRooms();
        player.setCurrentRoom(
            initializer.getInitializedRooms().getARandomRoomFromInitializedRooms());
      } else {
        player.addRoomToPreviousRooms(player.getCurrentRoom());
        player.setCurrentRoom(nextRoom);
      }
      game.printLocationInfo();
    }
  }

  /**
   * The player uses an item. Reads the command. If it is valid, the player uses the item. (Only
   * possible if the player has the item in their inventory.)
   *
   * @param command command to be read
   * @param player player which uses the item
   * @param initializer initalizer to remove the item from the initialized items
   */
  public void useItem(Command command, Player player, Initializer initializer) {
    if (!command.hasSecondWord()) {
      System.out.println("Use what? (Type: use [id of item])");
      return;
    }
    for (Map.Entry<Item, Integer> item : player.getInventoryAsHashMap().entrySet()) {
      if (item.getKey().getId() == Integer.parseInt(command.getSecondWord())) {
        System.out.println("You used item ID:" + item.getKey().getId());
        System.out.println(item.getKey().getEffect());
        player.dropItem(item.getKey());
        initializer.getInitializedItems().getItems().remove(item.getKey());
        break;
      }
    }
  }
}
