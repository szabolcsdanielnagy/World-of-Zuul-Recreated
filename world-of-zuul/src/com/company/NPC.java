package com.company;

import java.util.HashMap;

public class NPC {
  private final String name;
  private Room location;
  private Inventory inventory;
  private String message;
  private String interactionMessage;
  private static int generateId = 0;
  private final int npcId;

  public NPC(String name, String message, String interactionMessage, Room location) {
    generateId++;
    npcId = generateId;
    this.location = location;
    this.message = message;
    this.interactionMessage = interactionMessage;
    this.name = name;
    this.inventory = new Inventory(999);
  }

  public Room getLocation() {
    return location;
  }

  public void setLocation(Room location) {
    this.location = location;
  }

  public int getNpcId() {
    return npcId;
  }

  public String getInteractionMessage() {
    return getName() + ":" + interactionMessage;
  }

  public String getMessage() {
    return message;
  }

  public void setInteractionMessage(String interactionMessage) {
    this.interactionMessage = interactionMessage;
  }

  public void addItemToInventory(Item name) {
    inventory.addItemToInventory(name);
  }

  public void removeItemFromInventory(Item name) {
    inventory.removeItemFromInventory(name);
  }

  public String getName() {
    return name;
  }

  public String longDescriptionOfNpc() {
    return "ID:" + getNpcId() + " " + getName() + ":" + getMessage();
  }

  public HashMap<Item, Integer> getInventory() {
    return inventory.getInventory();
  }
}
