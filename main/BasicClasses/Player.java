package main.BasicClasses;

import java.util.HashMap;

public class Player {
    private int money;
    private HashMap<String, Integer> resources;

    private int health;

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

    public void decreaseHealth(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    public void increaseResource(String resourceType, int count) {
        resources.put(resourceType, resources.getOrDefault(resourceType, 0) + count);
    }

    public void collectResource(Resource resource) {
        String type = resource.getType();
        int count = resource.getCounts();
        resources.put(type, resources.getOrDefault(type, 0) + count);
        resource.setCounts(0);
        System.out.println("Collected " + count + " " + type); // Выводим сообщение о сборе ресурса
    }
}