package main.BasicClasses;

import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.HashMap;

public class Planet extends SpaceObject {
    private List<Resource> resources;
    private List<Bandit> bandits;
    private List<Builder> builders;
    private List<Building> buildings = new ArrayList<>();

    private List<Building> availableBuildings = new ArrayList<>();

    public List<Building> getAvailableBuildings() {
        return availableBuildings;
    }
    public List<Building> getBuildings() {
        return buildings;
    }

    public void setAvailableBuildings(List<Building> availableBuildings) {
        this.availableBuildings = availableBuildings;
    }

    public void removeAvailableBuilding(Building building) {
        availableBuildings.remove(building);
    }

    public Planet(String name, int size, int x, int y, List<Building> buildings)
    {
        super(name, size, x, y);
        availableBuildings.addAll(buildings);

    }

    public void removeResource(Resource resource) {
        resources.remove(resource);
    }

    public List<Resource> getResources() {
        if (resources == null) {
            resources = new ArrayList<>();
            Random random = new Random();
            for (int i = 0; i < 5; i++) {
                int resourceX = random.nextInt(1240);
                int resourceY = random.nextInt(900);
                resources.add(new Resource("Resource" + i, random.nextInt(100), resourceX, resourceY));
            }
        }
        return resources;
    }

    public List<Bandit> getBandits() {
        if (bandits == null) {
            bandits = new ArrayList<>();
            Random random = new Random();
            for (int i = 0; i < 1; i++) {
                int banditX = random.nextInt(1240);
                int banditY = random.nextInt(900);
                bandits.add(new Bandit(1, 100, 10, banditX, banditY, 1));
            }
        }
        return bandits;
    }

    public List<Builder> getBuilders() {
        if (builders == null) {
            builders = new ArrayList<>();
            Random random = new Random();
            for (int i = 0; i < 5; i++) {
                int builderX = random.nextInt(1240);
                int builderY = random.nextInt(900);
                HashMap<String, Integer> builderResources = new HashMap<>();
                builderResources.put("wood", 10);
                builderResources.put("Resource1", 10);
                builders.add(new Builder( builderResources, builderX, builderY));
            }
        }
        return builders;
    }

    public void addBuilding(Building building) {
        buildings.add(building);
    }

    public void drawBuildings(GraphicsContext gc) {
        for (Building building : buildings) {
            gc.strokeRect(building.getX(), building.getY(), 40, 40);
        }
    }
    public void removeBandit(Bandit bandit) {
        bandits.remove(bandit);
    }


}