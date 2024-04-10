package main.GUI;
import main.BasicClasses.Control;

import javax.swing.*;
import java.awt.*;

public class panel extends JPanel{
    private int shipX = 400;
    private int shipY = 300;
    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean leftPressed = false;
    private boolean rightPressed = false;

    public panel() {
        setFocusable(true);
        addKeyListener(new Control(this));
    }

    public void updateShipPosition() {
        if (upPressed && shipY > 0) {
            shipY -= 5;
        }
        if (downPressed && shipY < getHeight() - 50) {
            shipY += 5;
        }
        if (leftPressed && shipX > 0) {
            shipX -= 5;
        }
        if (rightPressed && shipX < getWidth() - 50) {
            shipX += 5;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GREEN);
        g.fillOval(shipX, shipY, 50, 50);
    }

    public void setUpPressed(boolean upPressed) {
        this.upPressed = upPressed;
    }

    public void setDownPressed(boolean downPressed) {
        this.downPressed = downPressed;
    }

    public void setLeftPressed(boolean leftPressed) {
        this.leftPressed = leftPressed;
    }

    public void setRightPressed(boolean rightPressed) {
        this.rightPressed = rightPressed;
    }
}
