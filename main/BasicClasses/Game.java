package main.BasicClasses;

public class Game {
    private Player player;
    private Galaxy galaxy;

    public Game(Player player, Galaxy galaxy) {
        this.player = player;
        this.galaxy = galaxy;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Galaxy getGalaxy() {
        return galaxy;
    }

    public void setGalaxy(Galaxy galaxy) {
        this.galaxy = galaxy;
    }
}