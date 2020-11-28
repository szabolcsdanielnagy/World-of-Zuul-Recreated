package com.company;

/**
 * Representations for all the valid command words for the game along with a string in a particular
 * language.
 *
 * @author Michael Kölling and David J. Barnes (Modified by: Szabolcs D. Nagy)
 * @version 21.20.2020
 */
public enum CommandWord {
  // A value for each command word along with its
  // corresponding user interface string.
  GO("go"),
  QUIT("quit"),
  HELP("help"),
  PICKUP("pickup"),
  INVENTORY("inventory"),
  USE("use"),
  BACK("back"),
  INTERACT("interact"),
  GIVE("give"),
  LOOK("look"),
  DROP("drop"),
  UNKNOWN("?");

  private String commandString;

  /**
   * Initialise with the corresponding command string.
   *
   * @param commandString The command string.
   */
  CommandWord(String commandString) {
    this.commandString = commandString;
  }

  /** @return The command word as a string. */
  public String toString() {
    return commandString;
  }
}
