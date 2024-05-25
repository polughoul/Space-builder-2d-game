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
        this.money = 100;
        this.sellPrices = new HashMap<>();
        this.imagePath = imagePath;
        this.image = new Image("file:" + imagePath);

        Map<String, Integer> goldPrice = new HashMap<>();
        goldPrice.put("coins", 20);
        goldPrice.put("obsidian", 2);
        this.resourcePrices.put("gold", goldPrice);

        Map<String, Integer> rubyPrice = new HashMap<>();
        rubyPrice.put("coins", 15);
        rubyPrice.put("gold", 1);
        this.resourcePrices.put("ruby", rubyPrice);

        Map<String, Integer> ironPrice = new HashMap<>();
        ironPrice.put("coins", 25);
        ironPrice.put("lazurite", 2);
        this.resourcePrices.put("iron", ironPrice);

        Map<String, Integer> silverPrice = new HashMap<>();
        silverPrice.put("coins", 10);
        silverPrice.put("ruby", 3);
        this.resourcePrices.put("silver", silverPrice);

        Map<String, Integer> nephritePrice = new HashMap<>();
        nephritePrice.put("coins", 15);
        nephritePrice.put("iron", 1);
        this.resourcePrices.put("nephrite", nephritePrice);

        Map<String, Integer> lazuritePrice = new HashMap<>();
        lazuritePrice.put("coins", 20);
        lazuritePrice.put("obsidian", 3);
        this.resourcePrices.put("lazurite", lazuritePrice);

        Map<String, Integer> obsidianPrice = new HashMap<>();
        obsidianPrice.put("coins", 25);
        obsidianPrice.put("silver", 4);
        this.resourcePrices.put("obsidian", obsidianPrice);

        Map<String, Integer> goldSellPrice = new HashMap<>();
        goldSellPrice.put("coins", 15);
        this.sellPrices.put("gold", goldSellPrice);

        Map<String, Integer> rubySellPrice = new HashMap<>();
        rubySellPrice.put("coins", 5);
        this.sellPrices.put("ruby", rubySellPrice);

        Map<String, Integer> ironSellPrice = new HashMap<>();
        ironSellPrice.put("coins", 15);
        this.sellPrices.put("iron", ironSellPrice);

        Map<String, Integer> silverSellPrice = new HashMap<>();
        silverSellPrice.put("coins", 20);
        this.sellPrices.put("silver", silverSellPrice);

        Map<String, Integer> nephriteSellPrice = new HashMap<>();
        nephriteSellPrice.put("coins", 25);
        this.sellPrices.put("nephrite", nephriteSellPrice);

        Map<String, Integer> lazuriteSellPrice = new HashMap<>();
        lazuriteSellPrice.put("coins", 10);
        this.sellPrices.put("lazurite", lazuriteSellPrice);

        Map<String, Integer> obsidianSellPrice = new HashMap<>();
        obsidianSellPrice.put("coins", 20);
        this.sellPrices.put("obsidian", obsidianSellPrice);
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