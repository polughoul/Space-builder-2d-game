package main.GUI;

import java.util.concurrent.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.canvas.*;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.BasicClasses.*;
import main.Controller.Control;
import main.Controller.gameStateManager;
import main.Controller.gameRules;
import main.Controller.ProjectileController;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.util.ArrayList;
import java.util.List;

/**
 * The GameView class represents the game view.
 */
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
    private ScheduledExecutorService banditSpawner;
    private gameStateManager gameStateManager;
    private ProjectileController projectileController;

    private gameRules gameRules = new gameRules();
    private boolean gameOver = false;

    private Label moneyLabel;
    private Label resourcesLabel;
    private Label healthLabel;

    private double mouseX;
    private double mouseY;

    private Pane overlayPane;

    /**
     * Constructs a new GameView.
     */
    public GameView() {
        setFocusTraversable(true);
        control = new Control(player, this);
        control.resetPlayer(this);
        Galaxy galaxy = new Galaxy("My Galaxy");
        galaxy.createSpaceObjects();
        spaceObjects = galaxy.getSpaceObjects();
        gameStateManager = new gameStateManager();
        projectileController = new ProjectileController(projectiles, player, control, this);

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
        buildingsComboBox.setOnAction(e -> this.requestFocus());
        buildingsComboBox.setVisible(false);
        overlayPane.getChildren().add(buildingsComboBox);

        buildButton = new Button("Construct a building");
        buildButton.setVisible(false);
        buildButton.setOnAction(e -> {
            control.buildBuilding();
            this.requestFocus();
        });
        overlayPane.getChildren().add(buildButton);

        tradeButton = new Button("Trade");
        tradeButton.setLayoutX(100);
        tradeButton.setLayoutY(150);
        tradeButton.setVisible(false);
        tradeButton.setFocusTraversable(false);
        tradeButton.setOnAction(e -> control.openTradeWindow());
        overlayPane.getChildren().add(tradeButton);

        moneyLabel = new Label();
        resourcesLabel = new Label();
        healthLabel = new Label();

        overlayPane.getChildren().addAll(moneyLabel, resourcesLabel, healthLabel);

        banditSpawner = Executors.newSingleThreadScheduledExecutor();
        banditSpawner.scheduleAtFixedRate(this::spawnBandits, 0, 1, TimeUnit.MINUTES);

        setOnMouseMoved(e -> {
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
        });

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                control.movePlayer();
                update();
                projectileController.updateProjectiles();
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

    /**
     * Spawns bandits on the planet.
     */
    private void spawnBandits() {
        for (SpaceObject spaceObject : spaceObjects) {
            if (spaceObject instanceof Planet) {
                ((Planet) spaceObject).spawnBandits();
            }
        }
    }

    /**
     * Displays the victory window after player construct all buildings in each planet.
     */
    public void displayVictoryWindow() {
        Stage victoryStage = new Stage();
        victoryStage.setTitle("Victory!");

        Label victoryLabel = new Label("Congratulations! You have won the game!");

        Button newGameButton = new Button("Start New Game");
        newGameButton.setOnAction(e -> {
            victoryStage.close();
            control.resetGame(this);
        });

        Button exitButton = new Button("Exit Game");
        exitButton.setOnAction(e -> System.exit(0));

        VBox vbox = new VBox(victoryLabel, newGameButton, exitButton);
        Scene scene = new Scene(vbox, 300, 200);
        victoryStage.setScene(scene);

        victoryStage.show();
    }


    public AnimationTimer getTimer() {
        return timer;
    }

    public void setPlayer(Player player) {
        this.player = player;
        projectileController = new ProjectileController(projectiles, player, control, this);
    }

    public void setControl(Control control) {
        this.control = control;
        projectileController = new ProjectileController(projectiles, player, control, this);
    }

    public List<Projectile> getProjectiles() {
        return projectiles;
    }

    public void setPlayerAlive(boolean isPlayerAlive) {
        projectileController.setPlayerAlive(isPlayerAlive);
    }

    public Button getCollectButton() {
        return collectButton;
    }

    public Button getBuildButton() {
        return buildButton;
    }

    public Button getTradeButton() {
        return tradeButton;
    }

    public Label getMoneyLabel() {
        return moneyLabel;
    }

    public Label getResourcesLabel() {
        return resourcesLabel;
    }

    public void setSpaceObjects(List<SpaceObject> spaceObjects) {
        this.spaceObjects = spaceObjects;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public double getMouseX() {
        return mouseX;
    }

    public double getMouseY() {
        return mouseY;
    }

    /**
     * Updates the visibility of tradeButton when player step on builder.
     */
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

    /**
     * Returns the list of buildings.
     *
     * @return The buildings combo box.
     */
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

    /**
     * Updates the buildings list.
     */
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

    /**
     * Saves the game.
     *
     * @param filename The filename.
     */
    public void saveGame(String filename) {
        GameState gameState = new GameState();
        gameState.setPlayer(player);
        gameState.setSpaceObjects(spaceObjects);
        gameState.setCurrentSpaceObject(currentSpaceObject);
        gameStateManager.saveGame(filename, gameState);
        this.requestFocus();
    }

    /**
     * Loads the game.
     *
     * @param filename The filename.
     */
    public void loadGame(String filename) {
        GameState gameState = gameStateManager.loadGame(filename);
        if (gameState != null) {
            player = gameState.getPlayer();
            player.setGameView(this);

            ImageView imageView = new ImageView();
            imageView.setFitWidth(80);
            imageView.setFitHeight(80);
            player.setImageView(imageView);
            spaceObjects = gameState.getSpaceObjects();
            currentSpaceObject = gameState.getCurrentSpaceObject();
            control = new Control(player, this);
            projectileController = new ProjectileController(projectiles, player, control, this);
            this.setOnKeyPressed(control::handle);
            this.setOnKeyReleased(control::handle);
            this.setOnKeyPressed(e -> {
                switch (e.getCode()) {
                    case SPACE:
                        player.fire(mouseX, mouseY);
                        break;
                }
            });
            for (SpaceObject spaceObject : spaceObjects) {
                if (spaceObject instanceof Planet) {
                    Planet planet = (Planet) spaceObject;
                    for (Bandit bandit : planet.getBandits()) {
                        bandit.reloadImages();
                    }
                }
            }
        }
        this.requestFocus();
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
                if (spaceObject instanceof Planet) {
                    gc.drawImage(((Planet) spaceObject).getImage(), spaceObject.getX() - spaceObject.getSize() / 2, spaceObject.getY() - spaceObject.getSize() / 2, spaceObject.getSize(), spaceObject.getSize());
                } else if (spaceObject instanceof Asteroid) {
                    gc.drawImage(((Asteroid) spaceObject).getImage(), spaceObject.getX() - spaceObject.getSize() / 2, spaceObject.getY() - spaceObject.getSize() / 2, spaceObject.getSize(), spaceObject.getSize());
                }
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

        if (!gameOver && gameRules.checkVictory(spaceObjects)) {
            gameOver = true;
            displayVictoryWindow();
        }

        for (Projectile projectile : projectiles) {
            projectile.draw(gc);
        }

        collectButton.setVisible(false);

        Font font = new Font("Arial", 20);
        Color color = Color.YELLOW;

        moneyLabel.setFont(font);
        moneyLabel.setTextFill(color);

        resourcesLabel.setFont(font);
        resourcesLabel.setTextFill(color);

        healthLabel.setFont(font);
        healthLabel.setTextFill(color);

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

