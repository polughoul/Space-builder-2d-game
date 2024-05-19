package main.BasicClasses;

import javafx.scene.image.Image;

import java.util.*;

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
            spaceObjects.add(new Asteroid( "Asteroid" + i, 20, random.nextInt(1024), random.nextInt(724), "main/assets/Asteroid1.jpg"));
        }

        List<String> planetImages = Arrays.asList("main/assets/Planet1.png", "main/assets/Planet2.png", "main/assets/Planet3.png");
        List<String> buildingImages = Arrays.asList("main/assets/Building1.png", "main/assets/Building2.png", "main/assets/Building3.png");

        for (int i = 0; i < 3; i++) {
            List<Building> buildings = new ArrayList<>();
            buildings.add(new Building("building1", createBuilding1Cost(), new Image("file:" + buildingImages.get(0))));
            buildings.add(new Building("building2", createBuilding2Cost(), new Image("file:" + buildingImages.get(1))));
            buildings.add(new Building("building3", createBuilding3Cost(), new Image("file:" + buildingImages.get(2))));

            String planetImage = planetImages.get(i);

            spaceObjects.add(new Planet("Planet" + i, 40,random.nextInt(1024), random.nextInt(724), new ArrayList<>(buildings), planetImage));
        }
    }

    private Map<String, Integer> createBuilding1Cost() {
        Map<String, Integer> building1Cost = new HashMap<>();
        building1Cost.put("Resource1", 10);
        building1Cost.put("Resource2", 10);
        return building1Cost;
    }

    private Map<String, Integer> createBuilding2Cost() {
        Map<String, Integer> building2Cost = new HashMap<>();
        building2Cost.put("Resource2", 10);
        building2Cost.put("Resource3", 10);
        return building2Cost;
    }

    private Map<String, Integer> createBuilding3Cost() {
        Map<String, Integer> building3Cost = new HashMap<>();
        building3Cost.put("Resource1", 10);
        building3Cost.put("Resource3", 10);
        return building3Cost;
    }
}