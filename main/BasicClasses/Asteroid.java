package main.BasicClasses;

public class Asteroid extends SpaceObject{
    private int size;

    public Asteroid(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
