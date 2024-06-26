package main.BasicClasses;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.GUI.GameView;

import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Logger;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The Player class represents a player in the game.
 * It implements the Serializable interface and contains properties and methods specific to a player.
 */
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
    private String playerImagePath = "src/main/java/main/assets/player.png";
    private int maxHealth;
    private int health;

    private static final Logger logger = Logger.getLogger(Player.class.getName());

    public void moveUp() {
        if (y > 0) {
            y -= speed;
            imageView.setRotate(270);
        }
    }

    public void moveDown() {
        if (y < 900) {
            y += speed;
            imageView.setRotate(90);
        }
    }

    public void moveLeft() {
        if (x > 0) {
            x -= speed;
            imageView.setRotate(180);
        }
    }

    public void moveRight() {
        if (x < 1240) {
            x += speed;
            imageView.setRotate(0);
        }
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Constructs a new Player with the given parameters.
     *
     * @param money The money of the Player.
     * @param health The health of the Player.
     * @param damage The damage of the Player.
     * @param gameView The GameView object.
     */
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Collects a Resource.
     *
     * @param resource The Resource to collect.
     */
    public void collectResource(Resource resource) {
        // Add the resource to the list of collected resources
        collectedResources.add(resource);
        // Get the type and amount of the resource
        String resourceType = resource.getType();
        int resourceAmount = resource.getCounts();
        // Add the resource to the player's resources
        resources.put(resourceType, resources.getOrDefault(resourceType, 0) + resourceAmount);
        logger.info("Collected resource: " + resource.getType());
    }
    public List<Resource> getCollectedResources() {
        return collectedResources;
    }

    /**
     * Builds a Building on a Planet.
     *
     * @param building The Building to build.
     * @param planet The Planet on which to build the Building.
     */
    public void buildBuilding(Building building, Planet planet) {
        if (hasEnoughResources(building)) {
            planet.addBuilding(building);
            for (Map.Entry<String, Integer> entry : building.getCost().entrySet()) {
                resources.put(entry.getKey(), resources.get(entry.getKey()) - entry.getValue());
            }
        }
        logger.info("Building " + building.getType() + " was succesfully constructed on" + planet.getName());
    }

    /**
     * Checks if the Player has enough resources to build a Building.
     *
     * @param building The Building to check.
     * @return True if the Player has enough resources, false otherwise.
     */
    public boolean hasEnoughResources(Building building) {
        for (Map.Entry<String, Integer> entry : building.getCost().entrySet()) {
            if (!resources.containsKey(entry.getKey()) || resources.get(entry.getKey()) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Fires a Projectile.
     *
     * @param targetX The x-coordinate of the target.
     * @param targetY The y-coordinate of the target.
     */
    public void fire(double targetX, double targetY) {
        // Create a new projectile with the player's position and the target's position
        Projectile projectile = new Projectile(this.x, this.y, targetX, targetY, this);
        // Add the projectile to the game view
        gameView.addProjectile(projectile);
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

}