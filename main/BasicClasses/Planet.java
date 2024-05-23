package main.BasicClasses;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.*;

public class Planet extends SpaceObject {
    private List<Resource> resources;
    private List<Bandit> bandits;
    private List<Builder> builders;
    private List<Building> buildings = new ArrayList<>();
    private transient Image image;
    private String imagePath;


    private List<Building> availableBuildings = new ArrayList<>();

    public void addBandit(Bandit bandit) {
        bandits.add(bandit);
    }

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
        this.imagePath = imagePath;
        this.bandits = new ArrayList<>();

    }
    public Image getImage() {
        if (image == null) {
            image = new Image("file:" + imagePath);
        }
        return image;
    }

    public void removeResource(Resource resource) {
        resources.remove(resource);
    }

    public List<Resource> getResources() {
        if (resources == null) {
            resources = new ArrayList<>();
            Random random = new Random();
            List<String> resourceTypes = Arrays.asList("silver", "ruby", "obsidian", "nephrite", "iron", "gold", "lazurite");
            for (int i = 0; i < 7; i++) {
                int resourceX = random.nextInt(1240);
                int resourceY = random.nextInt(900);
                String resourceType = resourceTypes.get(random.nextInt(resourceTypes.size()));
                resources.add(new Resource(resourceType, random.nextInt(100), resourceX, resourceY, "main/assets/" + resourceType + ".png"));
            }
        }
        return Collections.unmodifiableList(resources);
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

    public void spawnBandits() {
        Platform.runLater(() -> {
            Random random = new Random();
            int banditX = random.nextInt(1240);
            int banditY = random.nextInt(900);
            String[] banditImages = {"main/assets/bandit.png", "main/assets/bandit1.png", "main/assets/bandit2.png", "main/assets/bandit3.png", "main/assets/bandit4.png", "main/assets/bandit5.png", "main/assets/bandit6.png", "main/assets/bandit7.png", "main/assets/bandit8.png", "main/assets/bandit9.png", "main/assets/bandit10.png" };
            Bandit newBandit = new Bandit(1, 100, 10, banditX, banditY, 2, banditImages);
            addBandit(newBandit);
        });
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
    public void addAvailableBuilding(Building building) {
        building.setHealth(building.getMaxHealth());
        availableBuildings.add(building);
    }

    public void drawBuildings(GraphicsContext gc) {
        for (Building building : buildings) {
            gc.drawImage(building.getImage(), building.getX(), building.getY(), 40, 40);

            gc.setFill(Color.RED);
            gc.fillRect(building.getX(), building.getY() - 10, 40, 5);
            gc.setFill(Color.GREEN);
            gc.fillRect(building.getX(), building.getY() - 10, 40 * ((double) building.getHealth() / 100), 5);
        }
    }
    public void removeBandit(Bandit bandit) {
        bandits.remove(bandit);
    }

    public void removeBuilding(Building building) {
        buildings.remove(building);
    }
}