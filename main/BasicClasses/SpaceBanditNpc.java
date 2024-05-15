package main.BasicClasses;

import java.awt.*;

public class SpaceBanditNpc {
    private String name;
    private int level;
    private int health;
    private int damage;
    private int x;
    private int y;
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.drawString(name, x, y);
    }

    public SpaceBanditNpc(String name, int level, int health, int damage, int x, int y) {
        this.name = name;
        this.level = level;
        this.health = health;
        this.damage = damage;
        this.x = x;
        this.y = y;
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
