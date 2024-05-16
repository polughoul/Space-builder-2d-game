package main.BasicClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Asteroid extends SpaceObject {
    private List<Resource> resources;

    public Asteroid(String name, int size, int x, int y) {
        super(name, size, x, y);
    }

    public void removeResource(Resource resource) {
        resources.remove(resource);
    }

    @Override
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
}