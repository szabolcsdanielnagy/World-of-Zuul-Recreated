package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This class is the main class of the "World of Zuul" text based adventure game. The player can
 * walk around rooms, pickup / drop items, talk / interact with NPCs or give items to them. The main
 * objective of the game is to get out of the dungeon.
 *
 * <p>To play this game, create an instance of this class and call the "play" method.
 *
 * <p>This main class creates the parser and starts the game. It also evaluates and executes the
 * commands that the parser returns.
 *
 * @author Michael Kölling and David J. Barnes (Modified by: Szabolcs D. Nagy)
 * @version 21.20.2020
 */
public class Game {
  private final Parser parser;
  private final Initializer initializer;
  private final Player player;
  private boolean wantToQuit;

  /**
   * Constructor for the Game. Creates an object of the initializer, parser and the player class.
   */
  public Game() {
    initializer = new Initializer();
    parser = new Parser();
    player = initializer.getInitializedPlayer();
  }

  /** Main play routine. Loops until end of play. */
  public void play() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Please enter your character's name: ");
    String playerName = scanner.nextLine();
    player.setName(playerName);
    System.out.println("The name of your character is: " + playerName);
    printWelcome();
    // Enter the main command loop.  Here we repeatedly read commands and
    // execute them until the game is over.
    while (!wantToQuit && !(endGameScenario())) {
      Command command = parser.getCommand();
      wantToQuit = processCommand(command);
    }
    System.out.println("Thank you for playing.  Do you want to play again? Type 'yes' or 'no'");

    String input = scanner.nextLine();
    boolean isValid = false;
    do {
      if (input.equals("yes")) {
        isValid = true;
        play();
      } else if (input.equals("no")) {
        isValid = true;
        System.exit(0);
      } else {
        System.out.println("Please provide a valid answer! ('yes' / 'no')");
        input = scanner.nextLine();
      }
    } while (!isValid);
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
    System.out.println(player.getCurrentRoom().getLongDescription());
  }

  /**
   * Given a command, process (that is: execute) the command. The HashMap contains each command as a
   * key, and each method to be run as a value. Solution to store methods in a HashMap found at:
   * https://stackoverflow.com/questions/41206630/java-8-store-method-in-hashmap-and-get-return-value-from-method-in-map/41206653
   *
   * @param command The command to be processed.
   * @return true If the command ends the game, false otherwise.
   */
  private boolean processCommand(Command command) {
    CommandWord commandWord = command.getCommandWord();

    HashMap<CommandWord, Runnable[]> commands = new HashMap<>();
    commands.put(
        CommandWord.UNKNOWN,
        new Runnable[] {() -> System.out.println("I don't know what you mean...")});
    commands.put(CommandWord.LOOK, new Runnable[] {this::printLocationInfo});
    commands.put(CommandWord.HELP, new Runnable[] {this::printHelp});
    commands.put(
        CommandWord.GO, new Runnable[] {() -> player.goRoom(command, player, initializer, this)});
    commands.put(
        CommandWord.QUIT, new Runnable[] {() -> quit(command), () -> setWantToQuit(quit(command))});
    commands.put(CommandWord.PICKUP, new Runnable[] {() -> player.pickUpItem(command)});
    commands.put(CommandWord.INVENTORY, new Runnable[] {player::printInventory});
    commands.put(
        CommandWord.USE, new Runnable[] {() -> player.useItem(command, player, initializer)});
    commands.put(CommandWord.BACK, new Runnable[] {player::goBack, this::printLocationInfo});
    commands.put(
        CommandWord.INTERACT, new Runnable[] {() -> player.interactNpc(command, initializer)});
    commands.put(CommandWord.GIVE, new Runnable[] {() -> player.giveItem(command, initializer)});
    commands.put(CommandWord.DROP, new Runnable[] {() -> player.dropItem(command)});

    for (Map.Entry<CommandWord, Runnable[]> element : commands.entrySet()) {
      if (element.getKey().equals(commandWord)) {
        for (int i = 0; i < element.getValue().length; i++) {
          element.getValue()[i].run();
        }
      }
    }
    // else command not recognised.
    return wantToQuit;
  }

  // implementations of user commands:

  /** Print out some help information. We print the list of the command words. */
  private void printHelp() {
    System.out.println(player.getName() + " you are lost in the dungeon.");
    System.out.println("The following commands might help you:");
    parser.showCommands();
  }

  /** Method for printing out the details of the current room the player is in. */
  public void printLocationInfo() {
    System.out.println("");
    System.out.println(player.getCurrentRoom().getLongDescription());
    ArrayList<NPC> npcs = initializer.getInitializedNpcs();
    for (NPC npc : npcs) {
      if (player.getCurrentRoom().equals(npc.getCurrentRoom())) {
        System.out.println(npc.longDescriptionOfNpc());
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
    }
    return true; // signal that we want to quit
  }

  private void setWantToQuit(boolean wantToQuit) {
    this.wantToQuit = wantToQuit;
  }

  /**
   * Method to get whether the game should be ended or not.
   *
   * @return true if the game should be ended, false otherwise
   */
  public boolean endGameScenario() {
    int maxMovement = 3;
    if (player.getMovementCount() > maxMovement) {
      System.out.println(
          "You have reached the maximum steps you can take, now the game will exit.");
      return true;
    }
    ArrayList<Item> items = initializer.getInitializedItems();
    boolean endGameItemUsed = false;
    for (Item item : items) {
      if (item.getName().equals("Wand")) {
        endGameItemUsed = true;
        break;
      }
    }
    return !(endGameItemUsed);
  }
}
