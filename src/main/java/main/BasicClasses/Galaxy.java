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

    public void setName(String name) {
        this.name = name;
    }

    public List<SpaceObject> getSpaceObjects() {
        return spaceObjects;
    }

    public void setSpaceObjects(List<SpaceObject> spaceObjects) {
        this.spaceObjects = spaceObjects;
    }

    /**
     * Creates and adds SpaceObjects to the Galaxy.
     */
    public void createSpaceObjects() {
        Gson gson = new Gson();
        try {
            Reader reader = new FileReader("map.json");
            GalaxyData galaxyData = gson.fromJson(reader, GalaxyData.class);

            for (SpaceObjectData spaceObjectData : galaxyData.spaceObjects) {
                if (spaceObjectData.type.equals("Asteroid")) {
                    spaceObjects.add(new Asteroid(spaceObjectData.name, spaceObjectData.size, spaceObjectData.x, spaceObjectData.y, spaceObjectData.imagePath));
                } else if (spaceObjectData.type.equals("Planet")) {
                    List<Building> buildings = new ArrayList<>();
                    for (BuildingData buildingData : spaceObjectData.buildings) {
                        buildings.add(new Building(buildingData.name, buildingData.cost, buildingData.imagePath, buildingData.health));
                    }
                    spaceObjects.add(new Planet(spaceObjectData.name, spaceObjectData.size, spaceObjectData.x, spaceObjectData.y, buildings, spaceObjectData.imagePath));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the cost for building 1.
     *
     * @return The cost for building 1.
     */
    private Map<String, Integer> createBuilding1Cost() {
        Map<String, Integer> building1Cost = new HashMap<>();
        building1Cost.put("iron", 10);
        building1Cost.put("lazurite", 10);
        return building1Cost;
    }

    /**
     * Creates the cost for building 2.
     *
     * @return The cost for building 2.
     */
    private Map<String, Integer> createBuilding2Cost() {
        Map<String, Integer> building2Cost = new HashMap<>();
        building2Cost.put("gold", 10);
        building2Cost.put("ruby", 10);
        return building2Cost;
    }

    /**
     * Creates the cost for building 3.
     *
     * @return The cost for building 3.
     */
    private Map<String, Integer> createBuilding3Cost() {
        Map<String, Integer> building3Cost = new HashMap<>();
        building3Cost.put("silver", 10);
        building3Cost.put("obsidian", 10);
        return building3Cost;
    }
}