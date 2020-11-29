package com.company.characters;

import com.company.Room;

/**
 * This class represents the NPCs in the game.
 *
 * @author Szabolcs D. Nagy
 * @version 29.11.2020
 */
public class NPC extends Character {
  private static int generateId = 0;
  private final int npcId;
  private final String message;
  private final String interactionMessage;
  private boolean moving; // Determine whether an NPC moves or stays in one place

  /**
   * Create an object of the NPC class.
   *
   * @param name name of the NPC
   * @param message message of the NPC (shown when player enters the same room as the NPC)
   * @param interactionMessage interaction message of the NPC (shown when player interacts with the
   *     NPC)
   * @param currentRoom the default room of the NPC
   */
  public NPC(String name, String message, String interactionMessage, Room currentRoom) {
    super(999, currentRoom, name);
    generateId++;
    npcId = generateId;
    this.message = message;
    this.interactionMessage = interactionMessage;
    moving = false;
  }

  /**
   * Gets the ID of an NPC.
   *
   * @return id of the npc
   */
  public int getNpcId() {
    return npcId;
  }

  /**
   * Gets the name and the interaction message of the NPC.
   *
   * @return name: interaction message
   */
  public String getInteractionMessage() {
    return getName() + ":" + interactionMessage;
  }

  /**
   * Gets the message of the NPC.
   *
   * @return message of the npc
   */
  public String getMessage() {
    return message;
  }

  /**
   * Gets the id, name and message of the NPC.
   *
   * @return id, name and message of the NPC as a String
   */
  public String longDescriptionOfNpc() {
    return "ID:" + getNpcId() + " " + getName() + ":" + getMessage();
  }

  /**
   * Checks whether the NPC moves or stays in one place.
   *
   * @return true if it moves, false otherwise
   */
  public boolean isMoving() {
    return moving;
  }

  /**
   * Sets whether the NPC moves or stays in one place.
   *
   * @param moving statement whether it should move or not
   */
  public void setMoving(boolean moving) {
    this.moving = moving;
  }
}
