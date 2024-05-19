package main.BasicClasses;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import main.GUI.GameView;
import javafx.scene.image.Image;

import javax.swing.*;
public class Bandit {
    private int level;
    private int health;
    private int damage;
    private int speed;
    private int x;
    private int y;
    private Image image;

    private long lastAttackTime = 0;
    private long attackDelay = 5000;

    public Bandit(int level, int health, int damage, int x, int y, int speed, String imagePath) {
        this.level = level;
        this.health = health;
        this.damage = damage;
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.image = new Image("file:" + imagePath);
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(image, x - 60 / 2, y - 60 / 2, 60, 60);
    }

    public void move(Player player, Planet currentPlanet, GameView gameView) {
        if (currentPlanet.getBandits().contains(this)) {
            double dx = player.getX() - this.x;
            double dy = player.getY() - this.y;
            double distance = Math.sqrt(dx * dx + dy * dy);

            double directionX = dx / distance;
            double directionY = dy / distance;

            this.x += directionX * speed;
            this.y += directionY * speed;

            long currentTime = System.currentTimeMillis();
            if (currentTime - lastAttackTime >= attackDelay) {
                this.attack(player, gameView);
                lastAttackTime = currentTime;
            }

            if (this.health <= 0) {
                currentPlanet.removeBandit(this);
            }
        }
    }

    // геттеры и сеттеры
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
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

}