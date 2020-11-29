package com.company;

/**
 * This class represents the items in the game. Items can have a description, an effect, a name, a
 * weight and an ID.
 *
 * @author Szabolcs D. Nagy
 * @version 29.11.2020
 */
public class Item {
  private static int generateId = 0;
  private final String description;
  private final String effect;
  private final String name;
  private final int weight;
  private final int itemId;
  private Room chargedRoom;
  private boolean isUsable;

  /**
   * Create an object of the Item class.
   *
   * @param name name of the item
   * @param description description of the item
   * @param weight weight of the item
   * @param effect effect of the item
   */
  public Item(String name, String description, int weight, String effect) {
    generateId++;
    this.isUsable = true;
    this.chargedRoom = null;
    this.name = name;
    this.itemId = generateId;
    this.description = description;
    this.weight = weight;
    this.effect = effect;
  }

  /**
   * Gets the description of an item.
   *
   * @return the description
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Gets the ID of an item.
   *
   * @return id of the item
   */
  public int getId() {
    return itemId;
  }

  /**
   * Gets the full description of an item as a String.
   *
   * @return full description of the item
   */
  public String getFullDescription() {
    return getFullDescriptionWithoutWeight() + " (Weight: " + getWeight() + ")";
  }

  /**
   * Gets the full description of an item without its weight.
   *
   * @return the full description without weight
   */
  public String getFullDescriptionWithoutWeight() {
    return "ID:" + getId() + " Name: " + getName() + " Desc: " + getDescription();
  }

  /**
   * Gets the name of an item.
   *
   * @return the name of the item
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the effect of an item.
   *
   * @return the effect of the item
   */
  public String getEffect() {
    return effect;
  }

  /**
   * Gets the weight of an item.
   *
   * @return weight of the item
   */
  public int getWeight() {
    return weight;
  }

  /**
   * Gets the charged room of the Beamer.
   *
   * @return charged room
   */
  public Room getChargedRoom() {
    return chargedRoom;
  }

  /**
   * Sets the charged room of the beamer.
   *
   * @param chargedRoom charged room of the beamer
   */
  public void setChargedRoom(Room chargedRoom) {
    this.chargedRoom = chargedRoom;
  }

  /**
   * Gets whether an item is usable or not.
   *
   * @return true if usable, false otherwise
   */
  public boolean isUsable() {
    return isUsable;
  }

  /**
   * Sets the item's usable status to the given boolean
   *
   * @param usable new value
   */
  public void setUsable(boolean usable) {
    isUsable = usable;
  }
}
