package main.BasicClasses;

import javafx.scene.canvas.GraphicsContext;

import java.util.HashMap;

public class Builder {
    private int level;
    private HashMap<String, Integer> resources;
    private int x;
    private int y;


    public Builder(int level, HashMap<String, Integer> resources, int x, int y) {
        this.level = level;
        this.resources = resources;
        this.x = x;
        this.y = y;
    }
    public void draw(GraphicsContext gc) {
        gc.setFill(javafx.scene.paint.Color.PURPLE);
        gc.fillRect(x - 10 / 2, y - 10 / 2, 10, 10);
    }

    public boolean isPlayerOnBuilder(int playerX, int playerY) {
        double distance = Math.sqrt(Math.pow(playerX - this.x, 2) + Math.pow(playerY - this.y, 2));
        return distance <= 5;
    }



    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public HashMap<String, Integer> getResources() {
        return resources;
    }

    public void setResources(HashMap<String, Integer> resources) {
        this.resources = resources;
    }

}