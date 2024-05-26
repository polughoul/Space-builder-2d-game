package main.Controller;

import main.BasicClasses.Planet;
import main.BasicClasses.SpaceObject;

import java.util.List;

/**
 * The gameRules class represents the rules of the game.
 * It contains methods to check if the player has won the game.
 */
public class gameRules {
    /**
     * Checks if the player has won the game.
     * The player wins if all planets have at least 3 buildings.
     *
     * @param spaceObjects The list of space objects in the game.
     * @return True if the player has won, false otherwise.
     */
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