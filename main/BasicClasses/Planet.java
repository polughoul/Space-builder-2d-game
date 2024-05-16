package main.BasicClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.HashMap;

public class Planet extends SpaceObject {
    private List<Resource> resources;
    private List<Bandit> bandits;
    private List<Builder> builders;

    public Planet(String name, int size, int x, int y)
    {
        super(name, size, x, y);
    }

    public void removeResource(Resource resource) {
        resources.remove(resource);
    }

    public List<Resource> getResources() {
        if (resources == null) {
            resources = new ArrayList<>();
            Random random = new Random();
            for (int i = 0; i < 5; i++) {
                int resourceX = random.nextInt(1240);
                int resourceY = random.nextInt(900);
                resources.add(new Resource("Wood" + i, random.nextInt(100), resourceX, resourceY));
            }
        }
        return resources;
    }

    public List<Bandit> getBandits() {
        if (bandits == null) {
            bandits = new ArrayList<>();
            Random random = new Random();
            for (int i = 0; i < 5; i++) {
                int banditX = random.nextInt(1240);
                int banditY = random.nextInt(900);
                bandits.add(new Bandit("Bandit" + i, 1, 100, 50, banditX, banditY));
            }
        }
        return bandits;
    }

    public List<Builder> getBuilders() {
        if (builders == null) {
            builders = new ArrayList<>();
            Random random = new Random();
            for (int i = 0; i < 5; i++) {
                int builderX = random.nextInt(1240);
                int builderY = random.nextInt(900);
                HashMap<String, Integer> builderResources = new HashMap<>();
                builderResources.put("wood", 10);
                builders.add(new Builder("Builder" + i, 1, builderResources, builderX, builderY));
            }
        }
        return builders;
    }

    public void addBandit(Bandit bandit) {
        bandits.add(bandit);
    }

    public void addBuilder(Builder builder) {
        builders.add(builder);
    }

}