package main.BasicClasses;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Projectile {
    private double x, y;
    private double directionX, directionY;
    private double speed = 4;
    private Object owner;

    public Projectile(double startX, double startY, double targetX, double targetY, Object owner) {
        this.x = startX;
        this.y = startY;
        this.owner = owner;

        double dx = targetX - startX;
        double dy = targetY - startY;
        double distance = Math.sqrt(dx * dx + dy * dy);

        this.directionX = dx / distance;
        this.directionY = dy / distance;
    }

    public Object getOwner() {
        return owner;
    }


    public void move() {
        x += directionX * speed;
        y += directionY * speed;
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.fillOval(x - 5, y - 5, 10, 10);
    }

    // геттеры для x и y
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}