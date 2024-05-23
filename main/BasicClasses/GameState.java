package main.BasicClasses;

import java.io.*;
import java.util.List;

/**
 * The GameState class represents the state of the game.
 * It contains the player, space objects, and the current space object.
 */

public class GameState implements Serializable {
    private Player player;
    private List<SpaceObject> spaceObjects;
    private SpaceObject currentSpaceObject;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<SpaceObject> getSpaceObjects() {
        return spaceObjects;
    }

    public void setSpaceObjects(List<SpaceObject> spaceObjects) {
        this.spaceObjects = spaceObjects;
    }

    public SpaceObject getCurrentSpaceObject() {
        return currentSpaceObject;
    }

    public void setCurrentSpaceObject(SpaceObject currentSpaceObject) {
        this.currentSpaceObject = currentSpaceObject;
    }
}