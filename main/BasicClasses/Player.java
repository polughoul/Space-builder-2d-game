package main.BasicClasses;


import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Player {
    private int money;
    private HashMap<String, Integer> resources;
    private int x;
    private int y;
    private List<Resource> collectedResources = new ArrayList<>();
    private int speed = 5;


    private int health;

    public void moveUp() {
        if (y > 0) {
            y -= speed;
            System.out.println("Move up");
        }
    }

    public void moveDown() {
        if (y < 860) {
            y += speed;
            System.out.println("Move down");
        }
    }

    public void moveLeft() {
        if (x > 0) {
            x -= speed;
            System.out.println("Move left");
        }
    }

    public void moveRight() {
        if (x < 1230) {
            x += speed;
            System.out.println("Move right");
        }
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Player(int money, int health) {
        this.money = money;
        this.resources = new HashMap<>();
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public HashMap<String, Integer> getResources() {
        return resources;
    }

    public void setResources(HashMap<String, Integer> resources) {
        this.resources = resources;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void collectResource(Resource resource) {
        collectedResources.add(resource);
        String resourceType = resource.getType();
        int resourceAmount = resource.getCounts();
        resources.put(resourceType, resources.getOrDefault(resourceType, 0) + resourceAmount);
        System.out.println("Collected resource: " + resource.getType());
    }
    public List<Resource> getCollectedResources() {
        return collectedResources;
    }

    public void buildBuilding(Building building, Planet planet) {
        if (hasEnoughResources(building)) {
            planet.addBuilding(building);
            for (Map.Entry<String, Integer> entry : building.getCost().entrySet()) {
                resources.put(entry.getKey(), resources.get(entry.getKey()) - entry.getValue());
            }
        }
    }

    public boolean hasEnoughResources(Building building) {
        for (Map.Entry<String, Integer> entry : building.getCost().entrySet()) {
            if (!resources.containsKey(entry.getKey()) || resources.get(entry.getKey()) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }
}