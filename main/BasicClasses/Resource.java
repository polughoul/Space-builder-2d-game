package main.BasicClasses;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.Serializable;

public class Resource implements Serializable {
    private String type;
    private int counts;
    private int x;
    private int y;
    private transient Image image;
    private String imagePath;

    public boolean isPlayerOnResource(int playerX, int playerY) {
        int dx = this.x - playerX;
        int dy = this.y - playerY;
        int distance = (int) Math.sqrt(dx * dx + dy * dy);
        return distance <= 10 / 2;
    }

    public Resource(String type, int counts, int x, int y, String imagePath){
        this.type = type;
        this.counts = counts;
        this.x = x;
        this.y = y;
        this.imagePath = imagePath;
        this.image = new Image("file:" + imagePath);
    }
    public void draw(GraphicsContext gc) {
        if (image == null) {
            image = new Image("file:" + imagePath);
        }
        gc.drawImage(image, x - 40 / 2, y - 40 / 2, 40, 40);
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

}
