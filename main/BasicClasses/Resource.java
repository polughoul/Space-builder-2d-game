package main.BasicClasses;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.Serializable;

/**
 * The Resource class represents a resource in the game.
 * It implements the Serializable interface and contains properties and methods specific to a resource.
 */

public class Resource implements Serializable {
    private String type;
    private int counts;
    private int x;
    private int y;
    private transient Image image;
    private String imagePath;

    /**
     * Checks if the Player is on the Resource.
     *
     * @param playerX The x-coordinate of the Player.
     * @param playerY The y-coordinate of the Player.
     * @return True if the Player is on the Resource, false otherwise.
     */

    public boolean isPlayerOnResource(int playerX, int playerY) {
        int dx = this.x - playerX;
        int dy = this.y - playerY;
        int distance = (int) Math.sqrt(dx * dx + dy * dy);
        return distance <= 10 / 2;
    }

    /**
     * Constructs a new Resource with the given parameters.
     *
     * @param type The type of the Resource.
     * @param counts The counts of the Resource.
     * @param x The x-coordinate of the Resource.
     * @param y The y-coordinate of the Resource.
     * @param imagePath The path to the image representing the Resource.
     */

    public Resource(String type, int counts, int x, int y, String imagePath){
        this.type = type;
        this.counts = counts;
        this.x = x;
        this.y = y;
        this.imagePath = imagePath;
        this.image = new Image("file:" + imagePath);
    }
    public void draw(GraphicsContext gc) {
        if (image == null) {
            image = new Image("file:" + imagePath);
        }
        gc.drawImage(image, x - 40 / 2, y - 40 / 2, 40, 40);
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

}
