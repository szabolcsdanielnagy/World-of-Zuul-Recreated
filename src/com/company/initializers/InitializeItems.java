package com.company.initializers;

import com.company.Item;

import java.util.ArrayList;

public class InitializeItems {
  private ArrayList<Item> items;
  private Item bread, wand, map, lifeOrb, apple;
  private String drawnMap;

  public InitializeItems() {
    drawMap();
    createItems();
  }

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
                "The magical wand teleported you out of the dungeon. You find yourself in a forest, you are finally free . . . "));
    items.add(map = new Item("Map", "The map of the dungeon. Let's get out of here!", 1, drawnMap));
    items.add(
        lifeOrb = new Item("Life orb", "This might save you in the future!", 999, "Extra health."));
    items.add(
        apple =
            new Item("Apple", "Do not try to eat it, it is cursed!", 1, "You ate the apple.")); //
  }

  /**
   * * Method that returns the map of the game. (The game contains a "Map" item, which upon use
   * prints * this string.)
   *
   * @return the map
   */
  private void drawMap() {
    drawnMap =
        ":::'######:'######:'######:'######:'######:'######:'######:'######:::"
            + "\n"
            + "::: ##::GRAVE:::##: ##::::::::::##  ##:::ALTAR::##: ##::CASTLE::##:::"
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

  public ArrayList<Item> getItems() {
    return items;
  }

  public Item getItemByName(String name) {
    for (Item item : items) {
      if (item.getName().equals(name)) {
        return item;
      }
    }
    return null;
  }
}
