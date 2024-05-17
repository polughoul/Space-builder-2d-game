package main.BasicClasses;

import javafx.scene.canvas.GraphicsContext;

import javax.swing.*;
import java.awt.*;

public class Bandit {
    private int level;
    private int health;
    private int damage;
    private int speed;
    private int x;
    private int y;

    private double angle = 0; // угол в радианах
    private int radius = 100; // радиус движения
    private int centerX; // центр круга по X
    private int centerY; // центр круга по Y

    private long lastAttackTime = 0; // время последней атаки
    private long attackDelay = 5000;

    public Bandit(int level, int health, int damage, int x, int y, int speed) {
        this.level = level;
        this.health = health;
        this.damage = damage;
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.centerX = x;
        this.centerY = y;
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

            // Нормализация вектора направления
            double directionX = dx / distance;
            double directionY = dy / distance;

            // Движение бандита в сторону игрока
            this.x += directionX * speed;
            this.y += directionY * speed;

            if (distance <= 5) { // 5 - это расстояние, на котором бандит атакует игрока
                long currentTime = System.currentTimeMillis();
                if (currentTime - lastAttackTime >= attackDelay) {
                    this.attack(player);
                    lastAttackTime = currentTime;
                }
            }


            if (this.health <= 0) {
                currentPlanet.removeBandit(this);
            }

        } else {
            // Движение по кругу, если игрок не на планете
            angle += 0.01; // скорость вращения
            x = centerX + (int) (Math.cos(angle) * radius);
            y = centerY + (int) (Math.sin(angle) * radius);
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
        System.out.println("Вам нанесен урон - " + this.damage + ", ваше здоровье: " + newHealth);
        if (newHealth <= 0) {
            // Если здоровье игрока достигло 0 или меньше, показать сообщение о проигрыше
            JOptionPane.showMessageDialog(null, "You have lost. Press the 'New Game' button to start again.");
            // Здесь вы можете также обработать логику завершения игры или начала новой игры
        }
    }

}
