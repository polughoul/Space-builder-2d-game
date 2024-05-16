package main.BasicClasses;

import java.awt.*;

public class Bandit {
    private String name;
    private int level;
    private int health;
    private int damage;
    private int x;
    private int y;

    public Bandit(String name, int level, int health, int damage, int x, int y) {
        this.name = name;
        this.level = level;
        this.health = health;
        this.damage = damage;
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.drawString(  "bandit " , x - 10 / 2, y - 10 / 2);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

}
