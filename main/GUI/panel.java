package main.GUI;

import main.Controller.Control;
import main.BasicClasses.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import javax.swing.JButton;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class panel extends JPanel {
    private Player player;
    private Control control;
    private Timer timer;
    private List<SpaceObject> spaceObjects;
    private SpaceObject currentSpaceObject;
    private JButton collectButton;
    private JComboBox<Building> buildingsComboBox;
    private JButton buildButton;

    public panel() {
        player = new Player(100, 100);
        control = new Control(player, this);
        addKeyListener(control);
        setFocusable(true);
        requestFocusInWindow();

        timer = new Timer(1000 / 60, e -> {
            control.movePlayer();
            repaint();
        });
        timer.start();

        Galaxy galaxy = new Galaxy("My Galaxy");
        galaxy.createSpaceObjects();
        spaceObjects = galaxy.getSpaceObjects();

        collectButton = new JButton("Collect Resource");
        collectButton.setBounds(100, 100, 200, 50);
        collectButton.setVisible(false);
        collectButton.addActionListener(e -> {
            if (currentSpaceObject instanceof Asteroid || currentSpaceObject instanceof Planet) {
                for (Resource resource : currentSpaceObject.getResources()) {
                    if (resource.isPlayerOnResource(player.getX(), player.getY())) {
                        player.collectResource(resource);
                        if (currentSpaceObject instanceof Planet) {
                            ((Planet) currentSpaceObject).removeResource(resource);
                        } else if (currentSpaceObject instanceof Asteroid) {
                            ((Asteroid) currentSpaceObject).removeResource(resource);
                        }
                        System.out.println("Collected resources: " + player.getCollectedResources());
                        break;
                    }
                }
            }
        });
        add(collectButton);

        Map<String, Integer> building1Cost = new HashMap<>();
        building1Cost.put("Resource1", 50);
        building1Cost.put("Resource2", 30);
        Building building1 = new Building("building1", building1Cost);

        Map<String, Integer> building2Cost = new HashMap<>();
        building2Cost.put("Resource2", 70);
        building2Cost.put("Resource3", 40);
        Building building2 = new Building("building2", building2Cost);

        Map<String, Integer> building3Cost = new HashMap<>();
        building3Cost.put("Resource1", 20);
        building3Cost.put("Resource3", 60);
        Building building3 = new Building("building3", building3Cost);


        buildingsComboBox = new JComboBox<>();
        buildingsComboBox.setVisible(false);
        buildingsComboBox.addItem(building1);
        buildingsComboBox.addItem(building2);
        buildingsComboBox.addItem(building3);
        buildingsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                requestFocusInWindow();
            }
        });
        add(buildingsComboBox);

        buildButton = new JButton("Construct a building");
        buildButton.setVisible(false);
        buildButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                requestFocusInWindow();

                Building selectedBuilding = (Building) buildingsComboBox.getSelectedItem();
                if (player.hasEnoughResources(selectedBuilding)) {
                    player.buildBuilding(selectedBuilding, (Planet) currentSpaceObject);
                    repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "You don't have enough resources to build this building");
                }
            }
        });
        add(buildButton);
    }

    public void setCurrentSpaceObject(SpaceObject spaceObject) {
        this.currentSpaceObject = spaceObject;
    }

    public SpaceObject getCurrentSpaceObject() {
        return currentSpaceObject;
    }

    public List<SpaceObject> getSpaceObjects() {
        return spaceObjects;
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