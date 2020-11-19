package com.company;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
  private HashMap<Item, Integer> items;
  private final int inventorySize;
  private int currentWeightOfInventory;

  public Inventory(int inventorySize) {
    items = new HashMap<>();
    currentWeightOfInventory = 0;
    this.inventorySize = inventorySize;
  }

  public void addItemToInventory(Item item) {
    if (canPickUpItem(item)) {
      if (items.containsKey(item)) {
        items.put(item, items.get(item) + 1);
      } else {
        items.put(item, 1);
      }
      currentWeightOfInventory += item.getWeight();
    } else System.out.println("Can't carry anymore.");
  }

  public boolean canPickUpItem(Item item) {
    return inventorySize >= currentWeightOfInventory + item.getWeight();
  }

  public void removeItemFromInventory(Item item) {
    if (items.containsKey(item)) {
      currentWeightOfInventory -= item.getWeight();
      items.remove(item);
    } else System.out.println("You don't have that item on you.");
  }

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
