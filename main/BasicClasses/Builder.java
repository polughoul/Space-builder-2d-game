package main.BasicClasses;

import javafx.scene.canvas.GraphicsContext;

import java.util.HashMap;
import java.util.Map;

public class Builder {
    private int money;
    private HashMap<String, Integer> resources;
    private Map<String, Map<String, Integer>> resourcePrices;
    private Map<String, Map<String, Integer>> sellPrices;
    private int x;
    private int y;

    public Builder(HashMap<String, Integer> resources, int x, int y) {
        this.resources = resources;
        this.x = x;
        this.y = y;
        this.resourcePrices = new HashMap<>();
        this.money = 100;
        this.sellPrices = new HashMap<>();

        Map<String, Integer> woodPrice = new HashMap<>();
        woodPrice.put("coins", 20);
        woodPrice.put("Resource1", 50);
        this.resourcePrices.put("wood", woodPrice);

        Map<String, Integer> Resource1SellPrice = new HashMap<>();
        Resource1SellPrice.put("coins", 15);
        this.sellPrices.put("Resource1", Resource1SellPrice);

    }
    public void draw(GraphicsContext gc) {
        gc.setFill(javafx.scene.paint.Color.PURPLE);
        gc.fillRect(x - 10 / 2, y - 10 / 2, 10, 10);
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