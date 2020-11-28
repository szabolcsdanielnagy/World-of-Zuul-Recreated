package com.company;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is responsible for the inventory system in the game. Inventories have a size and a
 * list of the items in them. Items can be added to and removed from the inventory. Items can only
 * be added if the inventory can carry them.
 *
 * <p>This class is part of the "World of Zuul" text based adventure game.
 *
 * @author Szabolcs D. Nagy
 * @version 21.10.2020
 */
public class Inventory {
  private final int inventorySize;
  private HashMap<Item, Integer> items;
  private int currentWeightOfInventory;

  /**
   * Constructor for the class
   *
   * @param inventorySize size of the inventory
   */
  public Inventory(int inventorySize) {
    items = new HashMap<>();
    currentWeightOfInventory = 0;
    this.inventorySize = inventorySize;
  }

  /**
   * Method that adds an item to the inventory iff it has enough space.
   *
   * @param item item added to the inventory
   */
  public void addItemToInventory(Item item) {
    if (canAddItemToInventory(item)) {
      if (items.containsKey(item)) {
        items.put(item, items.get(item) + 1);
      } else {
        items.put(item, 1);
      }
      currentWeightOfInventory += item.getWeight();
    } else System.out.println("Can't carry anymore.");
  }

  /**
   * Method that checks whether an item can be added to an inventory.
   *
   * @param item item to be added to the inventory
   * @return true if the item can be added, false if it is not possible
   */
  public boolean canAddItemToInventory(Item item) {
    return inventorySize >= currentWeightOfInventory + item.getWeight();
  }

  /**
   * Method that removes an item from an inventory, iff the inventory contains the item.
   *
   * @param item item to be removed from the inventory
   */
  public void removeItemFromInventory(Item item) {
    if (items.containsKey(item)) {
      currentWeightOfInventory -= item.getWeight();
      items.remove(item);
    } else System.out.println("You don't have that item on you.");
  }

  /**
   * Method that returns the inventory as a HashMap.
   * @return inventory
   */
  public HashMap<Item, Integer> getInventory() {
    return items;
  }

  public void printInventory() {
    if (items.isEmpty()) {
      System.out.println("Your inventory is empty!");
    } else {
      for (Map.Entry<Item, Integer> item : items.entrySet()) {
        System.out.println(
            ""
                + item.getKey().getFullDescriptionWithoutWeight()
                + " Weight: "
                + item.getKey().getWeight() * item.getValue()
                + " Number of item(s) held:"
                + item.getValue());
      }
    }
  }
}
