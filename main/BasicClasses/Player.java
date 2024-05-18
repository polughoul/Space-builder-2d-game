package main.BasicClasses;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Player {
    private int money;
    private HashMap<String, Integer> resources;
    private int damage;
    private int x;
    private int y;
    private List<Resource> collectedResources = new ArrayList<>();
    private int speed = 2;


    private int health;

    public void moveUp() {
        if (y > 0) {
            y -= speed;
            System.out.println("Move up");
        }
    }

    public void moveDown() {
        if (y < 900) {
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
        if (x < 1240) {
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

    public Player(int money, int health, int damage) {
        this.money = money;
        this.resources = new HashMap<>();
        this.health = health;
        this.damage = damage;
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

    public void attack(Bandit bandit, Planet currentPlanet) {
        int newHealth = bandit.getHealth() - this.damage;
        bandit.setHealth(newHealth);
        System.out.println("Player give damage - " + this.damage + ", bandit health: " + newHealth);
        if (newHealth <= 0) {
            currentPlanet.removeBandit(bandit);
        }
    }

    public void attackNearestBandit(Planet currentPlanet) {
        Bandit nearestBandit = null;
        double nearestDistance = Double.MAX_VALUE;

        for (Bandit bandit : currentPlanet.getBandits()) {
            double dx = this.x - bandit.getX();
            double dy = this.y - bandit.getY();
            double distance = Math.sqrt(dx * dx + dy * dy);

            if (distance < nearestDistance) {
                nearestBandit = bandit;
                nearestDistance = distance;
            }
        }

        if (nearestBandit != null && nearestDistance <= 5) {
            this.attack(nearestBandit, currentPlanet);
        }
    }
}