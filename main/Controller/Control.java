package main.Controller;

import main.BasicClasses.*;
import main.GUI.panel;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Control extends KeyAdapter {
    private Player player;
    private panel gamePanel;
    private boolean moveUp, moveDown, moveLeft, moveRight;

    public Control(Player player, panel gamePanel) {
        this.player = player;
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                moveUp = true;
                break;
            case KeyEvent.VK_A:
                moveLeft = true;
                break;
            case KeyEvent.VK_S:
                moveDown = true;
                break;
            case KeyEvent.VK_D:
                moveRight = true;
                break;
        }

        if (e.getKeyCode() == KeyEvent.VK_E) {
            SpaceObject spaceObject = gamePanel.getCurrentSpaceObject();
            if (spaceObject instanceof Asteroid) {
                for (Resource resource : spaceObject.getResources()) {
                    if (resource.isPlayerOnResource(player.getX(), player.getY())) {
                        player.collectResource(resource);
                        ((Asteroid) spaceObject).removeResource(resource);
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                moveUp = false;
                break;
            case KeyEvent.VK_A:
                moveLeft = false;
                break;
            case KeyEvent.VK_S:
                moveDown = false;
                break;
            case KeyEvent.VK_D:
                moveRight = false;
                break;
        }
    }

    public void movePlayer() {
        if (moveUp) {
            player.moveUp();
        }
        if (moveDown) {
            player.moveDown();
        }
        if (moveLeft) {
            player.moveLeft();
        }
        if (moveRight) {
            player.moveRight();
        }
        for (SpaceObject spaceObject : gamePanel.getSpaceObjects()) {
            if (spaceObject.isPlayerOnObject(player.getX(), player.getY())) {
                // Если игрок уже находится на астероиде, не меняем currentSpaceObject
                if (!(gamePanel.getCurrentSpaceObject() instanceof Asteroid)) {
                    gamePanel.setCurrentSpaceObject(spaceObject);
                }
                break;
            }
        }

    }
}