package main.BasicClasses;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.GUI.GameView;
import javafx.scene.image.Image;

public class Bandit {
    private int level;
    private int health;
    private int damage;
    private int speed;
    private int x;
    private int y;
    private Image[] images;
    private int currentImageIndex = 0;

    private int maxHealth;


    private long lastAttackTime = 0;
    private long attackDelay = 1000;

    public Bandit(int level, int health, int damage, int x, int y, int speed, String[] imagePaths) {
        this.level = level;
        this.health = health;
        this.damage = damage;
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.maxHealth = health;
        this.images = new Image[imagePaths.length];
        for (int i = 0; i < imagePaths.length; i++) {
            this.images[i] = new Image("file:" + imagePaths[i]);
        }
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(images[currentImageIndex], x - 60 / 2, y - 60 / 2, 60, 60);
        gc.setFill(Color.RED);
        gc.fillRect(x - 30, y - 40, 60, 5);
        gc.setFill(Color.GREEN);
        gc.fillRect(x - 30, y - 40, 60 * ((double) health / maxHealth), 5);
    }

    public void move(Player player, Planet currentPlanet, GameView gameView) {
        if (currentPlanet.getBandits().contains(this)) {
            long currentTime = System.currentTimeMillis();
            if (currentPlanet.getBuildings().size() > 0 && currentTime - lastAttackTime >= attackDelay) {
                Building targetBuilding = currentPlanet.getBuildings().get(0);
                this.attack(targetBuilding, gameView);
                if (targetBuilding.getHealth() <= 0) {
                    currentPlanet.removeBuilding(targetBuilding);
                    currentPlanet.addAvailableBuilding(targetBuilding);
                }
                lastAttackTime = currentTime;
            } else {
                double dx = player.getX() - this.x;
                double dy = player.getY() - this.y;
                double distance = Math.sqrt(dx * dx + dy * dy);

                double directionX = dx / distance;
                double directionY = dy / distance;

                this.x += directionX * speed;
                this.y += directionY * speed;

                if (currentTime - lastAttackTime >= attackDelay) {
                    this.attack(player, gameView);
                    lastAttackTime = currentTime;
                }
            }

            if (this.health <= 0) {
                currentImageIndex = images.length - 1;
                currentPlanet.removeBandit(this);
            }
        }
    }
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
        if (this.health <= 0) {
            currentImageIndex = images.length - 1;
        } else if (currentImageIndex < images.length - 1) {
            currentImageIndex++;
        }
    }

    public int getLevel() {
        return level;
    }

    public int getDamage() {
        return damage;
    }

    public int getSpeed() {
        return speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // В классе Bandit
    public void attack(Player player, GameView gameView) {
        Projectile projectile = new Projectile(this.x, this.y, player.getX(), player.getY(), this);
        gameView.addProjectile(projectile);
    }

    public void attack(Building building, GameView gameView) {
        Projectile projectile = new Projectile(this.x, this.y, building.getX(), building.getY(), this);
        gameView.addProjectile(projectile);
    }

}