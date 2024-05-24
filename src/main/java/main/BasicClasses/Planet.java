package main.BasicClasses;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.*;

/**
 * The Planet class represents a planet in the game.
 * It extends the SpaceObject class and contains properties and methods specific to a planet.
 */
public class Planet extends SpaceObject {
    private List<Resource> resources;
    private List<Bandit> bandits;
    private List<Builder> builders;
    private List<Building> buildings = new ArrayList<>();
    private transient Image image;
    private String imagePath;
    private List<Building> availableBuildings = new ArrayList<>();

    /**
     * Adds a Bandit to the Planet.
     *
     * @param bandit The Bandit to add.
     */
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

    /**
     * Removes an available Building from the Planet.
     *
     * @param building The Building to remove.
     */

    public void removeAvailableBuilding(Building building) {
        availableBuildings.remove(building);
    }

    /**
     * Constructs a new Planet with the given parameters.
     *
     * @param name The name of the Planet.
     * @param size The size of the Planet.
     * @param x The x-coordinate of the Planet.
     * @param y The y-coordinate of the Planet.
     * @param buildings The list of buildings on the Planet.
     * @param imagePath The path to the image representing the Planet.
     */
    public Planet(String name, int size, int x, int y, List<Building> buildings, String imagePath)
    {
        super(name, size, x, y);
        availableBuildings.addAll(buildings);
        this.image = new Image("file:" + imagePath);
        this.imagePath = imagePath;
        this.bandits = new LinkedList<>();

    }
    public Image getImage() {
        if (image == null) {
            image = new Image("file:" + imagePath);
        }
        return image;
    }

    @Override
    public void removeResource(Resource resource) {
        resources.remove(resource);
    }

    /**
     * Returns the list of resources available on the Planet.
     * If the resources list is null, it creates a new list of resources.
     *
     * @return The list of resources available on the Planet.
     */
    public List<Resource> getResources() {
        if (resources == null) {
            resources = new ArrayList<>();
            Random random = new Random();
            List<String> resourceTypes = Arrays.asList("silver", "ruby", "obsidian", "nephrite", "iron", "gold", "lazurite");
            for (int i = 0; i < resourceTypes.size(); i++) {
                int resourceX = random.nextInt(1240);
                int resourceY = random.nextInt(900);
                String resourceType = resourceTypes.get(random.nextInt(resourceTypes.size()));
                resources.add(new Resource(resourceType, random.nextInt(100), resourceX, resourceY, "src/main/java/main/assets/" + resourceType + ".png"));
            }
        }
        return Collections.unmodifiableList(resources);
    }

    /**
     * Returns the list of Bandits on the Planet.
     * If the bandits list is null, it creates a new list of Bandits.
     *
     * @return The list of Bandits on the Planet.
     */
    public List<Bandit> getBandits() {
        if (bandits == null) {
            bandits = new LinkedList<>();
            Random random = new Random();
            String[] banditImages = {"src/main/java/main/assets/bandit.png", "src/main/java/main/assets/bandit1.png", "src/main/java/main/assets/bandit2.png", "src/main/java/main/assets/bandit3.png", "src/main/java/main/assets/bandit4.png", "src/main/java/main/assets/bandit5.png", "src/main/java/main/assets/bandit6.png", "src/main/java/main/assets/bandit7.png", "src/main/java/main/assets/bandit8.png", "src/main/java/main/assets/bandit9.png", "src/main/java/main/assets/bandit10.png"};
            for (int i = 0; i < 1; i++) {
                int banditX = random.nextInt(1240);
                int banditY = random.nextInt(900);
                bandits.add(new Bandit(1, 100, 10, banditX, banditY, 2, banditImages));
            }
        }
        return bandits;
    }

    /**
     * Spawns a new Bandit every minute on the Planet.
     */
    public void spawnBandits() {
        Platform.runLater(() -> {
            Random random = new Random();
            int banditX = random.nextInt(1240);
            int banditY = random.nextInt(900);
            String[] banditImages = {"src/main/java/main/assets/bandit.png", "src/main/java/main/assets/bandit1.png", "src/main/java/main/assets/bandit2.png", "src/main/java/main/assets/bandit3.png", "src/main/java/main/assets/bandit4.png", "src/main/java/main/assets/bandit5.png", "src/main/java/main/assets/bandit6.png", "src/main/java/main/assets/bandit7.png", "src/main/java/main/assets/bandit8.png", "src/main/java/main/assets/bandit9.png", "src/main/java/main/assets/bandit10.png"};
            Bandit newBandit = new Bandit(1, 100, 10, banditX, banditY, 2, banditImages);
            addBandit(newBandit);
        });
    }

    /**
     * Returns the list of Builders on the Planet.
     * If the builders list is null, it creates a new list of Builders.
     *
     * @return The list of Builders on the Planet.
     */
    public List<Builder> getBuilders() {
        if (builders == null) {
            builders = new LinkedList<>();
            Random random = new Random();
            for (int i = 0; i < 5; i++) {
                int builderX = random.nextInt(1240);
                int builderY = random.nextInt(900);
                HashMap<String, Integer> builderResources = new HashMap<>();
                builderResources.put("wood", 10);
                builderResources.put("Resource1", 10);
                builders.add(new Builder( builderResources, builderX, builderY, "src/main/java/main/assets/builder.png"));
            }
        }
        return builders;
    }

    /**
     * Adds a Building to the Planet.
     *
     * @param building The Building to add.
     */
    public void addBuilding(Building building) {
        buildings.add(building);
    }

    /**
     * Adds an available Building to the Planet.
     *
     * @param building The Building to add.
     */
    public void addAvailableBuilding(Building building) {
        building.setHealth(building.getMaxHealth());
        availableBuildings.add(building);
    }

    /**
     * Draws the Planet on the game canvas.
     *
     * @param gc The GraphicsContext object used for drawing.
     */
    public void drawBuildings(GraphicsContext gc) {
        for (Building building : buildings) {
            gc.drawImage(building.getImage(), building.getX(), building.getY(), 40, 40);

            gc.setFill(Color.RED);
            gc.fillRect(building.getX(), building.getY() - 10, 40, 5);
            gc.setFill(Color.GREEN);
            gc.fillRect(building.getX(), building.getY() - 10, 40 * ((double) building.getHealth() / 100), 5);
        }
    }

    /**
     * Removes a Bandit from the Planet.
     *
     * @param bandit The Bandit to remove.
     */
    public void removeBandit(Bandit bandit) {
        bandits.remove(bandit);
    }

    /**
     * Removes a Building from the Planet.
     *
     * @param building The Building to remove.
     */
    public void removeBuilding(Building building) {
        buildings.remove(building);
    }
}