package com.company.initializers;

import com.company.Room;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class initializes and creates the rooms which can be found in the game. It holds an
 * ArrayList of them.
 *
 * @author Szabolcs D. Nagy
 * @version 29.11.2020
 */
public class InitializeRooms {
  private final InitializeItems initializeItems;
  private ArrayList<Room> rooms;
  private Room entrance,
      chapel,
      lowerBasement,
      graveyard,
      crypts,
      altar,
      hole, // trapdoor
      upperBasement,
      cave,
      hiddenRoom,
      teleportRoom,
      storageRoom;

  /**
   * Creates an object of the InitializeRooms class.
   *
   * @param initializeItems initialized items in the game
   */
  public InitializeRooms(InitializeItems initializeItems) {
    this.initializeItems = initializeItems;
    createRooms();
    setAllExits();
    addItemsToTheRooms();
  }

  /** Creates the rooms in the game. */
  private void createRooms() {
    rooms = new ArrayList<>();
    rooms.add(entrance = new Room("outside of the main entrance of the dungeon", "entrance"));
    rooms.add(chapel = new Room("in the chapel", "chapel"));
    rooms.add(lowerBasement = new Room("in the lower basement", "lowerBasement"));
    rooms.add(storageRoom = new Room("in the storage room", "storageRoom"));
    rooms.add(graveyard = new Room("in the graveyard", "graveyard"));
    rooms.add(crypts = new Room("in the crypts", "crypts"));
    rooms.add(altar = new Room("in the altar", "altar"));
    rooms.add(hole = new Room("in a hole. You are trapped. You can't get out.", "hole"));
    rooms.add(upperBasement = new Room("in the upper basement", "upperBasement"));
    rooms.add(cave = new Room("in the cave", "cave"));
    rooms.add(hiddenRoom = new Room("in a hidden room", "hiddenRoom"));
    teleportRoom = new Room("in a teleport room", "teleportRoom");
    teleportRoom.setTeleport(true);
    hole.setLocked(true);
  }

  /** Sets the exits for the rooms. */
  private void setAllExits() {
    entrance.setExit("north", chapel);

    chapel.setExit("south", entrance);
    chapel.setExit("down", lowerBasement);
    chapel.setExit("up", upperBasement);

    lowerBasement.setExit("up", chapel);
    lowerBasement.setExit("south", storageRoom);
    lowerBasement.setExit("north", graveyard);

    storageRoom.setExit("north", lowerBasement);

    graveyard.setExit("south", lowerBasement);
    graveyard.setExit("east", crypts);

    crypts.setExit("west", graveyard);
    crypts.setExit("east", altar);

    altar.setExit("west", crypts);
    altar.setExit("east", hole);

    upperBasement.setExit("down", chapel);
    upperBasement.setExit("east", cave);
    upperBasement.setExit("south", hiddenRoom);

    hiddenRoom.setExit("north", upperBasement);
    hiddenRoom.setExit("east", teleportRoom);

    cave.setExit("west", upperBasement);

    hole.setExit("west", altar);
  }

  /** Adds the items to the rooms. */
  private void addItemsToTheRooms() {
    hiddenRoom.addItemToRoom(initializeItems.getItemByName("Thor's hammer"));
    cave.addItemToRoom(initializeItems.getItemByName("Bread"));
    storageRoom.addItemToRoom(initializeItems.getItemByName("Map"));
    chapel.addItemToRoom(initializeItems.getItemByName("Key"));
  }

  /**
   * Gets a random room from the created rooms.
   *
   * @return a random Room object
   */
  public Room getARandomRoomFromInitializedRooms() {
    Random random = new Random();
    return (rooms.get(random.nextInt(rooms.size())));
  }

  /**
   * Searches for a room in the created rooms by a String.
   *
   * @param name name of the room
   * @return the room if it is found, null otherwise
   */
  public Room getRoomByName(String name) {
    for (Room room : rooms) {
      if (room.getName().equals(name)) {
        return room;
      }
    }
    return null;
  }
}
