package com.company;

import java.util.ArrayList;
import java.util.Random;

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
  private NPC dwarf;
  private Item bread, wand, map, lifeOrb, apple;

  public Initializer() {
    createRooms();
    createPlayers();
    createNPCs();
    createItems();
    setAllExits();
    initializeNpc();
    initializeItems();
  }

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
    teleportRoom = new Room("in a magical room");
    teleportRoom.setTeleport(true);
  }

  public void createPlayers() {
    player = new Player(entrance, 2);
  }

  public void createNPCs() {
    npcs = new ArrayList<>();
    npcs.add(
        dwarf =
            new NPC(
                "Dwarf",
                "Hello traveller. I have been waiting here for a long time.",
                "Bring me the bread.",
                altar));
  }

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
    items.add(map = new Item("Map", "The map of the dungeon. Let's get out of here!", 1, setMap()));
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

  public void initializeNpc() {
    dwarf.addItemToInventory(wand);
  }

  public String setMap() {
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

  public void initializeItems() {
    hiddenRoom.addItemToRoom(apple);
    cave.addItemToRoom(bread);
    storageRoom.addItemToRoom(map);
    chapel.addItemToRoom(lifeOrb);
  }

  public Player getInitializedPlayer() {
    return player;
  }

  public ArrayList<NPC> getInitializedNpcs() {
    return npcs;
  }

  public void refreshInteractionMessages() {
    if (player.characterInventory().containsKey((bread))) {
      dwarf.setInteractionMessage("Give me the bread. (Use the 'give' command)");
    }
  }

  public Room randomRoom() {
    Random rnd = new Random();
    return (rooms.get(rnd.nextInt(rooms.size())));
  }

  public void trade() {
    if (dwarf.getInventory().containsKey(bread)) {
      player.getCharacterInventory().addItemToInventory(wand);
    }
  }
}
