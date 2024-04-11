package main.BasicClasses;

public class Technology {
    private String name;
    private int level;
    private int cost;

    public Technology(String name, int level, int cost) {
        this.name = name;
        this.level = level;
        this.cost = cost;
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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void apply(SpaceShip ship) {
        //  apply technology to ship
    }
}