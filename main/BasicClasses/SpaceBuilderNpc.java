package main.BasicClasses;

public class SpaceBuilderNpc extends SpaceShip {

    private String resourced_focus;

    public SpaceBuilderNpc(String name, int fuel, int health, int capacity, int money, String resourced_focus) {
        super(name, fuel, health, capacity);
            this.resourced_focus = resourced_focus;
    }

    public String getResourced_focus() {
        return resourced_focus;
    }

    public void setResourced_focus(String resourced_focus) {
        this.resourced_focus = resourced_focus;
    }
}
