package main.Controller;

import main.BasicClasses.*;
import main.GUI.GameView;


import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Control extends KeyAdapter {
    private Player player;
    private GameView gameGameView;
    private boolean moveUp, moveDown, moveLeft, moveRight;

    public Control(Player player, GameView gameGameView) {
        this.player = player;
        this.gameGameView = gameGameView;

        gameGameView.getCollectButton().addActionListener(e -> {
            if (gameGameView.getCurrentSpaceObject() instanceof Asteroid || gameGameView.getCurrentSpaceObject() instanceof Planet) {
                for (Resource resource : gameGameView.getCurrentSpaceObject().getResources()) {
                    if (resource.isPlayerOnResource(player.getX(), player.getY())) {
                        player.collectResource(resource);
                        if (gameGameView.getCurrentSpaceObject() instanceof Planet) {
                            ((Planet) gameGameView.getCurrentSpaceObject()).removeResource(resource);
                        } else if (gameGameView.getCurrentSpaceObject() instanceof Asteroid) {
                            ((Asteroid) gameGameView.getCurrentSpaceObject()).removeResource(resource);
                        }
                        System.out.println("Collected resources: " + player.getCollectedResources());
                        break;
                    }
                }
            }
        });

        gameGameView.getBuildButton().addActionListener(e -> {
            gameGameView.requestFocusInWindow();

            Building selectedBuilding = (Building) gameGameView.getBuildingsComboBox().getSelectedItem();
            if (player.hasEnoughResources(selectedBuilding) && gameGameView.getCurrentSpaceObject() instanceof Planet) {
                Planet currentPlanet = (Planet) gameGameView.getCurrentSpaceObject();
                gameGameView.setIsBuilding(true);
                gameGameView.setSelectedBuilding(selectedBuilding);
                gameGameView.getBuildingsComboBox().removeItem(selectedBuilding);
                currentPlanet.removeAvailableBuilding(selectedBuilding);
            } else {
                JOptionPane.showMessageDialog(null, "You don't have enough resources to build this building");
            }
        });
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
        for (SpaceObject spaceObject : gameGameView.getSpaceObjects()) {
            if (spaceObject.isPlayerOnObject(player.getX(), player.getY())) {
                if (!(gameGameView.getCurrentSpaceObject() instanceof Asteroid) && !(gameGameView.getCurrentSpaceObject() instanceof Planet)) {
                    gameGameView.setCurrentSpaceObject(spaceObject);
                }
                break;
            }
        }

    }
}