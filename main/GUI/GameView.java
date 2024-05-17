package main.GUI;

import main.Controller.Control;
import main.BasicClasses.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class GameView extends JPanel {
    private Player player;
    private Control control;
    private Timer timer;
    private List<SpaceObject> spaceObjects;
    private SpaceObject currentSpaceObject;
    private JButton collectButton;
    private JComboBox<Building> buildingsComboBox;
    private JButton buildButton;
    private boolean isBuilding = false;
    private Building selectedBuilding;

    public GameView() {
        player = new Player(100, 100);
        setFocusable(true);
        requestFocusInWindow();

        Galaxy galaxy = new Galaxy("My Galaxy");
        galaxy.createSpaceObjects();
        spaceObjects = galaxy.getSpaceObjects();

        collectButton = new JButton("Collect Resource");
        collectButton.setBounds(100, 100, 200, 50);
        collectButton.setVisible(false);
        add(collectButton);

        buildingsComboBox = new JComboBox<>();
        buildingsComboBox.setVisible(false);
        buildingsComboBox.addActionListener(e -> requestFocusInWindow());
        add(buildingsComboBox);

        buildButton = new JButton("Construct a building");
        buildButton.setVisible(false);
        add(buildButton);

        control = new Control(player, this);
        addKeyListener(control);

        timer = new Timer(1000 / 60, e -> {
            control.movePlayer();
            repaint();
        });
        timer.start();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isBuilding && currentSpaceObject instanceof Planet && selectedBuilding != null) {
                    selectedBuilding.setX(e.getX());
                    selectedBuilding.setY(e.getY());
                    player.buildBuilding(selectedBuilding, (Planet) currentSpaceObject);
                    isBuilding = false;
                    repaint();
                }
            }
        });
    }

    public JButton getCollectButton() {
        return collectButton;
    }

    public JButton getBuildButton() {
        return buildButton;
    }

    public JComboBox<Building> getBuildingsComboBox() {
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
        buildingsComboBox.removeAllItems();
        if (currentSpaceObject instanceof Planet) {
            Planet currentPlanet = (Planet) currentSpaceObject;
            for (Building building : currentPlanet.getAvailableBuildings()) {
                buildingsComboBox.addItem(building);
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect(player.getX() , player.getY() , 40, 40);
        if (currentSpaceObject != null) {
            if (currentSpaceObject instanceof Asteroid) {
                List<Resource> resources = currentSpaceObject.getResources();
                if (resources != null) {
                    for (Resource resource : resources) {
                        resource.draw(g);
                    }
                }
            } else if (currentSpaceObject instanceof Planet) {
                Planet planet = (Planet) currentSpaceObject;
                List<Resource> resources = planet.getResources();
                List<Bandit> bandits = planet.getBandits();
                List<Builder> builders = planet.getBuilders();
                if (resources != null) {
                    for (Resource resource : resources) {
                        resource.draw(g);
                    }
                }
                if (bandits != null) {
                    for (Bandit bandit : bandits) {
                        bandit.draw(g);
                    }
                }
                if (builders != null) {
                    for (Builder builder : builders) {
                        builder.draw(g);
                    }
                }
            }
        } else {
            for (SpaceObject spaceObject : spaceObjects) {
                g.drawOval(spaceObject.getX() - spaceObject.getSize() / 2, spaceObject.getY() - spaceObject.getSize() / 2, spaceObject.getSize(), spaceObject.getSize());
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
            planet.drawBuildings(g);
            buildingsComboBox.setVisible(true);
            buildButton.setVisible(true);
        } else {
            buildingsComboBox.setVisible(false);
            buildButton.setVisible(false);
        }
        collectButton.setVisible(false);
    }
}