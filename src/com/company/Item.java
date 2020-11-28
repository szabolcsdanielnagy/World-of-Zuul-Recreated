package com.company;

/**
 * This class is responsible for the items in the game. They can have descriptions, names, effects,
 * weight and a separate id. *
 *
 * <p>This class is part of the "World of Zuul" text based adventure game.
 *
 * @author Szabolcs D. Nagy
 * @version 21.10.2020
 */
public class Item {
  private static int generateId = 0;
  private final String description;
  private final String effect;
  private final String name;
  private final int weight;
  private final int itemId;

  /**
   * The constructor of the class.
   *
   * @param name - name of the item
   * @param description - description of the item
   * @param weight - weight of the item
   * @param effect - effect of the item
   */
  public Item(String name, String description, int weight, String effect) {
    generateId++;
    this.name = name;
    this.itemId = generateId;
    this.description = description;
    this.weight = weight;
    this.effect = effect;
  }

  /**
   * Method to get the description of an item.
   *
   * @return the description of the item
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Method to get the id of an item.
   *
   * @return the id of the item
   */
  public int getId() {
    return itemId;
  }

  /**
   * Method to get the full description of an item.
   *
   * @return id, name, description, weight
   */
  public String getFullDescription() {
    return "ID:"
        + getId()
        + " Name: "
        + getName()
        + " Desc: "
        + getDescription()
        + " (Weight: "
        + getWeight()
        + ")";
  }

  /**
   * Method to get the full description of an item excluding the weight
   *
   * @return id, name, description
   */
  public String getFullDescriptionWithoutWeight() {
    return "ID:" + getId() + " Name: " + getName() + " Desc: " + getDescription();
  }

  /**
   * Method to get the name of an item
   *
   * @return name of the item
   */
  public String getName() {
    return name;
  }

  /**
   * Method to get the effect of an item.
   *
   * @return effect of the item
   */
  public String getEffect() {
    return effect;
  }

  /**
   * Method to get the weight of an item.
   *
   * @return weight of the item
   */
  public int getWeight() {
    return weight;
  }
}
