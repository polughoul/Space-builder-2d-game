package main.BasicClasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.image.Image;

public abstract class SpaceObject implements Serializable {
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
        this.resources = new ArrayList<>();
    }

    public void removeResource(Resource resource) {
    }

    public List<Resource> getResources() {
        return resources;
    }

    public int getSize() {
        return size;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public abstract Image getImage();

    public boolean isPlayerOnObject(int playerX, int playerY) {
        int dx = this.x - playerX;
        int dy = this.y - playerY;
        int distance = (int) Math.sqrt(dx * dx + dy * dy);
        return distance <= this.size / 2;
    }

}