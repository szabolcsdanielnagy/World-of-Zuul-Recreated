package com.company.initializers;

import com.company.Room;

import java.util.ArrayList;
import java.util.Random;

public class InitializeRooms {
  private ArrayList<Room> rooms;
  private InitializeItems initializeItems;
  private Room entrance,
      chapel,
      lowerBasement,
      graveyard,
      crypts,
      altar,
      castleEntrance,
      upperBasement,
      cave,
      hiddenRoom,
      teleportRoom,
      storageRoom;

  public InitializeRooms(InitializeItems initializeItems) {
    this.initializeItems = initializeItems;
    createRooms();
    setAllExits();
    addItemsToTheRooms();
  }

  private void createRooms() {
    rooms = new ArrayList<>();
    rooms.add(entrance = new Room("outside of the main entrance of the dungeon", "entrance"));
    rooms.add(chapel = new Room("in the chapel", "chapel"));
    rooms.add(lowerBasement = new Room("in the lower basement", "lowerBasement"));
    rooms.add(storageRoom = new Room("in the storage room", "storageRoom"));
    rooms.add(graveyard = new Room("in the graveyard", "graveyard"));
    rooms.add(crypts = new Room("in the crypts", "crypts"));
    rooms.add(altar = new Room("in the altar", "altar"));
    rooms.add(castleEntrance = new Room("at the entrance of the castle", "castleEntrance"));
    rooms.add(upperBasement = new Room("in the upper basement", "upperBasement"));
    rooms.add(cave = new Room("in the cave", "cave"));
    rooms.add(hiddenRoom = new Room("in a hidden room", "hiddenRoom"));
    teleportRoom = new Room("in a teleport room", "teleportRoom");
    teleportRoom.setTeleport(true);
  }

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
    altar.setExit("east", castleEntrance);

    upperBasement.setExit("down", chapel);
    upperBasement.setExit("east", cave);
    upperBasement.setExit("south", hiddenRoom);

    hiddenRoom.setExit("north", upperBasement);
    hiddenRoom.setExit("east", teleportRoom);

    cave.setExit("west", upperBasement);

    castleEntrance.setExit("west", altar);
  }

  private void addItemsToTheRooms() {
    hiddenRoom.addItemToRoom(initializeItems.getItemByName("Apple"));
    cave.addItemToRoom(initializeItems.getItemByName("Bread"));
    storageRoom.addItemToRoom(initializeItems.getItemByName("Map"));
    chapel.addItemToRoom(initializeItems.getItemByName("Life orb"));
  }

  public Room getARandomRoomFromInitializedRooms() {
    Random random = new Random();
    return (rooms.get(random.nextInt(rooms.size())));
  }

  public Room getRoomByName(String name) {
    for (Room room : rooms) {
      if (room.getName().equals(name)) {
        return room;
      }
    }
    return null;
  }
}
