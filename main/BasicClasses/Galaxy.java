package main.BasicClasses;
import java.util.*;

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
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            spaceObjects.add(new Asteroid( "Asteroid" + i, 40, random.nextInt(1024), random.nextInt(724), "main/assets/11.png"));
        }

        List<String> planetImages = Arrays.asList("main/assets/14.png", "main/assets/16.png", "main/assets/17.png");
        List<String> buildingImages = Arrays.asList("main/assets/Building1.png", "main/assets/Building2.png", "main/assets/Building3.png");

        for (int i = 0; i < 3; i++) {
            List<Building> buildings = new ArrayList<>();
            buildings.add(new Building("building1", createBuilding1Cost(), buildingImages.get(0), 100));
            buildings.add(new Building("building2", createBuilding2Cost(), buildingImages.get(1), 100));
            buildings.add(new Building("building3", createBuilding3Cost(), buildingImages.get(2), 100));

            String planetImage = planetImages.get(i);

            spaceObjects.add(new Planet("Planet" + i, 60,random.nextInt(1024), random.nextInt(724), new ArrayList<>(buildings), planetImage));
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