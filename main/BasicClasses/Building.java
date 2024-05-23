package main.BasicClasses;

import java.io.Serializable;
import java.util.Map;
import javafx.scene.image.Image;

/**
 * The Building class represents a building in the game.
 * It implements the Serializable interface and contains properties and methods specific to a building.
 */

public class Building implements Serializable {
    private String type;
    private Map<String, Integer> cost;
    private int health;
    private int maxHealth;
    int x;
    int y;
    private String imagePath;

    /**
     * Constructs a new Building with the given parameters.
     *
     * @param type The type of the Building.
     * @param cost The cost of the Building.
     * @param imagePath The path to the image representing the Building.
     * @param health The health of the Building.
     */


    public Building(String type, Map<String, Integer> cost,  String imagePath,  int health ) {
        this.type = type;
        this.cost = cost;
        this.imagePath = imagePath;
        this.health = health;
        this.maxHealth = health;

    }
    public int getMaxHealth() {
        return maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Image getImage() {
        return new Image("file:" + imagePath);
    }

    public Map<String, Integer> getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return type + " (cost: " + cost + ")";
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


}