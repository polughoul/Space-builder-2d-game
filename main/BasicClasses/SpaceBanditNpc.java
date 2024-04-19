package main.BasicClasses;

public class SpaceBanditNpc extends SpaceShip{

    private String hostilityLevel;
    private int level;

    public SpaceBanditNpc(String name, int fuel, int health, int capacity, int money, String hostilityLevel) {
        super(name, fuel, health, capacity);
        this.hostilityLevel = hostilityLevel;
    }

    public String getHostilityLevel() {
        return hostilityLevel;
    }

    public void setHostilityLevel(String hostilityLevel) {
        this.hostilityLevel = hostilityLevel;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void attack(Player player) {
        int damage = this.getLevel() * 10;
        player.decreaseHealth(damage);
    }
}
