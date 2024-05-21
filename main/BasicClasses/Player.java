package main.BasicClasses;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.GUI.GameView;

import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player implements Serializable {
    private int money;
    private HashMap<String, Integer> resources;
    private int damage;
    private int x;
    private int y;
    private List<Resource> collectedResources = new ArrayList<>();
    private int speed = 2;
    private transient GameView gameView;
    private transient ImageView imageView;
    private String playerImagePath = "main/assets/player.png";
    private int maxHealth;
    private int health;



    public void moveUp() {
        if (y > 0) {
            y -= speed;
            imageView.setRotate(270);
            System.out.println("Move up");
        }
    }

    public void moveDown() {
        if (y < 900) {
            y += speed;
            imageView.setRotate(90);
            System.out.println("Move down");
        }
    }

    public void moveLeft() {
        if (x > 0) {
            x -= speed;
            imageView.setRotate(180);
            System.out.println("Move left");
        }
    }

    public void moveRight() {
        if (x < 1240) {
            x += speed;
            imageView.setRotate(0);
            System.out.println("Move right");
        }
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Player(int money, int health, int damage, GameView gameView) {
        this.money = money;
        this.resources = new HashMap<>();
        this.health = health;
        this.damage = damage;
        this.gameView = gameView;
        this.maxHealth = health;

        imageView = new ImageView();
        imageView.setFitWidth(80);
        imageView.setFitHeight(80);
    }

    public void draw(GraphicsContext gc) {
        Image image = new Image("file:" + playerImagePath);
        imageView.setImage(image);
        gc.drawImage(imageView.getImage(), x - imageView.getFitWidth() / 2, y - imageView.getFitHeight()/2, imageView.getFitWidth(), imageView.getFitHeight());
        gc.setFill(Color.GREEN);
        gc.fillRect(x - 30, y - 40, 60 * ((double) health / maxHealth), 5);
        gc.setFill(Color.RED);
        gc.fillRect(x - 30 + 60 * ((double) health / maxHealth), y - 40, 60 * (1 - (double) health / maxHealth), 5);
    }

    public int getDamage() {
        return damage;
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

    public void fire(double targetX, double targetY) {
        Projectile projectile = new Projectile(this.x, this.y, targetX, targetY, this);
        gameView.addProjectile(projectile);
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
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

        if (nearestBandit != null && nearestDistance <= 20) {
            this.fire(nearestBandit.getX(), nearestBandit.getY());
        }
    }
}