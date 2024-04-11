package main.BasicClasses;

public class Player extends SpaceShip {
    private int money;

    public Player(String name, int fuel, int health, int capacity, int money) {
        super(name, fuel, health, capacity);
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}