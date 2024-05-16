package main.BasicClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class SpaceObject {
    private List<Resource> resources;
    private String name;
    private int size;
    private int x;
    private int y;

    public SpaceObject(String name, int size, int x, int y){
        this.name = name;
        this.size = size;
        this.x = x;
        this.y = y;
    }


    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public SpaceObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isPlayerOnObject(int playerX, int playerY) {
        int dx = this.x - playerX;
        int dy = this.y - playerY;
        int distance = (int) Math.sqrt(dx * dx + dy * dy);
        return distance <= this.size / 2;
    }

}