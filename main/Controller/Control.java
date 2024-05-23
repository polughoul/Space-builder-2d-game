package main.Controller;

import javafx.scene.control.Alert;
import main.BasicClasses.*;
import main.GUI.GameView;
import main.GUI.TradeWindow;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.List;

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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("You don't have enough resources to build this building");
            alert.showAndWait();
        }
    }

    public void openTradeWindow() {
        if (gameGameView.getCurrentSpaceObject() instanceof Planet) {
            Planet planet = (Planet) gameGameView.getCurrentSpaceObject();
            for (Builder builder : planet.getBuilders()) {
                if (builder.isPlayerOnBuilder(player.getX(), player.getY())) {
                    TradeWindow tradeWindow = new TradeWindow(player, builder);
                    tradeWindow.show();
                    return;
                }
            }
        }
    }

    public void resetPlayer(GameView gameView) {
        Player player = new Player(100, 100, 10, gameView);
        this.player = player;
        gameView.setPlayer(player);
        gameView.setControl(this);
        gameView.setOnKeyPressed(this::handle);
        gameView.setOnKeyReleased(this::handle);

        gameView.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case SPACE:
                    player.fire(gameView.getMouseX(), gameView.getMouseY());
                    break;
            }
        });
    }

    public void resetGame(GameView gameView) {
        gameView.getTimer().stop();

        resetPlayer(gameView);
        Galaxy galaxy = new Galaxy("My Galaxy");
        galaxy.createSpaceObjects();
        List<SpaceObject> spaceObjects = galaxy.getSpaceObjects();
        gameView.setSpaceObjects(spaceObjects);
        gameView.setCurrentSpaceObject(null);
        gameView.setIsBuilding(false);
        gameView.setSelectedBuilding(null);
        gameView.setGameOver(false);

        gameView.getCollectButton().setVisible(false);
        gameView.getBuildingsComboBox().getItems().clear();
        gameView.getBuildingsComboBox().setVisible(false);
        gameView.getBuildButton().setVisible(false);
        gameView.getTradeButton().setVisible(false);
        gameView.getMoneyLabel().setText("");
        gameView.getResourcesLabel().setText("");
        gameView.getProjectiles().clear();
        gameView.setPlayerAlive(true);
        gameView.getTimer().start();
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