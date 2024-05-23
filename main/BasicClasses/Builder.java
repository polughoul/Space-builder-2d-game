package main.BasicClasses;

import javafx.scene.canvas.GraphicsContext;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.Image;

/**
 * The Builder class represents a builder in the game.
 * It implements the Serializable interface and contains properties and methods specific to a builder.
 */

public class Builder implements Serializable {
    private int money;
    private HashMap<String, Integer> resources;
    private Map<String, Map<String, Integer>> resourcePrices;
    private Map<String, Map<String, Integer>> sellPrices;
    private int x;
    private int y;
    private String imagePath;
    private transient Image image;

    /**
     * Constructs a new Builder with the given parameters.
     *
     * @param resources The resources of the Builder.
     * @param x The x-coordinate of the Builder.
     * @param y The y-coordinate of the Builder.
     * @param imagePath The path to the image representing the Builder.
     */

    public Builder(HashMap<String, Integer> resources, int x, int y, String imagePath) {
        this.resources = resources;
        this.x = x;
        this.y = y;
        this.resourcePrices = new HashMap<>();
        this.money = 15;
        this.sellPrices = new HashMap<>();
        this.imagePath = imagePath;
        this.image = new Image("file:" + imagePath);

        Map<String, Integer> woodPrice = new HashMap<>();
        woodPrice.put("coins", 20);
        woodPrice.put("Resource1", 50);
        this.resourcePrices.put("wood", woodPrice);

        Map<String, Integer> Resource1Price = new HashMap<>();
        Resource1Price.put("coins", 20);
        Resource1Price.put("wood", 50);
        this.resourcePrices.put("Resource1", Resource1Price);

        Map<String, Integer> Resource1SellPrice = new HashMap<>();
        Resource1SellPrice.put("coins", 15);
        this.sellPrices.put("Resource1", Resource1SellPrice);

    }

    /**
     * Draws the Builder on the game canvas.
     *
     * @param gc The GraphicsContext object used for drawing.
     */
    public void draw(GraphicsContext gc) {
        if (image == null) {
            image = new Image("file:" + imagePath);
        }
        gc.drawImage(image, x - 60 / 2, y - 60 / 2, 60, 60);
    }

    /**
     * Checks if the Player is on the Builder.
     *
     * @param playerX The x-coordinate of the Player.
     * @param playerY The y-coordinate of the Player.
     * @return True if the Player is on the Builder, false otherwise.
     */
    public boolean isPlayerOnBuilder(int playerX, int playerY) {
        double distance = Math.sqrt(Math.pow(playerX - this.x, 2) + Math.pow(playerY - this.y, 2));
        return distance <= 5;
    }

    public HashMap<String, Integer> getResources() {
        return resources;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setResources(HashMap<String, Integer> resources) {
        this.resources = resources;
    }

    /**
     * Returns the resource prices of the Builder.
     *
     * @param resource The resource.
     * @return The resource prices of the Builder.
     */
    public Map<String, Integer> getResourcePrice(String resource) {
        return resourcePrices.get(resource);
    }

    /**
     * Returns the sell prices of the Builder.
     *
     * @param resource The resource.
     * @return The sell prices of the Builder.
     */
    public Map<String, Integer> getSellPrice(String resource) {
        return sellPrices.get(resource);
    }

}