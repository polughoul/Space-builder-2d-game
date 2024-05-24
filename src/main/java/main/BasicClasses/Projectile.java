package main.BasicClasses;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * The Projectile class represents a projectile in the game.
 * It contains properties and methods specific to a projectile.
 */
public class Projectile {
    private double x, y;
    private double directionX, directionY;
    private double speed = 4;
    private Object owner;

    /**
     * Constructs a new Projectile with the given parameters.
     *
     * @param startX The x-coordinate of the starting point of the Projectile.
     * @param startY The y-coordinate of the starting point of the Projectile.
     * @param targetX The x-coordinate of the target point of the Projectile.
     * @param targetY The y-coordinate of the target point of the Projectile.
     * @param owner The owner of the Projectile.
     */
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

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}