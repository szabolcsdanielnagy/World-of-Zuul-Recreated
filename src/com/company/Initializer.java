package com.company;

import java.util.*;

/**
 * This class creates and initializes all the rooms, players, NPCs and items. It contains a method
 * to set an end-game scenario for the game, and a method to control the movement of all the NPCs in
 * the game.
 *
 * <p>This class is part of the "World of Zuul" text based adventure game.
 *
 * @author Szabolcs D. Nagy
 * @version 21.20.2020
 */
public class Initializer {

  private ArrayList<NPC> npcs;
  private ArrayList<Room> rooms;
  private ArrayList<Item> items;
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
  private Player player;
  private NPC dwarf, ghost, zombie;
  private Item bread, wand, map, lifeOrb, apple;
  private Random random;

  /** Constructor for the class, creates the rooms, players, items and initializes them. */
  public Initializer() {
    createRooms();
    createPlayers();
    createNPCs();
    createItems();
    setAllExits();
    initializeNpcs();
    initializeItems();
  }

  /** Method that creates the rooms. */
  public void createRooms() {
    rooms = new ArrayList<>();
    rooms.add(entrance = new Room("outside of the main entrance of the dungeon"));
    rooms.add(chapel = new Room("in the chapel"));
    rooms.add(lowerBasement = new Room("in the lower basement"));
    rooms.add(storageRoom = new Room("in the storage room"));
    rooms.add(graveyard = new Room("in the graveyard"));
    rooms.add(crypts = new Room("in the crypts"));
    rooms.add(altar = new Room("in the altar"));
    rooms.add(castleEntrance = new Room("at the entrance of the castle"));
    rooms.add(upperBasement = new Room("in the upper basement"));
    rooms.add(cave = new Room("in the cave"));
    rooms.add(hiddenRoom = new Room("in a hidden room"));
    teleportRoom = new Room("in a teleport room");
    teleportRoom.setTeleport(true);
  }

  /** Method that creates the players. */
  public void createPlayers() {
    player = new Player(entrance, 1,"Szabolcs");
  }

  /** Method that creates the NPCs. */
  public void createNPCs() {
    npcs = new ArrayList<>();
    npcs.add(
        dwarf =
            new NPC(
                "Dwarf",
                "Hello traveller. I have been waiting here for a long time.",
                "Bring me the bread from the cave.",
                altar));
    npcs.add(
        ghost =
            new NPC(
                "Ghost",
                "I am wandering around the dungeon.",
                "I can't help you get out.",
                chapel));
    npcs.add(zombie = new NPC("Zombie", "I will eat your brain.", "Run.", graveyard));
  }

  /** Method that creates the items. */
  public void createItems() {
    items = new ArrayList<>();
    items.add(
        bread =
            new Item(
                "Bread", "Go and talk to the dwarf he might like it.", 1, "You ate the bread."));
    items.add(
        wand =
            new Item(
                "Wand",
                "This will help you get out of the dungeon. ",
                1,
                "The magical wand teleported you out of the dungeon. You find yourself in a forest, you are finally free . . . "));
    items.add(map = new Item("Map", "The map of the dungeon. Let's get out of here!", 1, getMap()));
    items.add(
        lifeOrb = new Item("Life orb", "This might save you in the future!", 999, "Extra health."));
    items.add(
        apple =
            new Item(
                "Apple",
                "Do not try to eat it, it is cursed!",
                1,
                "You ate the apple.")); // 50 % chance to die if you eat it
  }

  /** Method that sets all the exits in the rooms. */
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

  /** Method that initializes the NPCs that were created. */
  public void initializeNpcs() {
    dwarf.pickUpItem(wand);
    ghost.setMoving(true);
  }

  /**
   * * Method that returns the map of the game. (The game contains a "Map" item, which upon use
   * prints * this string.)
   *
   * @return the map
   */
  public String getMap() {
    String map = "";
    map +=
        ":::'######:'######:'######:'######:'######:'######:'######:'######:::"
            + "\n"
            + "::: ##::GRAVE:::##: ##::::::::::##  ##:::ALTAR::##: ##::CASTLE::##:::"
            + "\n"
            + "::: ##:::YARD:::##: ##::CRYPTS::##: ##::::::::::##: ##::::::::::##:::"
            + "\n"
            + "::: ######:'######: ######:'######: ######:'######: ######:'######:::"
            + "\n"
            + ":::......::......::......::......::......::......::......::......::::"
            + "\n"
            + ":::'######:'######:'######:'######:'######:'######:'######:'######:::"
            + "\n"
            + "::: ##::LOWER:: ##: ##::::::::::##: ##:UPPER::::##: ##::::::::::##:::"
            + "\n"
            + "::: ##:BASEMENT:##: ##::CHAPEL::##: ##:basement:##: ##:::CAVE:::##:::"
            + "\n"
            + "::: ######:'######: ######:'######: ######:'######: ######:'######:::"
            + "\n"
            + ":::......::......::......::......::......::......::......::......::::"
            + "\n"
            + ":::'######:'######:'######:'######:'######:'######:'######:'######:::"
            + "\n"
            + "::: ##::STORAGE:##: ##::::::::::##: ##::HIDDEN::##: ##:TELEPORT:##:::"
            + "\n"
            + "::: ##:::ROOM:::##: ##:ENTRANCE:##: ##:::ROOM:::##: ##:::ROOM:::##:::"
            + "\n"
            + "::: ######:'######: ######:'######: ######:'######: ######:'######:::";
    return map;
  }

  /** Method that initializes the items. */
  public void initializeItems() {
    hiddenRoom.addItemToRoom(apple);
    cave.addItemToRoom(bread);
    storageRoom.addItemToRoom(map);
    chapel.addItemToRoom(lifeOrb);
  }

  /**
   * Method that returns the items created in the game
   *
   * @return the items in the game
   */
  public ArrayList<Item> getInitializedItems() {
    return items;
  }

  /**
   * Method that returns the initialized player.
   *
   * @return the player
   */
  public Player getInitializedPlayer() {
    return player;
  }

  /**
   * This method is used to get the initialized NPCs.
   *
   * @return the initialized NPCs
   */
  public ArrayList<NPC> getInitializedNpcs() {
    return npcs;
  }
  /**
   * This method is used to get a random Room object from the created rooms.
   *
   * @return the random Room
   */
  public Room getARandomRoomFromInitializedRooms() {
    random = new Random();
    return (rooms.get(random.nextInt(rooms.size())));
  }

  /**
   * This method is responsible for removing an item from an NPC, if the player gives an item to
   * them that they need. They give something in exchange.
   */
  public void trade() {
    if (dwarf.getInventoryAsHashMap().containsKey(bread)) {
      player.getInventory().addItemToInventory(wand);
      dwarf.dropItem(wand);
    }
  }

  /** This method moves the NPCs from their current room to a neighbour room */
  public void npcMovement() {
    for (NPC npc : npcs) {
      if (npc.isMoving()) {
        Room location = npc.getCurrentRoom();
        String possibleDirections = location.getExitString();
        HashMap<String, Room> rooms = location.getExits();
        HashMap<String, Room> possibleRooms = new HashMap<>();
        for (Map.Entry<String, Room> room : rooms.entrySet()) {
          if (possibleDirections.contains(room.getKey()) && !(room.getValue().isTeleport())) {
            possibleRooms.put(room.getKey(), room.getValue());
          }
        }
        /**
         * Solution used to get a random value from a hashmap:
         * https://stackoverflow.com/questions/12385284/how-to-select-a-random-key-from-a-hashmap-in-java/12385392
         */
        List<String> keysAsArray = new ArrayList<>(possibleRooms.keySet());
        random = new Random();
        npc.setCurrentRoom(possibleRooms.get(keysAsArray.get(random.nextInt(keysAsArray.size()))));
      }
    }
  }
}
