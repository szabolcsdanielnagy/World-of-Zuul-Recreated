package com.company.initializers;

import com.company.Item;

import java.util.ArrayList;

/**
 * This class initializes and creates the items which can be found in the game. It holds an
 * ArrayList of them.
 *
 * @author Szabolcs D. Nagy
 * @version 29.11.2020
 */
public class InitializeItems {
  private ArrayList<Item> items;
  private Item bread, wand, map, key, thorHammer, beamer;
  // The game has a 'Map' item. This variable holds the map of the game as a String.
  private String drawnMap;

  /** Creates an object of the InitializeItems class. */
  public InitializeItems() {
    drawMap();
    createItems();
  }

  /** Creates the items which can be found in the game. */
  private void createItems() {
    items = new ArrayList<>();
    items.add(
        bread =
            new Item(
                "Bread", "Go and talk to the dwarf he might like it.", 1, "You ate the bread."));
    items.add(
        wand =
            new Item(
                "Wand",
                "This will help you get out of the dungeon. ",
                1,
                "The magical wand teleported you out of the dungeon."
                    + " You find yourself in a forest, you are finally free . . . "));
    items.add(
        map = new Item("Map", "The map of the dungeon. " + "Let's get out of here!", 1, drawnMap));
    items.add(
        key = new Item("Key", "This is a magical key. It can open any doors.", 1, "Key used."));
    items.add(
        thorHammer =
            new Item(
                "Thor's hammer",
                "Only the worthy can wield this hammer.",
                999,
                "You smashed on the ground with the hammer.")); //
    items.add(
        beamer =
            new Item(
                "Beamer",
                "This is a beamer. "
                    + "It can be charged and fired. If you charge it the beamer memorizes your current location."
                    + " Upon firing it teleports you back to the location",
                1,
                "Beamer fired."));
  }

  /** Draws the map of the game for the 'Map' item. */
  private void drawMap() {
    drawnMap =
        ":::'######:'######:'######:'######:'######:'######:'######:'######:::"
            + "\n"
            + "::: ##::GRAVE:::##: ##::::::::::##  ##:::ALTAR::##: ##:::HOLE:::##:::"
            + "\n"
            + "::: ##:::YARD:::##: ##::CRYPTS::##: ##::::::::::##: ##::::::::::##:::"
            + "\n"
            + "::: ######:'######: ######:'######: ######:'######: ######:'######:::"
            + "\n"
            + ":::......::......::......::......::......::......::......::......::::"
            + "\n"
            + ":::'######:'######:'######:'######:'######:'######:'######:'######:::"
            + "\n"
            + "::: ##::LOWER:: ##: ##::::::::::##: ##:UPPER::::##: ##::::::::::##:::"
            + "\n"
            + "::: ##:BASEMENT:##: ##::CHAPEL::##: ##:basement:##: ##:::CAVE:::##:::"
            + "\n"
            + "::: ######:'######: ######:'######: ######:'######: ######:'######:::"
            + "\n"
            + ":::......::......::......::......::......::......::......::......::::"
            + "\n"
            + ":::'######:'######:'######:'######:'######:'######:'######:'######:::"
            + "\n"
            + "::: ##::STORAGE:##: ##::::::::::##: ##::HIDDEN::##: ##:TELEPORT:##:::"
            + "\n"
            + "::: ##:::ROOM:::##: ##:ENTRANCE:##: ##:::ROOM:::##: ##:::ROOM:::##:::"
            + "\n"
            + "::: ######:'######: ######:'######: ######:'######: ######:'######:::";
  }

  /**
   * Gets the items created in the game as an ArrayList.
   *
   * @return the list of the items
   */
  public ArrayList<Item> getItems() {
    return items;
  }

  /**
   * Searches for an item in the created items by a String.
   *
   * @param name name of the item
   * @return the item if the item is found, null otherwise
   */
  public Item getItemByName(String name) {
    for (Item item : items) {
      if (item.getName().equals(name)) {
        return item;
      }
    }
    return null;
  }
}
