package main.Controller;

import main.BasicClasses.Planet;
import main.BasicClasses.SpaceObject;

import java.util.List;

public class gameRules {
    public boolean checkVictory(List<SpaceObject> spaceObjects) {
        for (SpaceObject spaceObject : spaceObjects) {
            if (spaceObject instanceof Planet) {
                Planet planet = (Planet) spaceObject;
                if (planet.getBuildings().size() < 3) {
                    return false;
                }
            }
        }
        return true;
    }
}