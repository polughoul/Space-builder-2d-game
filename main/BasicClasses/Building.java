package main.BasicClasses;

import java.util.Map;

public class Building {
    private String type;
    private Map<String, Integer> cost;
    int x;
    int y;


    public Building(String type, Map<String, Integer> cost) {
        this.type = type;
        this.cost = cost;
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
}