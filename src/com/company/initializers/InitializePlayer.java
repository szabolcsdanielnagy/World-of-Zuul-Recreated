package com.company.initializers;

import com.company.characters.Player;

public class InitializePlayer {
  private Player player;
  private InitializeRooms initializeRooms;

  public InitializePlayer(InitializeRooms initializeRooms) {
    this.initializeRooms = initializeRooms;
    createPlayer();
  }

  private void createPlayer() {
    player = new Player(initializeRooms.getRoomByName("entrance"), 1, "Player");
  }

  public Player getPlayer() {
    return player;
  }
}
