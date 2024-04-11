package main.BasicClasses;

import java.util.List;

public class Level {

    private SolarSystem solarSystem;
    private List<Item> items;
    private List<SpaceBuilderNpc> spaceBuilder;
    private List<SpaceBanditNpc> spaceBandit;
    private List<SpaceStation> spaceStations;

    // Getters and setters for the attributes
    public SolarSystem getSolarSystem() {
        return solarSystem;
    }

    public void setSolarSystem(SolarSystem solarSystem) {
        this.solarSystem = solarSystem;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<SpaceBuilderNpc> getSpaceBuilder() {
        return spaceBuilder;
    }

    public void setSpaceBuilder(List<SpaceBuilderNpc> spaceBuilder) {
        this.spaceBuilder = spaceBuilder;
    }

    public List<SpaceBanditNpc> getSpaceBandit() {
        return spaceBandit;
    }

    public void setSpaceBandit(List<SpaceBanditNpc> spaceBandit) {
        this.spaceBandit = spaceBandit;
    }

    public List<SpaceStation> getSpaceStations() {
        return spaceStations;
    }

    public void setSpaceStations(List<SpaceStation> spaceStations) {
        this.spaceStations = spaceStations;
    }
}