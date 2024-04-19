package main.BasicClasses;

public class SpaceStation {
    private int health;
    private int production;

    public SpaceStation(int health, int productionCapacity) {
        this.health = health;
        this.production = productionCapacity;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getProductionCapacity() {
        return production;
    }

    public void setProductionCapacity(int productionCapacity) {
        this.production = productionCapacity;
    }

    public void buildStation(Player player, Resource requiredResource) {
        if (player.hasEnoughResources(requiredResource)) {
            player.decreaseResource(requiredResource);
        }
    }

    public void upgradeStation(Player player, Resource requiredResource) {
        if (player.hasEnoughResources(requiredResource)) {
            player.decreaseResource(requiredResource);
        }
    }
}