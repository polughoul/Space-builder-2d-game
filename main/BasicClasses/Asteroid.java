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
                resources.add(new Resource("Resource" + i, random.nextInt(100), getX() + random.nextInt(getSize() * 20), getY() + random.nextInt(getSize() * 20)));
            }
        }
        return resources;
    }
}