package main.BasicClasses;

import java.awt.*;

public class Resource {
    private String type;
    private int counts;
    private int x;
    private int y;

    public boolean isPlayerOnResource(int playerX, int playerY) {
        int dx = this.x - playerX;
        int dy = this.y - playerY;
        int distance = (int) Math.sqrt(dx * dx + dy * dy);
        int resourceSize = 10; // Замените это на реальный размер ресурса
        return distance <= resourceSize;
    }

    public Resource(String type, int counts, int x, int y){
        this.type = type;
        this.counts = counts;
        this.x = x;
        this.y = y;
    }
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawString(type + ": " + counts, x, y);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

}
