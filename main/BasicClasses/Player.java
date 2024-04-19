package main.BasicClasses;

import java.util.HashMap;

public class Player extends SpaceShip {
    private int money;
    private HashMap<String, Integer> resources;

    public Player(String name, int fuel, int health, int capacity, int money) {
        super(name, fuel, health, capacity);
        this.money = money;
        this.resources = new HashMap<>();
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void decreaseHealth(int damage) {
        int newHealth = this.getHealth() - damage;
        this.setHealth(Math.max(newHealth, 0));
    }

    public boolean hasEnoughResources(Resource requiredResource) {
        return resources.getOrDefault(requiredResource.getType(), 0) >= requiredResource.getCounts();
    }

    public void decreaseResource(Resource resource) {
        resources.put(resource.getType(), resources.getOrDefault(resource.getType(), 0) - resource.getCounts());
    }

    public void increaseResource(Resource resource) {
        resources.put(resource.getType(), resources.getOrDefault(resource.getType(), 0) + resource.getCounts());
    }
}