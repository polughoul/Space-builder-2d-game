package main.BasicClasses;

import java.util.List;

public class Asteroid extends SpaceObject {
    public Asteroid(List<Resource> resources, String name, int size, int x, int y) {
        super(resources, name, size, x, y);
    }
}