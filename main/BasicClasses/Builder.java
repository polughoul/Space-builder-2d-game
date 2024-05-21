package main.BasicClasses;

import javafx.scene.canvas.GraphicsContext;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.Image;

public class Builder implements Serializable {
    private int money;
    private HashMap<String, Integer> resources;
    private Map<String, Map<String, Integer>> resourcePrices;
    private Map<String, Map<String, Integer>> sellPrices;
    private int x;
    private int y;
    private String imagePath;
    private transient Image image;

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
    public void draw(GraphicsContext gc) {
        if (image == null) {
            image = new Image("file:" + imagePath);
        }
        gc.drawImage(image, x - 60 / 2, y - 60 / 2, 60, 60);
    }

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

    public Map<String, Integer> getResourcePrice(String resource) {
        return resourcePrices.get(resource);
    }
    public Map<String, Integer> getSellPrice(String resource) {
        return sellPrices.get(resource);
    }

}