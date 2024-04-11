package main.BasicClasses;

public class Game {
    private Level currentLevel;
    private SpaceShip playerShip;
    private Inventory playerInventory;

    public Game(Level currentLevel, SpaceShip playerShip, Inventory playerInventory) {
        this.currentLevel = currentLevel;
        this.playerShip = playerShip;
        this.playerInventory = playerInventory;
    }
    public void update() {
        // This is where the game state update will be done
    }

    // Getters and setters for the attributes
    public Level getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }

    public SpaceShip getPlayerShip() {
        return playerShip;
    }

    public void setPlayerShip(SpaceShip playerShip) {
        this.playerShip = playerShip;
    }

    public Inventory getPlayerInventory() {
        return playerInventory;
    }

    public void setPlayerInventory(Inventory playerInventory) {
        this.playerInventory = playerInventory;
    }
}