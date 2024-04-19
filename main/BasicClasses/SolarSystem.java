package main.BasicClasses;

import java.util.List;

public class SolarSystem {
    private List<SpaceObject> spaceObjects;
    private List<SpaceBuilderNpc> spaceBuilders;
    private List<SpaceBanditNpc> spaceBandits;

    public List<SpaceObject> getSpaceObjects() {
        return spaceObjects;
    }

    public void setSpaceObjects(List<SpaceObject> spaceObjects) {
        this.spaceObjects = spaceObjects;
    }

    public List<SpaceBuilderNpc> getSpaceBuilders() {
        return spaceBuilders;
    }

    public void setSpaceBuilders(List<SpaceBuilderNpc> spaceBuilders) {
        this.spaceBuilders = spaceBuilders;
    }

    public List<SpaceBanditNpc> getSpaceBandits() {
        return spaceBandits;
    }

    public void setSpaceBandits(List<SpaceBanditNpc> spaceBandits) {
        this.spaceBandits = spaceBandits;
    }
}
