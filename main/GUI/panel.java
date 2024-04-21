package main.GUI;

import main.Controller.Control;
import main.BasicClasses.*;

import javax.swing.*;
import java.awt.*;

public class panel extends JPanel {

    private Game game;
    private int shipX = 400;
    private int shipY = 300;
    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private SpaceObject currentObject = null;
    private Resource currentResource = null;
    private boolean isOnObject = false;
    private JButton collectButton;

    public panel(Game game) {
        this.game = game;
        setFocusable(true);
        addKeyListener(new Control(this));

        JButton leaveButton = new JButton("Leave");
        leaveButton.addActionListener(e -> onLeaveButtonClick());
        add(leaveButton);

        collectButton = new JButton("Collect Resource");
        collectButton.setVisible(false);
        collectButton.addActionListener(e -> {
            if (isOnObject && currentResource != null) {
                currentResource.collect(game.getPlayer());
                currentObject.getResources().remove(currentResource);
                currentResource = null;
                collectButton.setVisible(false);
                repaint();
                requestFocusInWindow();
            }
        });
        add(collectButton);
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

        boolean isOnAnyObject = false;
        for (SpaceObject spaceObject : game.getGalaxy().getSpaceObjects()) {
            if (spaceObject.isPlayerOnObject(shipX, shipY)) {
                isOnAnyObject = true;
                currentObject = spaceObject;
                if (currentObject != spaceObject) {
                    boolean isOnAnyResource = false;
                    for (Resource resource : currentObject.getResources()) {
                        if (resource.isPlayerOnResource(shipX, shipY)) {
                            currentResource = resource;
                            collectButton.setVisible(true);
                            isOnAnyResource = true;
                            break;
                        }
                    }
                    if (!isOnAnyResource) {
                        currentResource = null;
                        collectButton.setVisible(false);
                    }
                }
                break;
            }
        }

        if (!isOnAnyObject && isOnObject) {
            // Если игрок больше не находится на currentObject, обновляем состояние
            isOnObject = false;
            currentObject = null;
            currentResource = null;
            collectButton.setVisible(false);
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GREEN);
        g.fillOval(shipX, shipY, 50, 50);

        if (isOnObject) {
            // Отрисовка дополнительных элементов, связанных с currentObject
            for (Resource resource : currentObject.getResources()) {
                if (resource.getCounts() > 0) {
                    resource.draw(g);
                }
            }
            if (currentObject instanceof Planet) {
                Planet planet = (Planet) currentObject;
                for (SpaceBuilderNpc builder : planet.getSpaceBuilders()) {
                    builder.draw(g);
                }
                for (SpaceBanditNpc bandit : planet.getSpaceBandits()) {
                    bandit.draw(g);
                }
            }
        } else {
            // Отрисовка космических объектов
            for (SpaceObject outerSpaceObject : game.getGalaxy().getSpaceObjects()) {
                g.setColor(Color.BLUE);
                g.fillOval(outerSpaceObject.getX(), outerSpaceObject.getY(), 50, 50);
                g.setColor(Color.WHITE);
                g.drawString(outerSpaceObject.getName(), outerSpaceObject.getX(), outerSpaceObject.getY());
            }
        }
    }
    public void onLeaveButtonClick() {
        isOnObject = false;
        currentObject = null;
        currentResource = null;
        repaint();
        requestFocusInWindow();
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