package main.BasicClasses;

import java.util.List;

public class Galaxy {
    private String name;
    private List<SpaceObject> spaceObjects;

    public Galaxy(String name, List<SpaceObject> spaceObjects) {
        this.name = name;
        this.spaceObjects = spaceObjects;
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
}