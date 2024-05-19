package main.BasicClasses;

import java.util.Map;
import javafx.scene.image.Image;

public class Building {
    private String type;
    private Map<String, Integer> cost;
    int x;
    int y;
    private Image image;


    public Building(String type, Map<String, Integer> cost,  Image image) {
        this.type = type;
        this.cost = cost;
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public String getType() {
        return type;
    }

    public Map<String, Integer> getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return type + " (cost: " + cost + ")";
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}