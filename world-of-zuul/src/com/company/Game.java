package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is the main class of the "World of Zuul" application. "World of Zuul" is a very
 * simple, text based adventure game. Users can walk around some scenery. That's all. It should
 * really be extended to make it more interesting!
 *
 * <p>To play this game, create an instance of this class and call the "play" method.
 *
 * <p>This main class creates and initialises all the others: it creates all rooms, creates the
 * parser and starts the game. It also evaluates and executes the commands that the parser returns.
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class Game {
  private final Parser parser;
  private final Initializer initializer;
  private final Player player;

  /** Create the game. */
  public Game() {
    initializer = new Initializer();
    parser = new Parser();
    player = initializer.getInitializedPlayer();
  }

  /** Main play routine. Loops until end of play. */
  public void play() {
    printWelcome();
    // Enter the main command loop.  Here we repeatedly read commands and
    // execute them until the game is over.
    boolean finished = false;
    while (!finished) {
      Command command = parser.getCommand();
      finished = processCommand(command);
    }
    System.out.println("Thank you for playing.  Good bye.");
  }

  /** Print out the opening message for the player. */
  private void printWelcome() {
    System.out.println();
    System.out.println("Welcome to the World of Zuul!");
    System.out.println(" _       __           __    __         ____   _____               __");
    System.out.println("| |     / /___  _____/ /___/ /  ____  / __/  /__  /  __  ____  __/ /");
    System.out.println("| | /| / / __ \\/ ___/ / __  /  / __ \\/ /_      / /  / / / / / / / / ");
    System.out.println("| |/ |/ / /_/ / /  / / /_/ /  / /_/ / __/     / /__/ /_/ / /_/ / /  ");
    System.out.println("|__/|__/\\____/_/  /_/\\__,_/   \\____/_/       /____/\\__,_/\\__,_/_/ ");
    System.out.println("Type 'help' if you need help.");
    System.out.println();
    System.out.println(player.getLocation().getLongDescription());
  }

  /**
   * Given a command, process (that is: execute) the command.
   *
   * @param command The command to be processed.
   * @return true If the command ends the game, false otherwise.
   */
  private boolean processCommand(Command command) {
    boolean wantToQuit = false;

    if (command.isUnknown()) {
      System.out.println("I don't know what you mean...");
      return false;
    }

    String commandWord = command.getCommandWord();

    switch (commandWord) {
      case "look":
        printLocationInfo();
        break;
      case "help":
        printHelp();
        break;
      case "go":
        goRoom(command);
        break;
      case "quit":
        wantToQuit = quit(command);
        break;
      case "pickup":
        pickUpItem(command);
        break;
      case "inventory":
        player.printInventory();
        break;
      case "use":
        useItem(command);
        break;
      case "back":
        player.goBack();
        printLocationInfo();
        break;
      case "interact":
        interactNpc(command);
        break;
      case "give":
        giveItem(command);
        break;
      case "drop":
        dropItem(command);
        break;
    }
    // else command not recognised.
    return wantToQuit;
  }

  // implementations of user commands:

  /**
   * Print out some help information. Here we print some stupid, cryptic message and a list of the
   * command words.
   */
  private void printHelp() {
    System.out.println("You are lost in the dungeon.");
    System.out.println("The following commands might help you:");
    parser.showCommands();
  }

  /** Method for printing out the details of the current room the player is in. */
  private void printLocationInfo() {
    System.out.println("");
    initializer.refreshInteractionMessages();
    initializer.getInitializedPlayer().setLocation(player.getLocation());
    System.out.println(player.getLocation().getLongDescription());
    ArrayList<NPC> npcs = initializer.getInitializedNpcs();
    for (NPC npc : npcs) {
      if (player.getLocation().equals(npc.getLocation())) {
        System.out.println(npc.longDescriptionOfNpc());
      }
    }
  }

  /**
   * Try to interact with an NPC if the player and the NPC are in the same room.
   *
   * @param command - input command the user has given.
   */
  private void interactNpc(Command command) {
    if (!command.hasSecondWord()) {
      System.out.println("Interact with whom? (Type: interact [id of npc])");
      return;
    }
    ArrayList<NPC> npcs = initializer.getInitializedNpcs();
    for (NPC npc : npcs) {
      if (npc.getLocation().equals(player.getLocation())) {
        System.out.println(npc.getInteractionMessage());
        break;
      }
    }
  }

  /**
   * Try to go in one direction. If there is an exit, enter the new room, otherwise print an error
   * message.
   */
  private void goRoom(Command command) {
    if (!command.hasSecondWord()) {
      // no second word -> nowhere to go
      System.out.println("Go where? (Type: go [direction])");
      return;
    }
    String direction = command.getSecondWord();
    // Try to leave current room.
    Room nextRoom = player.getLocation().getExit(direction);
    if (nextRoom == null) {
      System.out.println("There is no door!");
    } else {

      if (nextRoom.isTeleport()) {
        System.out.println("You are being teleported to a room . . .");
        player.removePreviousRooms();
        player.setLocation(initializer.randomRoom());
      } else {
        player.addRoomsToPreviousRooms(player.getLocation());
        player.setLocation(nextRoom);
      }
      printLocationInfo();
    }
  }

  private void pickUpItem(Command command) {
    if (!command.hasSecondWord()) {
      System.out.println("Pickup what? (Type 'pickup [id of item]'");
      return;
    }
    for (Item item : player.getLocation().listAllItemsInRoom()) {
      if (item.getId() == Integer.parseInt(command.getSecondWord())) {
        if (player.getCharacterInventory().canPickUpItem(item)) {
          player.pickUpItem((item));
          player.getLocation().removeItemFromRoom(item);
          break;
        }
      }
    }
  }

  private void dropItem(Command command) {
    if (!command.hasSecondWord()) {
      System.out.println("Drop what? (Type: drop [id of item])");
    }
    for (Map.Entry<Item, Integer> item : player.characterInventory().entrySet()) {
      if (item.getKey().getId() == Integer.parseInt(command.getSecondWord())) {
        System.out.println("You dropped a(n):" + item.getKey().getName());
        player.dropItem(item.getKey());
        player.getLocation().addItemToRoom(item.getKey());
        break;
      }
    }
  }

  private void useItem(Command command) {
    if (!command.hasSecondWord()) {
      System.out.println("Use what? (Type: use [id of item])");
      return;
    }
    for (Map.Entry<Item, Integer> item : player.characterInventory().entrySet()) {
      if (item.getKey().getId() == Integer.parseInt(command.getSecondWord())) {
        System.out.println("You used item ID:" + item.getKey().getId());
        System.out.println(item.getKey().getEffect());
        player.dropItem(item.getKey());
        break;
      }
    }
  }

  private void giveItem(Command command) {
    if (!command.hasSecondWord()) {
      System.out.println("Give what? (Type: give [id of item] [id of npc])");
      return;
    }
    if (command.hasSecondWord() && command.hasThirdWord()) {
      HashMap<Item, Integer> inventory = player.characterInventory();
      ArrayList<NPC> npcs = initializer.getInitializedNpcs();
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
              && npc.getLocation().equals(player.getLocation())) {
            player.dropItem(item.getKey());
            npc.addItemToInventory(item.getKey());
            initializer.trade();
            break;
          }
        }
      }
    }
  }

  /**
   * "Quit" was entered. Check the rest of the command to see whether we really quit the game.
   *
   * @return true, if this command quits the game, false otherwise.
   */
  private boolean quit(Command command) {
    if (command.hasSecondWord()) {
      System.out.println("Quit what?");
      return false;
    } else {
      return true; // signal that we want to quit
    }
  }
}
