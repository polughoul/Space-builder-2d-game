package main.BasicClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Galaxy {
    private String name;
    private List<SpaceObject> spaceObjects;

    public Galaxy(String name) {
        this.name = name;
        this.spaceObjects = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SpaceObject> getSpaceObjects() {
        return spaceObjects;
    }

    public void setSpaceObjects(List<SpaceObject> spaceObjects) {
        this.spaceObjects = spaceObjects;
    }

    public void createSpaceObjects() {
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            spaceObjects.add(new Asteroid( "Asteroid" + i, 20, random.nextInt(1240), random.nextInt(900)));
        }

        for (int i = 0; i < 3; i++) {
            spaceObjects.add(new Planet("Planet" + i, 40,random.nextInt(1240), random.nextInt(900)));
        }
    }
}