package main.BasicClasses;

import javafx.scene.image.Image;

import java.util.*;

public class Asteroid extends SpaceObject {
    private List<Resource> resources;
    private Image image;

    public Asteroid(String name, int size, int x, int y, String imagePath) {
        super(name, size, x, y);
        this.image = new Image("file:" + imagePath);
    }

    public void removeResource(Resource resource) {
        resources.remove(resource);
    }
    public Image getImage() {
        return image;
    }


    @Override
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
}