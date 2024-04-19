package main.BasicClasses;

import java.util.List;

public abstract class SpaceObject {
    private List<Resource> resources;
    private String name;
    private int size;

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public void explore() {
    }

    public void collectResources(Player player) {
    }
}