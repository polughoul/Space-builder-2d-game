package main.BasicClasses;

import javafx.scene.image.Image;
import java.util.*;

/**
 * This class represents an Asteroid in the game.
 * It extends the SpaceObject class and contains additional properties and methods specific to an Asteroid.
 */
public class Asteroid extends SpaceObject {
    private List<Resource> resources;
    private transient Image image;
    private String imagePath;

    /**
     * Constructs a new Asteroid with the given parameters.
     *
     * @param name The name of the Asteroid.
     * @param size The size of the Asteroid.
     * @param x The x-coordinate of the Asteroid.
     * @param y The y-coordinate of the Asteroid.
     * @param imagePath The path to the image representing the Asteroid.
     */
    public Asteroid(String name, int size, int x, int y, String imagePath) {
        super(name, size, x, y);
        this.imagePath = imagePath;
        this.image = new Image("file:" + imagePath);
    }

    /**
     * Removes a specific resource from the Asteroid's list of resources.
     *
     * @param resource The resource to be removed.
     */
    @Override
    public void removeResource(Resource resource) {
        resources.remove(resource);
    }

    /**
     * Returns the image representing the Asteroid.
     * If the image is null, it creates a new Image using the imagePath.
     *
     * @return The image representing the Asteroid.
     */
    public Image getImage() {
        if (image == null) {
            image = new Image("file:" + imagePath);
        }
        return image;
    }

    /**
     * Returns the list of resources available on the Asteroid.
     * If the resources list is null, it creates a new list of resources.
     *
     * @return The list of resources available on the Asteroid.
     */
    @Override
    public List<Resource> getResources() {
        if (resources == null) {
            resources = new ArrayList<>();
            Random random = new Random();
            List<String> resourceTypes = Arrays.asList("silver", "ruby", "obsidian", "nephrite", "iron", "gold", "lazurite");
            for (int i = 0; i < resourceTypes.size(); i++) {
                int resourceX = random.nextInt(1240);
                int resourceY = random.nextInt(900);
                String resourceType = resourceTypes.get(random.nextInt(resourceTypes.size()));
                resources.add(new Resource(resourceType, random.nextInt(100), resourceX, resourceY, "main/assets/" + resourceType + ".png"));
            }
        }
        return Collections.unmodifiableList(resources);
    }
}