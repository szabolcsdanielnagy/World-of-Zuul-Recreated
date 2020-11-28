package com.company;

/**
 * This class is part of the "World of Zuul" application. "World of Zuul" is a text based adventure
 * game.
 *
 * <p>This class holds information about a command that was issued by the user. A command currently
 * consists of three strings: a command word a second word and a third word
 *
 * <p>The way this is used is: Commands are already checked for being valid command words. If the
 * user entered an invalid command (a word that is not known) then the command word is <null>.
 *
 * <p>If the command had only one word, then the second word is <null>.
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class Command {
  private final String secondWord;
  private final String thirdWord;
  private CommandWord commandWord;

  /**
   * Create a command object. First, second and third word must be supplied, but either one (or all
   * three) can be null.
   *
   * @param commandWord The first word of the command. Null if the command was not recognised.
   * @param secondWord The second word of the command.
   * @param thirdWord The third word of the command.
   */
  public Command(CommandWord commandWord, String secondWord, String thirdWord) {
    this.commandWord = commandWord;
    this.secondWord = secondWord;
    this.thirdWord = thirdWord;
  }

  /**
   * Return the command word (the first word) of this command. If the command was not understood,
   * the result is null.
   *
   * @return The command word.
   */
  public CommandWord getCommandWord() {
    if (commandWord == null) {
      return CommandWord.UNKNOWN;
    }
    return commandWord;
  }

  /** @return The second word of this command. Returns null if there was no second word. */
  public String getSecondWord() {
    return secondWord;
  }

  /** @return true if this command was not understood. */
  public boolean isUnknown() {
    return (commandWord == CommandWord.UNKNOWN);
  }

  /** @return true if the command has a second word. */
  public boolean hasSecondWord() {
    return (secondWord != null);
  }

  /** @return the third word of this command. Returns null if there was no second word. */
  public String getThirdWord() {
    return thirdWord;
  }

  /** @return true if the command has a third word. */
  public boolean hasThirdWord() {
    return (thirdWord != null);
  }
}
