package main.Controller;

import main.BasicClasses.*;
import main.GUI.GameView;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Control {
    private Player player;
    private GameView gameGameView;
    private boolean moveUp, moveDown, moveLeft, moveRight;

    public Control(Player player, GameView gameGameView) {
        this.player = player;
        this.gameGameView = gameGameView;

        gameGameView.addEventHandler(KeyEvent.KEY_PRESSED, this::keyPressed);
        gameGameView.addEventHandler(KeyEvent.KEY_RELEASED, this::keyReleased);

    }
    public void collectResource() {
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
    }

    public void buildBuilding() {
        Building selectedBuilding = gameGameView.getBuildingsComboBox().getSelectionModel().getSelectedItem();
        if (player.hasEnoughResources(selectedBuilding) && gameGameView.getCurrentSpaceObject() instanceof Planet) {
            Planet currentPlanet = (Planet) gameGameView.getCurrentSpaceObject();
            gameGameView.setIsBuilding(true);
            gameGameView.setSelectedBuilding(selectedBuilding);
            gameGameView.getBuildingsComboBox().getItems().remove(selectedBuilding);
            currentPlanet.removeAvailableBuilding(selectedBuilding);
        } else {
            System.out.println("You don't have enough resources to build this building");
        }
    }



    public void handle(KeyEvent e) {
        if (e.getEventType() == KeyEvent.KEY_PRESSED) {
            keyPressed(e);
        } else if (e.getEventType() == KeyEvent.KEY_RELEASED) {
            keyReleased(e);
        }
    }
    public void keyPressed(KeyEvent e) {
        if (e.getCode() == KeyCode.W) {
            moveUp = true;
        } else if (e.getCode() == KeyCode.A) {
            moveLeft = true;
        } else if (e.getCode() == KeyCode.S) {
            moveDown = true;
        } else if (e.getCode() == KeyCode.D) {
            moveRight = true;
        } else if (e.getCode() == KeyCode.SPACE) {
            if (gameGameView.getCurrentSpaceObject() instanceof Planet) {
                player.attackNearestBandit((Planet) gameGameView.getCurrentSpaceObject());
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getCode() == KeyCode.W) {
            moveUp = false;
        } else if (e.getCode() == KeyCode.A) {
            moveLeft = false;
        } else if (e.getCode() == KeyCode.S) {
            moveDown = false;
        } else if (e.getCode() == KeyCode.D) {
            moveRight = false;
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