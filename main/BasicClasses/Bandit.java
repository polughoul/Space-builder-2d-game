package main.BasicClasses;

import javafx.scene.canvas.GraphicsContext;

import javax.swing.*;
public class Bandit {
    private int level;
    private int health;
    private int damage;
    private int speed;
    private int x;
    private int y;

    private long lastAttackTime = 0;
    private long attackDelay = 5000;

    public Bandit(int level, int health, int damage, int x, int y, int speed) {
        this.level = level;
        this.health = health;
        this.damage = damage;
        this.speed = speed;
        this.x = x;
        this.y = y;
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(javafx.scene.paint.Color.GREEN);
        gc.fillOval(x - 10 / 2, y - 10 / 2, 10, 10);
    }

    public void move(Player player, Planet currentPlanet) {
        if (currentPlanet.getBandits().contains(this)) {
            double dx = player.getX() - this.x;
            double dy = player.getY() - this.y;
            double distance = Math.sqrt(dx * dx + dy * dy);

            double directionX = dx / distance;
            double directionY = dy / distance;

            this.x += directionX * speed;
            this.y += directionY * speed;

            if (distance <= 5) {
                long currentTime = System.currentTimeMillis();
                if (currentTime - lastAttackTime >= attackDelay) {
                    this.attack(player);
                    lastAttackTime = currentTime;
                }
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

    public void attack(Player player) {
        int newHealth = player.getHealth() - this.damage;
        player.setHealth(newHealth);
        System.out.println("bandit damaged you - " + this.damage + ", your health: " + newHealth);
        if (newHealth <= 0) {
            JOptionPane.showMessageDialog(null, "You have lost. Press the 'New Game' button to start again.");
            // here i need implement the game over logic
        }
    }

}