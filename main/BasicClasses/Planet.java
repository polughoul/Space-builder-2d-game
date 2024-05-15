package main.BasicClasses;

import java.util.List;
public class Planet extends SpaceObject {
    private List<SpaceBuilderNpc> spaceBuilders;
    private List<SpaceBanditNpc> spaceBandits;

    public Planet(List<Resource> resources, String name, int size, List<SpaceBuilderNpc> spaceBuilders, List<SpaceBanditNpc> spaceBandits, int x, int y) {
        super(name, size, x, y);
        this.spaceBuilders = spaceBuilders;
        this.spaceBandits = spaceBandits;
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