package main.BasicClasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.image.Image;

/**
 * The SpaceObject class represents a space object in the game(planet, asteroid).
 * It implements the Serializable interface and contains properties and methods specific to a space object.
 */

public abstract class SpaceObject implements Serializable {
    private List<Resource> resources;
    private String name;
    private int size;
    private int x;
    private int y;

    /**
     * Constructs a new SpaceObject with the given parameters.
     *
     * @param name The name of the SpaceObject.
     * @param size The size of the SpaceObject.
     * @param x The x-coordinate of the SpaceObject.
     * @param y The y-coordinate of the SpaceObject.
     */
    public SpaceObject(String name, int size, int x, int y){
        this.name = name;
        this.size = size;
        this.x = x;
        this.y = y;
        this.resources = new ArrayList<>();
    }

    public void removeResource(Resource resource) {
    }

    public List<Resource> getResources() {
        return resources;
    }

    public int getSize() {
        return size;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public abstract Image getImage();

    /**
     * Checks if the Player is on the SpaceObject.
     *
     * @param playerX The x-coordinate of the Player.
     * @param playerY The y-coordinate of the Player.
     * @return True if the Player is on the SpaceObject, false otherwise.
     */
    public boolean isPlayerOnObject(int playerX, int playerY) {
        int dx = this.x - playerX;
        int dy = this.y - playerY;
        int distance = (int) Math.sqrt(dx * dx + dy * dy);
        return distance <= this.size / 2;
    }

}