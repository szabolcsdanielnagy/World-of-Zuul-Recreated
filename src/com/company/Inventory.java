package com.company;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents the Inventory system in the game. Inventories have a size, a HashMap of the
 * items they contain, and a current weight.
 *
 * @author Szabolcs D. Nagy
 * @version 29.11.2020
 */
public class Inventory {
  private final int inventorySize;
  private final HashMap<Item, Integer> items;
  private int currentWeightOfInventory;

  /**
   * Creates an object of the Inventory class.
   *
   * @param inventorySize the size of the inventory
   */
  public Inventory(int inventorySize) {
    this.items = new HashMap<>();
    this.currentWeightOfInventory = 0;
    this.inventorySize = inventorySize;
  }

  /**
   * Adds an item to the inventory if the inventory can carry it.
   *
   * @param item item to be added
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
   * Checks whether an item can be added to an inventory.
   *
   * @param item item to be added to the inventory
   * @return true if the item can be added, false if it is not possible
   */
  public boolean canAddItemToInventory(Item item) {
    return inventorySize >= currentWeightOfInventory + item.getWeight();
  }

  /**
   * Removes an item from the inventory if it contains the item.
   *
   * @param item to be removed
   */
  public void removeItemFromInventory(Item item) {
    if (items.containsKey(item)) {
      currentWeightOfInventory -= item.getWeight();
      items.remove(item);
    } else System.out.println("You don't have that item on you.");
  }

  /**
   * Gets the inventory as a HashMap
   *
   * @return the inventory+
   */
  public HashMap<Item, Integer> getInventory() {
    return items;
  }

  /** Prints each item and its amount in the inventory. */
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
