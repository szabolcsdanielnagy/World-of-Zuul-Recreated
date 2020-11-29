package com.company.initializers;

import com.company.Room;
import com.company.characters.NPC;

import java.util.*;

/**
 * This class initializes and creates the NPCs which can be found in the game. It holds an ArrayList
 * of them.
 *
 * @author Szabolcs D. Nagy
 * @version 29.11.2020
 */
public class InitializeNpcs {

  private final InitializeRooms initializeRooms;
  private final InitializeItems initializeItems;
  private ArrayList<NPC> npcs;
  private NPC dwarf, ghost, zombie;

  /**
   * Creates an object of the InitializeNpcs class.
   *
   * @param initializeRooms initialized rooms in the game
   * @param initializeItems initialized items in the game
   */
  public InitializeNpcs(InitializeRooms initializeRooms, InitializeItems initializeItems) {
    this.initializeRooms = initializeRooms;
    this.initializeItems = initializeItems;
    createNpcs();
    setUpNpcs();
  }

  /** Creates the NPCs which can be found in the game. */
  private void createNpcs() {
    npcs = new ArrayList<>();
    npcs.add(
        dwarf =
            new NPC(
                "Dwarf",
                "Hello traveller. I have been waiting here for a long time.",
                "Find the bread in the dungeon and bring it to me.",
                initializeRooms.getRoomByName("altar")));
    npcs.add(
        ghost =
            new NPC(
                "Ghost",
                "I am wandering around the dungeon.",
                "I can't help you get out.",
                initializeRooms.getRoomByName("chapel")));
    npcs.add(
        zombie =
            new NPC(
                "Zombie",
                "I will eat your brain.",
                "Run.",
                initializeRooms.getRoomByName("graveyard")));
  }

  /** Sets up the NPCs. (Characteristics of the NPCs can be changed / added here.) */
  private void setUpNpcs() {
    dwarf.pickUpItem(initializeItems.getItemByName("Wand"));
    ghost.setMoving(true);
    zombie.setMoving(true);
  }

  /**
   * Moves the NPCs in a random possible direction. Solution used to get a random value from a
   * hashmap:
   * https://stackoverflow.com/questions/12385284/how-to-select-a-random-key-from-a-hashmap-in-java/12385392
   */
  public void npcMovement() {
    for (NPC npc : npcs) {
      if (npc.isMoving()) {
        Room location = npc.getCurrentRoom();
        String possibleDirections = location.getExitString();
        HashMap<String, Room> rooms = location.getExits();
        HashMap<String, Room> possibleRooms = new HashMap<>();
        for (Map.Entry<String, Room> room : rooms.entrySet()) {
          if (possibleDirections.contains(room.getKey())
              && !(room.getValue().isTeleport())
              && !(room.getValue().isLocked())) {
            possibleRooms.put(room.getKey(), room.getValue());
          }
        }
        List<String> keysAsArray = new ArrayList<>(possibleRooms.keySet());
        Random random = new Random();
        npc.setCurrentRoom(possibleRooms.get(keysAsArray.get(random.nextInt(keysAsArray.size()))));
      }
    }
  }

  /**
   * Gets the created NPCs as an ArrayList.
   *
   * @return the created NPCs
   */
  public ArrayList<NPC> getNpcs() {
    return npcs;
  }

  /**
   * Searches for an NPC in the created items by a String.
   *
   * @param name name of the NPC
   * @return the NPC if it is found, null otherwise
   */
  public NPC getNpcByName(String name) {
    for (NPC npc : npcs) {
      if (npc.getName().equals(name)) {
        return npc;
      }
    }
    return null;
  }
}
