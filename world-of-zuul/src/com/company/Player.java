package com.company;

import java.util.HashMap;
import java.util.Stack;

public class Player {

    private Inventory inventory;
    private Room location;
    private Stack<Room> previousRooms;

    public Player(Room location, int inventorySize) {
        inventory = new Inventory(inventorySize);
        this.location = location;
        previousRooms = new Stack<>();
    }

    public HashMap<Item, Integer> characterInventory() {
        return inventory.getInventory();
    }

    public Inventory getCharacterInventory() {
        return inventory;
    }

    public void printInventory() {
        inventory.printInventory();
    }

    public void pickUpItem(Item item) {
        inventory.addItemToInventory(item);
    }

    public void dropItem(Item item) {
        inventory.removeItemFromInventory(item);
    }

    public void setLocation(Room location) {
        this.location = location;
    }

    public Room getLocation() {
        return location;
    }

    public void addRoomsToPreviousRooms(Room room) {
        this.previousRooms.push(room);
    }

    public void removePreviousRooms() {
        this.previousRooms = new Stack<>();
    }

    public void goBack() {
        if (previousRooms.empty()) {
            System.out.println("No where to go back");
            return;
        }
        setLocation(previousRooms.pop());
    }
}
