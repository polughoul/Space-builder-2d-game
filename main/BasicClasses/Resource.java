package main.BasicClasses;

import javafx.scene.canvas.GraphicsContext;

public class Resource {
    private String type;
    private int counts;
    private int x;
    private int y;

    public boolean isPlayerOnResource(int playerX, int playerY) {
        int dx = this.x - playerX;
        int dy = this.y - playerY;
        int distance = (int) Math.sqrt(dx * dx + dy * dy);
        return distance <= 10 / 2;
    }

    public Resource(String type, int counts, int x, int y){
        this.type = type;
        this.counts = counts;
        this.x = x;
        this.y = y;
    }
    public void draw(GraphicsContext gc) {
        gc.setFill(javafx.scene.paint.Color.BLACK);
        gc.fillText(type + ": " + counts, x - 10 / 2, y - 10 / 2);
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
