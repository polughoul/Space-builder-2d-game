package main.BasicClasses;

import java.awt.*;

public class Resource {
    private String type;
    private int counts;
    private int x;
    private int y;

    public Resource(String type, int counts, int x, int y){
        this.type = type;
        this.counts = counts;
        this.x = x;
        this.y = y;
    }
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
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
    public void collect(Player player) {
        player.collectResource(this);
        counts = 0;
    }
    public boolean isPlayerOnResource(int playerX, int playerY) {
        return playerX >= x && playerX <= x + 50 && playerY >= y && playerY <= y + 50;
    }
}
