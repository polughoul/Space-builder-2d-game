package main.BasicClasses;

import javafx.scene.image.Image;

import java.util.*;

public class Asteroid extends SpaceObject {
    private List<Resource> resources;
    private transient Image image;
    private String imagePath;

    public Asteroid(String name, int size, int x, int y, String imagePath) {
        super(name, size, x, y);
        this.imagePath = imagePath;
        this.image = new Image("file:" + imagePath);
    }

    @Override
    public void removeResource(Resource resource) {
        resources.remove(resource);
    }
    public Image getImage() {
        if (image == null) {
            image = new Image("file:" + imagePath);
        }
        return image;
    }


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