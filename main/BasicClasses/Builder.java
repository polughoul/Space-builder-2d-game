package main.BasicClasses;

import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

import java.util.HashMap;

public class Builder {
    private String name;
    private int level;
    private HashMap<String, Integer> resources;
    private int x;
    private int y;


    public Builder(String name, int level, HashMap<String, Integer> resources, int x, int y) {
        this.name = name;
        this.level = level;
        this.resources = resources;
        this.x = x;
        this.y = y;
    }
    public void draw(GraphicsContext gc) {
        gc.setFill(javafx.scene.paint.Color.RED);
        gc.fillText("builder", x - 10 / 2, y - 10 / 2);
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

    public HashMap<String, Integer> getResources() {
        return resources;
    }

    public void setResources(HashMap<String, Integer> resources) {
        this.resources = resources;
    }

}