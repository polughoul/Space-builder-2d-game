package main.BasicClasses;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Player {
    private int money;
    private HashMap<String, Integer> resources;
    private int x;
    private int y;
    private List<Resource> collectedResources = new ArrayList<>();

    private int health;

    public void moveUp() {
        if (y > 0) {
            y -= 1;
            System.out.println("Move up");
        }
    }

    public void moveDown() {
        if (y < 580) {
            y += 1;
            System.out.println("Move down");
        }
    }

    public void moveLeft() {
        if (x > 0) {
            x -= 1;
            System.out.println("Move left");
        }
    }

    public void moveRight() {
        if (x < 780) {
            x += 1;
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
    }

}