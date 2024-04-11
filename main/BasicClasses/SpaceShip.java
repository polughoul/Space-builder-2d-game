package main.BasicClasses;

public class SpaceShip{

    private String name;
    private int fuel;
    private int health;
    private int capacity;

    public SpaceShip(String name, int fuel, int health, int capacity) {
        this.name = name;
        this.fuel = fuel;
        this.health = health;
        this.capacity = capacity;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

}