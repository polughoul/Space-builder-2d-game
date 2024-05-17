package main.GUI;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.animation.AnimationTimer;
import main.Controller.Control;
import main.BasicClasses.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class GameView extends Pane {
    private Player player;
    private Control control;
    private AnimationTimer timer;
    private List<SpaceObject> spaceObjects;
    private SpaceObject currentSpaceObject;
    private Button collectButton;
    private ComboBox<Building> buildingsComboBox;
    private Button buildButton;
    private boolean isBuilding = false;
    private Building selectedBuilding;
    private Canvas canvas;

    private Pane overlayPane;

    public GameView() {
        player = new Player(100, 400, 50);
        setFocusTraversable(true);

        Galaxy galaxy = new Galaxy("My Galaxy");
        galaxy.createSpaceObjects();
        spaceObjects = galaxy.getSpaceObjects();

        overlayPane = new Pane();
        overlayPane.setPickOnBounds(false);

        collectButton = new Button("Collect Resource");
        collectButton.setLayoutX(100);
        collectButton.setLayoutY(100);
        collectButton.setVisible(false);
        collectButton.setFocusTraversable(false);
        collectButton.setOnAction(e -> control.collectResource());
        overlayPane.getChildren().add(collectButton);

        buildingsComboBox = new ComboBox<>();
        buildingsComboBox.setVisible(false);
        overlayPane.getChildren().add(buildingsComboBox);

        buildButton = new Button("Construct a building");
        buildButton.setVisible(false);
        buildButton.setOnAction(e -> control.buildBuilding());
        overlayPane.getChildren().add(buildButton);

        control = new Control(player, this);
        setOnKeyPressed(control::handle);
        setOnKeyReleased(control::handle);

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                control.movePlayer();
                draw();
            }
        };
        timer.start();

        setOnMouseClicked(e -> {
            if (isBuilding && currentSpaceObject instanceof Planet && selectedBuilding != null) {
                selectedBuilding.setX((int)e.getX());
                selectedBuilding.setY((int)e.getY());
                player.buildBuilding(selectedBuilding, (Planet) currentSpaceObject);
                isBuilding = false;
                draw();
            }
        });

        canvas = new Canvas(1280, 960);

        getChildren().addAll(canvas, overlayPane);
    }

    public Button getCollectButton() {
        return collectButton;
    }

    public Button getBuildButton() {
        return buildButton;
    }

    public ComboBox<Building> getBuildingsComboBox() {
        return buildingsComboBox;
    }

    public void setCurrentSpaceObject(SpaceObject spaceObject) {
        this.currentSpaceObject = spaceObject;
        updateBuildingsComboBox();
    }

    public void setSelectedBuilding(Building building) {
        this.selectedBuilding = building;
    }

    private void updateBuildingsComboBox() {
        buildingsComboBox.getItems().clear();
        if (currentSpaceObject instanceof Planet) {
            Planet currentPlanet = (Planet) currentSpaceObject;
            for (Building building : currentPlanet.getAvailableBuildings()) {
                buildingsComboBox.getItems().add(building);
            }
        }
    }

    public SpaceObject getCurrentSpaceObject() {
        return currentSpaceObject;
    }

    public List<SpaceObject> getSpaceObjects() {
        return spaceObjects;
    }

    public void setIsBuilding(boolean isBuilding) {
        this.isBuilding = isBuilding;
    }

    protected void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.fillRect(player.getX() , player.getY() , 40, 40);
        if (currentSpaceObject != null) {
            if (currentSpaceObject instanceof Asteroid) {
                List<Resource> resources = currentSpaceObject.getResources();
                if (resources != null) {
                    for (Resource resource : resources) {
                        resource.draw(gc);
                    }
                }
            } else if (currentSpaceObject instanceof Planet) {
                Planet planet = (Planet) currentSpaceObject;
                List<Resource> resources = planet.getResources();
                List<Bandit> bandits = planet.getBandits();
                List<Builder> builders = planet.getBuilders();
                if (resources != null) {
                    for (Resource resource : resources) {
                        resource.draw(gc);
                    }
                }
                if (bandits != null) {
                    for (Bandit bandit : bandits) {
                        bandit.move(player, (Planet) currentSpaceObject);
                        bandit.draw(gc);
                    }
                }
                if (builders != null) {
                    for (Builder builder : builders) {
                        builder.draw(gc);
                    }
                }
            }
        } else {
            for (SpaceObject spaceObject : spaceObjects) {
                gc.fillOval(spaceObject.getX() - spaceObject.getSize() / 2, spaceObject.getY() - spaceObject.getSize() / 2, spaceObject.getSize(), spaceObject.getSize());
            }
        }

        if (currentSpaceObject instanceof Asteroid || currentSpaceObject instanceof Planet) {
            for (Resource resource : currentSpaceObject.getResources()) {
                if (resource.isPlayerOnResource(player.getX(), player.getY())) {
                    collectButton.setVisible(true);
                    return;
                }
            }
        }
        if (currentSpaceObject instanceof Planet) {
            Planet planet = (Planet) currentSpaceObject;
            planet.drawBuildings(gc);
            buildingsComboBox.setVisible(true);
            buildButton.setVisible(true);
        } else {
            buildingsComboBox.setVisible(false);
            buildButton.setVisible(false);
        }
        collectButton.setVisible(false);
    }
}