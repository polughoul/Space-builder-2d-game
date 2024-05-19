package main.BasicClasses;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.*;

public class Planet extends SpaceObject {
    private List<Resource> resources;
    private List<Bandit> bandits;
    private List<Builder> builders;
    private List<Building> buildings = new ArrayList<>();
    private Image image;


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

    public Planet(String name, int size, int x, int y, List<Building> buildings, String imagePath)
    {
        super(name, size, x, y);
        availableBuildings.addAll(buildings);
        this.image = new Image("file:" + imagePath);

    }
    public Image getImage() {
        return image;
    }

    public void removeResource(Resource resource) {
        resources.remove(resource);
    }

    public List<Resource> getResources() {
        if (resources == null) {
            resources = new ArrayList<>();
            Random random = new Random();
            Map<String, Image> resourceImages = new HashMap<>();
            resourceImages.put("silver", new Image("file:main/assets/silver.png"));
            resourceImages.put("ruby", new Image("file:main/assets/rubin.png"));
            resourceImages.put("obsidian", new Image("file:main/assets/obsidian.png"));
            resourceImages.put("nephrite", new Image("file:main/assets/nefrit.png"));
            resourceImages.put("iron", new Image("file:main/assets/metal.png"));
            resourceImages.put("gold", new Image("file:main/assets/gold.png"));
            resourceImages.put("lazurite", new Image("file:main/assets/lazurit.png"));
            List<String> resourceTypes = new ArrayList<>(resourceImages.keySet());
            for (int i = 0; i < 7; i++) {
                int resourceX = random.nextInt(1240);
                int resourceY = random.nextInt(900);
                String resourceType = resourceTypes.get(random.nextInt(resourceTypes.size()));
                resources.add(new Resource(resourceType, random.nextInt(100), resourceX, resourceY, resourceImages.get(resourceType)));
            }
        }
        return resources;
    }

    public List<Bandit> getBandits() {
        if (bandits == null) {
            bandits = new ArrayList<>();
            Random random = new Random();
            String[] banditImages = {"main/assets/bandit.png", "main/assets/bandit1.png", "main/assets/bandit2.png", "main/assets/bandit3.png", "main/assets/bandit4.png", "main/assets/bandit5.png", "main/assets/bandit6.png", "main/assets/bandit7.png", "main/assets/bandit8.png", "main/assets/bandit9.png", "main/assets/bandit10.png" };
            for (int i = 0; i < 1; i++) {
                int banditX = random.nextInt(1240);
                int banditY = random.nextInt(900);
                bandits.add(new Bandit(1, 100, 10, banditX, banditY, 2, banditImages));
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
                builders.add(new Builder( builderResources, builderX, builderY, "main/assets/builder.png"));
            }
        }
        return builders;
    }

    public void addBuilding(Building building) {
        buildings.add(building);
    }

    // В классе Planet
    public void drawBuildings(GraphicsContext gc) {
        for (Building building : buildings) {
            gc.drawImage(building.getImage(), building.getX(), building.getY(), 40, 40);
        }
    }
    public void removeBandit(Bandit bandit) {
        bandits.remove(bandit);
    }
}