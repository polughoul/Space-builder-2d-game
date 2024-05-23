package main.BasicClasses;

import java.util.Collections;
import java.util.List;

public class NullSpaceObject extends SpaceObject {
    public NullSpaceObject() {
        super("", 0, 0, 0);
    }

    @Override
    public List<Resource> getResources() {
        return Collections.emptyList();
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public boolean isPlayerOnObject(int playerX, int playerY) {
        return false;
    }
}