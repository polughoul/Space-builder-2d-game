package main.GUI;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.Controller.Control;
import main.BasicClasses.*;

import java.util.ArrayList;
import java.util.Iterator;
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
    private Button tradeButton;
    private boolean isBuilding = false;
    private Building selectedBuilding;
    private Canvas canvas;
    private List<Projectile> projectiles = new ArrayList<>();

    private boolean gameOver = false;

    private Label moneyLabel;
    private Label resourcesLabel;
    private Label healthLabel;

    private double mouseX;
    private double mouseY;

    private Pane overlayPane;

    public GameView() {
        setFocusTraversable(true);
        resetPlayer();
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

        tradeButton = new Button("Trade");
        tradeButton.setLayoutX(100);
        tradeButton.setLayoutY(150);
        tradeButton.setVisible(false);
        tradeButton.setFocusTraversable(false);
        tradeButton.setOnAction(e -> openTradeWindow());
        overlayPane.getChildren().add(tradeButton);

        moneyLabel = new Label();
        resourcesLabel = new Label();
        healthLabel = new Label();

        overlayPane.getChildren().addAll(moneyLabel, resourcesLabel, healthLabel);

        setOnMouseMoved(e -> {
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
        });

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                control.movePlayer();
                update();
                update_projectiles();
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

    public boolean checkVictory() {
        for (SpaceObject spaceObject : spaceObjects) {
            if (spaceObject instanceof Planet) {
                Planet planet = (Planet) spaceObject;
                if (planet.getBuildings().size() < 3) {
                    return false;
                }
            }
        }
        gameOver = true;
        return true;
    }

    public void displayVictoryWindow() {
        Stage victoryStage = new Stage();
        victoryStage.setTitle("Victory!");

        Label victoryLabel = new Label("Congratulations! You have won the game!");

        Button newGameButton = new Button("Start New Game");
        newGameButton.setOnAction(e -> {
            victoryStage.close();
            resetGame();
        });

        Button exitButton = new Button("Exit Game");
        exitButton.setOnAction(e -> {
            System.exit(0);
        });

        VBox vbox = new VBox(victoryLabel, newGameButton, exitButton);
        Scene scene = new Scene(vbox, 300, 200);
        victoryStage.setScene(scene);

        victoryStage.show();
    }

    private void resetPlayer() {
        player = new Player(100, 20, 50, this);
        control = new Control(player, this);
        this.setOnKeyPressed(control::handle);
        this.setOnKeyReleased(control::handle);

        this.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case SPACE:
                    player.fire(mouseX, mouseY);
                    break;
            }
        });
    }

    public void resetGame() {
        timer.stop();

        resetPlayer();
        Galaxy galaxy = new Galaxy("My Galaxy");
        galaxy.createSpaceObjects();
        spaceObjects = galaxy.getSpaceObjects();
        currentSpaceObject = null;
        isBuilding = false;
        selectedBuilding = null;
        gameOver = false;

        collectButton.setVisible(false);
        buildingsComboBox.getItems().clear();
        buildingsComboBox.setVisible(false);
        buildButton.setVisible(false);
        tradeButton.setVisible(false);
        moneyLabel.setText("");
        resourcesLabel.setText("");
        projectiles.clear();
        isPlayerAlive = true;
        timer.start();
    }

    public void update() {
        if (currentSpaceObject instanceof Planet) {
            Planet planet = (Planet) currentSpaceObject;
            for (Builder builder : planet.getBuilders()) {
                if (builder.isPlayerOnBuilder(player.getX(), player.getY())) {
                    tradeButton.setVisible(true);
                    return;
                }
            }
        }
        tradeButton.setVisible(false);
    }

    private void openTradeWindow() {
        if (currentSpaceObject instanceof Planet) {
            Planet planet = (Planet) currentSpaceObject;
            for (Builder builder : planet.getBuilders()) {
                if (builder.isPlayerOnBuilder(player.getX(), player.getY())) {
                    TradeWindow tradeWindow = new TradeWindow(player, builder);
                    tradeWindow.show();
                    return;
                }
            }
        }
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

    public void addProjectile(Projectile projectile) {
        projectiles.add(projectile);
    }
    private boolean isPlayerAlive = true;

    public void update_projectiles() {
        Iterator<Projectile> iterator = projectiles.iterator();

        if (!isPlayerAlive) {
            return;
        }
        if (player.getHealth() <= 0) {
            isPlayerAlive = false;
        }
        while (iterator.hasNext()) {
            Projectile projectile = iterator.next();
            projectile.move();

            // Check if the projectile hits a bandit
            if (currentSpaceObject instanceof Planet) {
                Planet planet = (Planet) currentSpaceObject;
                List<Bandit> bandits = planet.getBandits();
                for (Bandit bandit : bandits) {
                    if (Math.hypot(projectile.getX() - bandit.getX(), projectile.getY() - bandit.getY()) <  10) {
                        if (projectile.getOwner() != bandit) {
                            iterator.remove();
                            bandit.setHealth(bandit.getHealth() - player.getDamage());
                            System.out.println("Bandit was hit. Bandit's health: " + bandit.getHealth());
                            break;
                        }
                    }
                    if (Math.hypot(projectile.getX() - player.getX(), projectile.getY() - player.getY()) <  10) {
                        if (projectile.getOwner() == bandit) {
                            iterator.remove();
                            player.setHealth(player.getHealth() - bandit.getDamage());
                            System.out.println("You were hit. Your health: " + player.getHealth());
                            if (player.getHealth() <= 0) {
                                Platform.runLater(() -> {
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "You died! The game will now restart.", ButtonType.OK);
                                    alert.showAndWait();
                                    if (alert.getResult() == ButtonType.OK) {
                                        resetGame();
                                    }
                                });
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

    protected void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        player.draw(gc);
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
                        bandit.move(player, (Planet) currentSpaceObject, this);
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

        if (!gameOver && checkVictory()) {
            displayVictoryWindow();
        }

        for (Projectile projectile : projectiles) {
            projectile.draw(gc);
        }

        collectButton.setVisible(false);

        moneyLabel.setText("Money: " + player.getMoney());
        resourcesLabel.setText("Resources: " + player.getResources());
        healthLabel.setText("Health: " + player.getHealth());

        moneyLabel.setLayoutX(10);
        moneyLabel.setLayoutY(canvas.getHeight() - 100);
        resourcesLabel.setLayoutX(10);
        resourcesLabel.setLayoutY(canvas.getHeight() - 80);
        healthLabel.setLayoutX(10);
        healthLabel.setLayoutY(canvas.getHeight() - 60);

    }
}

