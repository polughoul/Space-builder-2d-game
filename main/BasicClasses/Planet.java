package main.BasicClasses;

import java.util.List;

public class Planet extends SpaceObject {
    private List<SpaceBuilderNpc> spaceBuilder;
    private List<SpaceBanditNpc> spaceBandit;

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
}