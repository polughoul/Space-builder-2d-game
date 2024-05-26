package main.BasicClasses;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.*;

import main.data.BuildingData;
import main.data.GalaxyData;
import main.data.SpaceObjectData;

/**
 * The Galaxy class represents a galaxy in the game.
 * It contains properties and methods specific to a galaxy.
 */

public class Galaxy {
    private String name;
    private List<SpaceObject> spaceObjects;

    /**
     * Constructs a new Galaxy with the given name.
     *
     * @param name The name of the Galaxy.
     */
    public Galaxy(String name) {
        this.name = name;
        this.spaceObjects = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<SpaceObject> getSpaceObjects() {
        return spaceObjects;
    }

    /**
     * Creates and adds SpaceObjects to the Galaxy.
     */
    public void createSpaceObjects() {
        Gson gson = new Gson();
        try {
            // Read the JSON file
            Reader reader = new FileReader("map.json");
            // Convert the JSON file to GalaxyData object
            GalaxyData galaxyData = gson.fromJson(reader, GalaxyData.class);

            // Iterate over each SpaceObjectData in the GalaxyData
            for (SpaceObjectData spaceObjectData : galaxyData.spaceObjects) {
                // If the SpaceObjectData is of type "Asteroid"
                if (spaceObjectData.type.equals("Asteroid")) {
                    // Create a new Asteroid and add it to the spaceObjects list
                    spaceObjects.add(new Asteroid(spaceObjectData.name, spaceObjectData.size, spaceObjectData.x, spaceObjectData.y, spaceObjectData.imagePath));
                }
                // If the SpaceObjectData is of type "Planet"
                else if (spaceObjectData.type.equals("Planet")) {
                    List<Building> buildings = new ArrayList<>();
                    for (BuildingData buildingData : spaceObjectData.buildings) {
                        buildings.add(new Building(buildingData.name, buildingData.cost, buildingData.imagePath, buildingData.health));
                    }
                    spaceObjects.add(new Planet(spaceObjectData.name, spaceObjectData.size, spaceObjectData.x, spaceObjectData.y, buildings, spaceObjectData.imagePath));
                }
            }
        } catch (FileNotFoundException e) {
            // Print the stack trace if the file is not found
            e.printStackTrace();
        }
    }
}