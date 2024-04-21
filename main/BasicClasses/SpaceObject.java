package main.BasicClasses;

import java.util.List;

public abstract class SpaceObject {
    private List<Resource> resources;
    private String name;
    private int size;
    private int x;
    private int y;

    private Resource resource;

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public SpaceObject(List<Resource> resources, String name, int size, int x, int y){
        this.resources = resources;
        this.name = name;
        this.size = size;
        this.x = x;
        this.y = y;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void explore() {
        // Здесь будет логика исследования космического объекта
    }

    public void collectResources(Player player) {
        // Здесь будет логика сбора ресурсов игроком
    }

    public SpaceObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isPlayerOnObject(int playerX, int playerY) {
        // Проверяем, находятся ли координаты игрока внутри области объекта
        return playerX >= x && playerX <= x + 50 && playerY >= y && playerY <= y + 50;
    }

    public void collect(Player player) {
        // Пустая реализация, которую можно переопределить в подклассах
    }

}