package com.company;

public class Item {
  private final String description;
  private final String effect;
  private final String name;
  private static int generateId = 0;
  private final int weight;
  private final int itemId;

  public Item(String name, String description, int weight, String effect) {
    generateId++;
    this.name = name;
    this.itemId = generateId;
    this.description = description;
    this.weight = weight;
    this.effect = effect;
  }

  public String getDescription() {
    return this.description;
  }

  public int getId() {
    return itemId;
  }

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

  public String getFullDescriptionWithoutWeight() {
    return "ID:" + getId() + " Name: " + getName() + " Desc: " + getDescription();
  }

  public String getName() {
    return name;
  }

  public String getEffect() {
    return effect;
  }

  public int getWeight() {
    return weight;
  }
}
