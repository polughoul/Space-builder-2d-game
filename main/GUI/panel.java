package main.GUI;

import main.Controller.Control;
import main.BasicClasses.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;

public class panel extends JPanel {
    private Player player;
    private Control control;
    private Timer timer;
    private List<SpaceObject> spaceObjects;
    private SpaceObject currentSpaceObject;
    private JButton collectButton;

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

        spaceObjects = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            spaceObjects.add(new Asteroid( "Asteroid" + i, 20, random.nextInt(1240), random.nextInt(900)));
        }

        for (int i = 0; i < 3; i++) {
            spaceObjects.add(new Planet("Planet" + i, 40,random.nextInt(1240), random.nextInt(900)));
        }

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
        g.fillRect(player.getX() - 20, player.getY() - 20, 40, 40);
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
        collectButton.setVisible(false);
    }
}