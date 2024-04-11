package main.BasicClasses;

public class SpaceBanditNpc extends SpaceShip{

    private String hostilityLevel;

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
}
